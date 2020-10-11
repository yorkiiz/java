
package com.ibm.lcd.cfm.monitor.summary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import com.ibm.lcd.cfm.monitor.util.GenericTable;

public final class GraphPanel extends JPanel {

	private static final long serialVersionUID = -6566366549657570899L;
	private final static int SERIES_GAP = 10;
    private final static int DATA_GAP = 1;
    private final static int SCALE_COUNT = 10;
    private final static int SCALE_MAX_INTERVAL = 10;

    private volatile GraphData currentData = null;
    private Color[] colorTable = null;
    private Insets panelInsets = null;

    public GraphPanel(Color[] colorTable) {
        super();
        this.colorTable = colorTable;
        setBackground(Color.white);
        setBorder(new MatteBorder(10, 10, 10, 10, Color.white));
        Border border = getBorder();
        if (border != null)
            panelInsets = border.getBorderInsets(this);
    }

    public final void paintComponent(Graphics g) {
        super.paintComponent(g);

        GraphData data = currentData;
        Rectangle rect = getVisibleRect();
        Dimension graphSize = null;

        if (data != null) {
            graphSize = drawGraph(data, g, rect.width, rect.height, panelInsets);
        }
        if (graphSize == null)
            graphSize = new Dimension(rect.width, rect.height);
        if (!graphSize.equals(getPreferredSize())) {
            setPreferredSize(graphSize);
            revalidate();
        }
    }

    public final void setGraphData(GenericTable table) {
        if (table == null) {
            currentData = null;
            return;
        }

        int i, row, rows, col, cols;
        String str = null;
        GraphData data = new GraphData();

        rows = table.getRows();
        cols = table.getColumns();
        if (rows <= 0 || cols <= 0) {
            currentData = null;
            return;
        }

        String[] sa = new String[rows - 1];
        for (i = 0; i < data.series.length; i++)
            sa[i] = data.series[i];
        for (; i < rows - 1; i++) {
            try {
                str = (String)table.get(i + 1, 0);
            } catch (Exception ex) {
                str = "";
            }
            sa[i] = str;
        }
        data.series = sa;

        int[] ia = new int[rows - 1];
        for (i = 0; i < data.seriesMax.length; i++)
            ia[i] = data.seriesMax[i];
        for (; i < rows - 1; i++)
            ia[i] = 0;
        data.seriesMax = ia;

        data.value = new int[rows - 1][cols - 1];
        for (row = 1; row < rows; row++) {
            data.seriesMax[row - 1] = 0;
            for (col = 1; col < cols; col++) {
                try {
                    i = ((Number)table.get(row, col)).intValue();
                } catch (Exception ex) {
                    i = 0;
                }
                data.value[row - 1][col - 1] = i;
                data.seriesMax[row - 1] += i;
                Thread.yield();
            }
            if (data.seriesMax[row - 1] > data.maxValue)
                data.maxValue = data.seriesMax[row - 1];
        }

        data.maxValue = ((data.maxValue / SCALE_MAX_INTERVAL) + 1) * SCALE_MAX_INTERVAL; // Determine max value.
        if (data.maxValue <= 0)
            data.maxValue = 1;
        currentData = data;
    }

