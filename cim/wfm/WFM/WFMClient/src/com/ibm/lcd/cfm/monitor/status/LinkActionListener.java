
package com.ibm.lcd.cfm.monitor.status;

import java.applet.Applet;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.ibm.lcd.cfm.monitor.config.MonitorConfig;
import com.ibm.lcd.cfm.monitor.layout.BaseLayer;
import com.ibm.lcd.cfm.monitor.layout.LinkDefinition;
import com.ibm.lcd.cfm.monitor.layout.LinkParameter;
import com.ibm.lcd.cfm.monitor.util.URLRequester;


public final class LinkActionListener extends AbstractAction {
	private static final long serialVersionUID = -714673968088317672L;
	
    private final Container container;
    private final LinkDefinition link;
    private Object data;
    private final String target;

    public LinkActionListener(Container container, Object data, LinkDefinition link, String target) {
        this.container = container;
        this.data = data;
        this.link = link;
        this.target = target;
    }
    
    public final void actionPerformed(ActionEvent e) {

        if (link == null || !(container instanceof Applet))
            return;
        Map map = createParameterMap();
        String str = URLRequester.getURLString(link.url, map);
        if (str.length() <= 0)
            return;
        if (str.startsWith("/")) {
            String contextPath = MonitorConfig.get(MonitorConfig.CONTEXT_PATH, "");
            if (contextPath.endsWith("/"))
                str = contextPath + str.substring(1);
            else
                str = contextPath + str;
        }
        try {
            Applet applet = (Applet)container;
            URL url = new URL(applet.getDocumentBase(), str);
            str = (target != null && target.length() > 0) ? target : "_blank";
            applet.getAppletContext().showDocument(url, str);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(container, ex.toString(), MonitorConfig.getMessage("error.title"), JOptionPane.ERROR_MESSAGE);
        }
    }

    private Map createParameterMap() {

        if (link == null || data == null)
            return null;
        
        LinkParameter[] parameters = link.getLinkParameters();
        if (parameters.length <= 0)
            return null;
        
        HashMap map = new HashMap();
        String value;
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].name == null || parameters[i].name.length() == 0)
                continue;

            if (parameters[i].refer == null) {
                value = parameters[i].value;
            }
            else if (data instanceof BaseLayer) {
                value = ((BaseLayer)data).getReferredValue(parameters[i].refer);
            }
            else {
                try {
                    value = (String)data;
                }
                catch (Exception ex) {
                    value = null;
                }
            }
            if (value != null && value.length() > 0)
                map.put(parameters[i].name, value);
        }

        return map;
    }

}
