package com.ibm.lcd.cfm.monitor.summary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.ibm.lcd.cfm.monitor.config.MonitorConfig;
import com.ibm.lcd.cfm.monitor.config.SummaryConfig;
import com.ibm.lcd.cfm.monitor.util.*;

public final class SummaryPanel extends ThreadPanel {

	private static final long serialVersionUID = 6040358726926398769L;

	private final static Color[] defaultColorTable = {
        Color.red, Color.green, Color.blue, Color.pink, Color.gray, Color.yellow, Color.cyan, Color.orange, Color.magenta
    };


    private boolean errorFlag = false;
    private String trackingUri = null;
    private CSVReceiver receiver = null;
    private GraphTableRunner updateRunner = null;

    private final SummaryConfig summaryConfig;
    private Color[] colorTable = null;
    private JLabel titleLabel = null;
    private GraphTable dataTable = null;
    private GraphPanel graphPanel = null;
    private GraphLegendPanel legendPanel = null;
    private LabelRunner titleLabelRunner = null;

    public SummaryPanel(SummaryConfig config) {
        super();
        summaryConfig = config;
        if (summaryConfig != null) {
            trackingUri = summaryConfig.uri;
            if (summaryConfig.colorTable != null && summaryConfig.colorTable.length > 0)
                colorTable = summaryConfig.colorTable;
        }
        if (colorTable == null)
            colorTable = defaultColorTable;
        initPanel();
    }

