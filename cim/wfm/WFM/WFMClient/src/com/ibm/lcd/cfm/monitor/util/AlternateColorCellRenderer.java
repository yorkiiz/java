
package com.ibm.lcd.cfm.monitor.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class AlternateColorCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 6955223710420365856L;
	private final Color firstColor;
    private final Color secondColor;

    public AlternateColorCellRenderer(Color firstColor, Color secondColor) {
        super();
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }

    public final Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (firstColor != null && (row & 0x01) == 0)
            setBackground(firstColor);
        else if (secondColor != null && (row & 0x01) != 0)
            setBackground(secondColor);
        return super.getTableCellRendererComponent(table, value, false, hasFocus, row, column);
    }

}
