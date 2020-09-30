
package com.ibm.lcd.cfm.monitor.config;

import java.awt.Color;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SummaryConfig {
    final String CLASS_NAME = "SummaryConfig";

    public String uri = "";

    public String titleKey = "";

    public int type = 0;

    public Color[] colorTable = null;

    public SummaryConfig() {
    }

    public void parseColors(String colorString) {

        colorTable = null;
        if (colorString == null || colorString.length() <= 0)
            return;

        StringTokenizer token = new StringTokenizer(colorString, ",");
        String s = null;
        Color color = null;
        ArrayList list = new ArrayList();

        while (token.hasMoreTokens()) {
            s = token.nextToken().trim();
            try {
                color = new Color(Integer.parseInt(s, 16));
                list.add(color);
            } catch (Exception ex) {}
        }
        colorTable = (Color[])list.toArray(new Color[list.size()]);
    }

    public boolean isData() {
        return (type == 0);
    }

    public boolean isGraph() {
        return (type == 1);
    }

}
