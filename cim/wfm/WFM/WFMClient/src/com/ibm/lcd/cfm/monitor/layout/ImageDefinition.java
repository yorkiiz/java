
package com.ibm.lcd.cfm.monitor.layout;

import java.awt.Color;
import java.awt.Image;

public class ImageDefinition extends IdentifyObject {

    public Image image = null;
    public String path = null;
    public Color baseColor = null;
    public Color borderColor = null;
    public boolean loaded = false;

    public ImageDefinition(String id) {
        super(id);
    }

}
