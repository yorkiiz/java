
package com.ibm.lcd.cfm.monitor.util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class GraphCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 4049205833415070834L;

    public GraphCellRenderer() {
        super();
    }

    public final Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column == 0)
            return table.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Component component = super.getTableCellRendererComponent(table, value, false, hasFocus, row, column);
        if (value instanceof Number && component instanceof JLabel) {
            ((JLabel)component).setHorizontalAlignment(JLabel.RIGHT);
        }
        return component;
    }

}
