
package com.ibm.lcd.cfm.monitor.util;

import javax.swing.table.AbstractTableModel;

public class LabeledTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 4541416538111194382L;
	private LabeledTable sourceTable;

    /**
     *
     */
    public LabeledTableModel(LabeledTable sourceTable) {
        super();
        this.sourceTable = sourceTable;
    }

    /**
     *
     */
    public final int getRowCount() {
        LabeledTable currentTable = sourceTable;
        return (currentTable != null) ? currentTable.getRows() : 0;
    }

    /**
     *
     */
    public int getColumnCount() {
        LabeledTable currentTable = sourceTable;
        return (currentTable != null) ? currentTable.getColumns() : 0;
    }

    /**
     *
     */
    public final String getColumnName(int columnIndex) {
        try {
            return sourceTable.getLabel(columnIndex);
        }
        catch (Exception ex) {}
        return null;
    }

    /**
     *
     */
    public final Object getValueAt(int rowIndex, int columnIndex) {
        try {
            return sourceTable.get(rowIndex, columnIndex);
        }
        catch (Exception ex) {}
        return null;
    }

    /**
     *
     */
    public final void fireTableDataChanged(LabeledTable sourceTable) {
        this.sourceTable = sourceTable;
        fireTableDataChanged();
    }

    /**
     *
     */
    public final void fireTableStructureChanged(LabeledTable sourceTable) {
        this.sourceTable = sourceTable;
        fireTableStructureChanged();
    }

}