    private Dimension drawGraph(GraphData data, Graphics g, int width, int height, Insets insets) {
        int top = 0, left = 0, right = 0, bottom = 0;
        if (insets != null) {
            top = insets.top;
            left = insets.left;
            right = insets.right;
            bottom = insets.bottom;
        }
        if (width <= 0 || height <= 0)
            return null;

        FontMetrics fontMetrics = g.getFontMetrics();
        Rectangle rect = null;

        int scaleTextWidth = 0; // maximum width of scale text
        int scaleTextHeight = 0; // maximum height of scale text
        int seriesTextMaxWidth = 0; // maximum width of series text
        int seriesTextMaxHeight = 0; // maximum height of series text
        int[] seriesTextHeight = null; // height of each series text
        int i, j, k, row, col;

        // ---------- Measure necessary area.

        // Measure maximum size of scale text.
        scaleTextWidth = fontMetrics.getStringBounds("__" + data.maxValue, g).getBounds().width;
        scaleTextHeight = fontMetrics.getStringBounds("0123456789", g).getBounds().height;

        // Measure maximum width of series text.
        seriesTextHeight = new int[data.series.length];
        for (i = 0; i < data.series.length; i++) {
            seriesTextHeight[i] = scaleTextHeight;
            if (data.series[i] != null && data.series[i].length() > 0) {
                rect = fontMetrics.getStringBounds(data.series[i], g).getBounds();
                seriesTextHeight[i] = rect.height;
                if (rect.height > seriesTextMaxHeight)
                    seriesTextMaxHeight = rect.height;
                if (rect.width > seriesTextMaxWidth)
                    seriesTextMaxWidth = rect.width;
            }
        }
        if (seriesTextMaxWidth < scaleTextWidth)
            seriesTextMaxWidth = scaleTextWidth;
        if (seriesTextMaxHeight < scaleTextHeight)
            seriesTextMaxHeight = scaleTextHeight;

        // Calculate graph area.
        int graphHeight = ((SERIES_GAP + seriesTextMaxHeight) * data.series.length) + SERIES_GAP + scaleTextHeight;
        int graphWidth = seriesTextMaxWidth + (scaleTextWidth * SCALE_COUNT);
        if (graphWidth < (width - left - right))
            graphWidth = (width - left - right);
        if (graphHeight < (height - top - bottom))
            graphHeight = (height - top - bottom);

        // Calculate plot area.
        int plotBase = graphHeight - scaleTextHeight;
        int plotHeight = plotBase - SERIES_GAP;
        int plotWidth = graphWidth - seriesTextMaxWidth - scaleTextWidth;
        int seriesHeight = plotHeight;
        if (data.series.length > 0) {
            seriesHeight = plotHeight / data.series.length;
            plotHeight = seriesHeight * data.series.length;
        }
        plotWidth = (plotWidth / SCALE_COUNT) * SCALE_COUNT;

        // Calculate data area.
        int dataHeight = seriesHeight - SERIES_GAP;
        int dataTop = 0;
        int dataLeft = 0;

        // ---------- Draw frame.

        // Draw line.
        g.setColor(Color.black);
        i = left + seriesTextMaxWidth;
        g.drawLine(i, top, i, top + plotBase);
        g.drawLine(i, top + plotBase, i + plotWidth, top + plotBase);

        // Draw scale text.
        i = plotWidth / SCALE_COUNT;
        j = data.maxValue / SCALE_COUNT;
        g.drawString("0", left + seriesTextMaxWidth, top + plotBase + scaleTextHeight);
        for (k = 0; k < SCALE_COUNT - 1; k++)
            g.drawString("" + j * (k + 1), left + seriesTextMaxWidth + (i * (k + 1)), top + plotBase + scaleTextHeight);
        g.drawString("" + data.maxValue, left + seriesTextMaxWidth + plotWidth, top + plotBase + scaleTextHeight);

        // Draw scale line.
        g.setColor(Color.lightGray);
        for (j = 0; j < SCALE_COUNT; j++)
            g.drawLine(left + seriesTextMaxWidth + (i * (j + 1)), top, left + seriesTextMaxWidth + (i * (j + 1)), top + plotBase - 1);

        // Draw series text.
        g.setColor(Color.black);
        i = SERIES_GAP + (int)((seriesHeight - SERIES_GAP) * 0.5);
        dataLeft = seriesTextMaxWidth + 1;
        for (row = 0; row < data.series.length; row++) {
            j = top + (seriesHeight * row) + i + (int)(seriesTextHeight[row] * 0.5) - 1;
            g.drawString(data.series[row], left, j);
            if (data.seriesMax[row] > 0) {
                k = (int)(((double)data.seriesMax[row] / data.maxValue) * plotWidth) + 1;
                g.drawString("" + data.seriesMax[row], left + dataLeft + k, j);
            }
        }

        // ---------- Draw data.
        for (row = 0; row < data.value.length; row++) {
            dataLeft = seriesTextMaxWidth + 1;
            dataTop = (seriesHeight * row) + SERIES_GAP;

            for (col = 0; col < data.value[row].length; col++) {
                i = (int)(((double)data.value[row][col] / data.maxValue) * plotWidth);
                if (i <= 0)
                    continue;
                g.setColor(getDataColor(col));
                g.fill3DRect(left + dataLeft, top + dataTop, i, dataHeight - DATA_GAP, true);
                dataLeft = dataLeft + i;
            }
        }
        return new Dimension(left + graphWidth + right, top + graphHeight + bottom);
    }

    private Color getDataColor(int columnIndex) {
        if (colorTable == null || colorTable.length <= 0)
            return Color.red;
        columnIndex = columnIndex % colorTable.length;
        return colorTable[columnIndex];
    }

    private final class GraphData {
        public int[][] value = null;
        public String[] series = new String[0];
        public int[] seriesMax = new int[0];
        public int maxValue = 0;
        public GraphData() {}
    }

}