    private void initPanel() {
        JPanel panel = null;
        JScrollPane scrollPane = null;
        JTabbedPane tabbedPane = new JTabbedPane();

        titleLabel = new JLabel(" ");
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        titleLabel.setIcon(MonitorConfig.getIcon(MonitorConfig.IMG_STAT_OFF));
        titleLabelRunner = new LabelRunner(titleLabel);

        panel = new JPanel(new BorderLayout());
        if (summaryConfig != null && summaryConfig.isGraph()) {
            graphPanel = new GraphPanel(colorTable);
            scrollPane = new JScrollPane(graphPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
            panel.add(scrollPane, BorderLayout.CENTER);

            legendPanel = new GraphLegendPanel(colorTable);
            scrollPane = new JScrollPane(legendPanel);
            scrollPane.getVerticalScrollBar().setUnitIncrement(20);
            scrollPane.getHorizontalScrollBar().setUnitIncrement(20);
            panel.add(scrollPane, BorderLayout.WEST);

            tabbedPane.addTab(MonitorConfig.getMessage("summary.tab.graph"), panel);
        }

        dataTable = new GraphTable(new LabeledTableModel(new LabeledTable()));
        scrollPane = new JScrollPane(dataTable);
        tabbedPane.addTab(MonitorConfig.getMessage("summary.tab.table"), scrollPane);
        tabbedPane.setSelectedIndex(0);

        setLayout(new BorderLayout());
        add(titleLabel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    public final void clear() {
        if (graphPanel != null) {
            graphPanel.setGraphData(null);
            graphPanel.repaint();
        }
        if (legendPanel != null) {
            legendPanel.setGraphData(null);
            legendPanel.repaint();
        }
        SwingUtilities.invokeLater(new GraphTableRunner(null));
    }

    protected final boolean beforeRun() {
        receiver = new CSVReceiver();
        updateRunner = new GraphTableRunner(null);
        errorFlag = false;
        return true;
    }

    /**
     * This method will be invoked when the thread waked up.
     *
     * @param thisThread The current thread.
     * @param workerRefresh whether thread needs to refresh (not wake up).
     * @return true if the thread can continue, false otherwise.
     */
    protected final boolean doRun(Thread thisThread, boolean workerRefresh) throws InterruptedException, InvocationTargetException {
        GenericTable tbl = null;

        try {
            titleLabelRunner.setIcon(MonitorConfig.getIcon(MonitorConfig.IMG_STAT_ON));
            SwingUtilities.invokeAndWait(titleLabelRunner);

            tbl = receiver.receive(trackingUri);
            optimizeTable(tbl);
            if (graphPanel != null) {
                graphPanel.setGraphData(tbl);
                graphPanel.repaint();
            }
            if (legendPanel != null) {
                legendPanel.setGraphData(tbl);
                legendPanel.repaint();
            }

            updateRunner.setSourceTable(createDataTable(tbl));
            SwingUtilities.invokeAndWait(updateRunner);

            titleLabelRunner.setIcon(MonitorConfig.getIcon(MonitorConfig.IMG_STAT_OK));
            SwingUtilities.invokeAndWait(titleLabelRunner);
            return true;

        } catch (IOException ex) {
            ex.printStackTrace();
            errorFlag = true;
            clear();
        }
        return false;
    }

    /**
     * This method will be invoked after the thread is stopped.
     *
     */
    protected final void afterRun() {
        receiver = null;
        updateRunner = null;
        if (errorFlag)
            titleLabelRunner.setIcon(MonitorConfig.getIcon(MonitorConfig.IMG_STAT_NG));
        else
            titleLabelRunner.setIcon(MonitorConfig.getIcon(MonitorConfig.IMG_STAT_OFF));
        SwingUtilities.invokeLater(titleLabelRunner);
    }

    // ------------------------------------------------------ Private Methods

    /**
     * Optimizes the specified table for processing the summary data.
     *
     * @param tbl
     */
    private void optimizeTable(GenericTable tbl) throws InterruptedException {
        if (tbl == null)
            return;

        int rows = tbl.getRows();
        int cols = tbl.getColumns();
        int row, col;
        String str = null;

        // Optimize label cell.
        if (rows > 0) {
            for (col = 0; col < cols; col++) {
                try {
                    str = (String)tbl.get(0, col);
                } catch (Exception ex) {
                    str = null;
                }
                if (str == null || str.length() <= 0)
                    tbl.set(0, col, " ");
            }
        }
        if (cols > 0) {
            for (row = 0; row < rows; row++) {
                try {
                    str = (String)tbl.get(row, 0);
                } catch (Exception ex) {
                    str = null;
                }
                if (str == null || str.length() <= 0)
                    tbl.set(row, 0, " ");
            }
        }

        // Optimize data cell.
        if (summaryConfig == null || !summaryConfig.isGraph())
            return;
        for (row = 1; row < rows; row++) {
            for (col = 1; col < cols; col++) {
                try {
                    tbl.set(row, col, new Integer((String)tbl.get(row, col)));
                } catch (Exception ex) {
                    tbl.set(row, col, new Integer(0));
                }
                //doYield();
            }
        }
    }

    /**
     * Create a data table witht the specified table.
     *
     * @param tbl
     */
    private LabeledTable createDataTable(GenericTable tbl){
        if (tbl == null || tbl.getColumns() <= 0)
            return new LabeledTable();

        LabeledTable dataTbl = new LabeledTable(tbl);
        dataTbl.setLabel(0, " ");
        return dataTbl;
    }

    private final class GraphTable extends JTable {
		private static final long serialVersionUID = 6667623157797516378L;

        public GraphTable(TableModel dm) {
            super(dm);
            setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            setColumnSelectionAllowed(false);
            setCellSelectionEnabled(false);
            setRowSelectionAllowed(true);

            JTableHeader header = getTableHeader();
            header.setReorderingAllowed(false);
        }

        public final boolean isCellEditable(int row, int column) {
            return false;
        }

        public final void createDefaultColumnsFromModel() {
            TableModel tableModel = getModel();
            if (tableModel != null) {
                int cnt = tableModel.getColumnCount();
                TableColumn column = null;
                TableColumnModel columnModel = getColumnModel();
                while (columnModel.getColumnCount() > cnt)
                    columnModel.removeColumn(columnModel.getColumn(cnt));
                for (int i = columnModel.getColumnCount(); i < cnt; i++) {
                    column = new TableColumn(i);
                    addColumn(column);
                    setDefaultRenderer(getColumnClass(i), new GraphCellRenderer());
                }
                resizeAndRepaint();
            }
        }
    }

    /**
     *
     */
    private final class GraphTableRunner implements Runnable {
        LabeledTable sourceTable = null;

        /**
         *
         */
        public GraphTableRunner(LabeledTable sourceTable) {
            this.sourceTable = sourceTable;
        }

        /**
         *
         */
        public final void setSourceTable(LabeledTable sourceTable) {
            this.sourceTable = sourceTable;
        }

        /**
         *
         */
        public final void run() {
            LabeledTableModel tableModel = (LabeledTableModel)dataTable.getModel();
            tableModel.fireTableStructureChanged(sourceTable);
        }
    }

}
