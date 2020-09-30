
package com.ibm.lcd.cfm.monitor.util;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JLabel;


public final class LabelRunner implements Runnable {

    private final JLabel label;
    private byte dirty = 0;
    private String text = null;
    private Icon icon = null;
    private Color fg = null;
    private Color bg = null;

    public LabelRunner(JLabel label) {
        this.label = label;
    }

    public final void setText(String text) {
        this.text = text;
        dirty |= 0x01;
    }

    public final void setIcon(Icon icon) {
        this.icon = icon;
        dirty |= 0x02;
    }

    public final void setForeground(Color fg) {
        this.fg = fg;
        dirty |= 0x04;
    }

    public final void setBackground(Color bg) {
        this.bg = bg;
        dirty |= 0x08;
    }

    public final void run() {
        if (label != null) {
            if ((dirty & 0x01) != 0)
                label.setText(text);
            if ((dirty & 0x02) != 0)
                label.setIcon(icon);
            if ((dirty & 0x04) != 0 && fg != null)
                label.setForeground(fg);
            if ((dirty & 0x08) != 0 && bg != null)
                label.setBackground(bg);
        }
        dirty = 0;
    }

}
