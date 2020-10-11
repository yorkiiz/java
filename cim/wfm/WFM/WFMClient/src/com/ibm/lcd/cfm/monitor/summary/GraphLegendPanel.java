
package com.ibm.lcd.cfm.monitor.summary;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import com.ibm.lcd.cfm.monitor.util.GenericTable;

public final class GraphLegendPanel extends JPanel {
	private static final long serialVersionUID = 4714763358203004271L;

	final String CLASS_NAME = "GraphLegendPanel";

    private final static int BOX_HGAP = 2;
    private final static int BOX_VGAP = 1;

    private Color[] legendColor = null;
    private volatile GraphLegendData[] currentData = null;
    private Insets panelInsets = null;

    public GraphLegendPanel(Color[] colorTable) {
        super();
        
        
        this.legendColor = colorTable;
        setBorder(new MatteBorder(10, 10, 10, 10, getBackground()));
        Border border = getBorder();
        if (border != null)
            panelInsets = border.getBorderInsets(this);
            
    }

    public final void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        GraphLegendData[] data = currentData;
        Dimension legendSize = null;

        if (data != null) {
            legendSize = drawLegend(data, g, panelInsets);
        }
        if (legendSize != null) {
            if (!legendSize.equals(getPreferredSize())) {
                setPreferredSize(legendSize);
                revalidate();

                Container c = getTopLevelAncestor();
                if (c != null)
                    c.repaint();
            }
        }
    }

    public final void setGraphData(GenericTable table) {
    	
        if (table == null) {
            currentData = null;
            return;
        }

        int col, cols;
        GraphLegendData[] data = null;

        cols = table.getColumns();
        if (table.getRows() > 0 && cols > 1) {
            data = new GraphLegendData[cols - 1];
            for (col = 1; col < cols; col++) {
                data[col - 1] = new GraphLegendData();
                try {
                    data[col - 1].text = (String)table.get(0, col);
                } catch (Exception ex) {
                    data[col - 1].text = "";
                }
                data[col - 1].color = getLegendColor(col - 1);
                Thread.yield();
            }
        }
        currentData = data;
        
    }

    private Dimension drawLegend(GraphLegendData[] data, Graphics g, Insets insets) {
    	
        int top = 0, left = 0, right = 0, bottom = 0;
        if (insets != null) {
            top = insets.top;
            left = insets.left;
            right = insets.right;
            bottom = insets.bottom;
        }

        FontMetrics fontMetrics = g.getFontMetrics();
        int boxWidth = fontMetrics.getStringBounds("__", g).getBounds().width;
        int boxHeight = fontMetrics.getStringBounds("|yj", g).getBounds().height;
        int textMaxWidth = 0;
        int dataTop = top;
        int i;
        Rectangle rect = null;

        for (i = 0; i < data.length; i++) {
            g.setColor(data[i].color);
            g.fill3DRect(left, dataTop, boxWidth, boxHeight, true);
            g.setColor(Color.black);
            if (data[i].text != null && data[i].text.length() > 0) {
                rect = fontMetrics.getStringBounds(data[i].text, g).getBounds();
                if (rect.width > textMaxWidth)
                    textMaxWidth = rect.width;
                g.drawString(data[i].text, left + boxWidth + BOX_HGAP, dataTop + boxHeight - ((boxHeight - rect.height) / 2) - 1);
            }
            dataTop += (boxHeight + BOX_VGAP);
        }
        dataTop += (boxHeight + BOX_VGAP);

        return new Dimension(left + boxWidth + BOX_HGAP + textMaxWidth + right, top + dataTop + bottom);
    }

    private Color getLegendColor(int columnIndex) {
        if (legendColor == null || legendColor.length <= 0)
            return Color.red;
        columnIndex = columnIndex % legendColor.length;
        return legendColor[columnIndex];
    }

    private final class GraphLegendData {
        public String text = null;
        public Color color = null;
        public GraphLegendData() {}
    }

}
