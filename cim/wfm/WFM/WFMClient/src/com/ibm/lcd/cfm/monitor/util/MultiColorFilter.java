
package com.ibm.lcd.cfm.monitor.util;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class MultiColorFilter extends RGBImageFilter {

    class FilterEntry {
        int beforeRed = 0;
        int beforeGreen = 0;
        int beforeBlue = 0;
        int afterRed = 0;
        int afterGreen = 0;
        int afterBlue = 0;
    }
    
	public static final int bmp_color[] = {
		0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000, 
		0x000000FF, 0x000000FF, 0x000000FF, 0x000000FF, 0x000000FF, 0x000000FF, 0x000000FF, 0x000000FF, 
		0x0000FF00, 0x0000FF00, 0x0000FF00, 0x0000FF00, 0x0000FF00, 0x0000FF00, 0x0000FF00, 0x0000FF00, 
		0x0000FFFF, 0x0000FFFF, 0x0000FFFF, 0x0000FFFF, 0x0000FFFF, 0x0000FFFF, 0x0000FFFF, 0x0000FFFF, 
		0x00FF0000, 0x00FF0000, 0x00FF0000, 0x00FF0000, 0x00FF0000, 0x00FF0000, 0x00FF0000, 0x00FF0000, 
		0x00FF00FF, 0x00FF00FF, 0x00FF00FF, 0x00FF00FF, 0x00FF00FF, 0x00FF00FF, 0x00FF00FF, 0x00FF00FF, 
		0x00FFFF00, 0x00FFFF00, 0x00FFFF00, 0x00FFFF00, 0x00FFFF00, 0x00FFFF00, 0x00FFFF00, 0x00FFFF00, 
		0x00FFFFFF, 0x00FFFFFF, 0x00FFFFFF, 0x00FFFFFF, 0x00FFFFFF, 0x00FFFFFF, 0x00FFFFFF, 0x00FFFFFF, 

		0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 0xFF000000, 
		0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 0xFF0000FF, 
		0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 0xFF00FF00, 
		0xFF00FFFF, 0xFF00FFFF, 0xFF00FFFF, 0xFF00FFFF, 0xFF00FFFF, 0xFF00FFFF, 0xFF00FFFF, 0xFF00FFFF, 
		0xFFFF0000, 0xFFFF0000, 0xFFFF0000, 0xFFFF0000, 0xFFFF0000, 0xFFFF0000, 0xFFFF0000, 0xFFFF0000, 
		0xFFFF00FF, 0xFFFF00FF, 0xFFFF00FF, 0xFFFF00FF, 0xFFFF00FF, 0xFFFF00FF, 0xFFFF00FF, 0xFFFF00FF, 
		0xFFFFFF00, 0xFFFFFF00, 0xFFFFFF00, 0xFFFFFF00, 0xFFFFFF00, 0xFFFFFF00, 0xFFFFFF00, 0xFFFFFF00, 
		0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 
	};


    private FilterEntry[] entries = new FilterEntry[0];
    private int range = 0;

    public MultiColorFilter(int range) {
        this.range = Math.abs(range);
    }

    public final void add(Color before, Color after) {
        FilterEntry[] newEntries = new FilterEntry[entries.length + 1];
        System.arraycopy(entries, 0, newEntries, 0, entries.length);

        FilterEntry entry = new FilterEntry();
        if (before != null) {
            entry.beforeRed = before.getRed();
            entry.beforeGreen = before.getGreen();
            entry.beforeBlue = before.getBlue();
        }
        if (after != null) {
            entry.afterRed = after.getRed();
            entry.afterGreen = after.getGreen();
            entry.afterBlue = after.getBlue();
        }
        newEntries[entries.length] = entry;
        entries = newEntries;
    }

    public final boolean isEmpty() {
        return (entries.length <= 0);
    }

    public final int filterRGB(int x, int y, int rgb) {
        int r = (rgb & 0x00FF0000) >> 16;
        int g = (rgb & 0x0000FF00) >> 8;
        int b = (rgb & 0x000000FF);
        int re, ge, be;
        for (int i = 0; i < entries.length; i++) {
            re = r - entries[i].beforeRed;
            ge = g - entries[i].beforeGreen;
            be = b - entries[i].beforeBlue;
            if ((Math.abs(re) <= range) &&
                (Math.abs(ge) <= range) &&
                (Math.abs(be) <= range)) {
				  r = entries[i].afterRed;
				  g = entries[i].afterGreen;
				  b = entries[i].afterBlue;
                return ((rgb & 0xFF000000) | (r << 16) | (g << 8) | b);
            }
        }
        return rgb;
    }

}
