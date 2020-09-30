
package com.ibm.lcd.cfm.monitor;

import java.awt.BorderLayout;
import java.util.Properties;

import javax.swing.JApplet;

import com.ibm.lcd.cfm.monitor.config.MonitorConfig;
import com.ibm.lcd.cfm.monitor.status.MonitorPanel;
import com.ibm.lcd.cfm.monitor.util.CSVReceiver;

public final class FloorMonitorApplet extends JApplet {
	private static final long serialVersionUID = -8129868277238813155L;

    private FloorMonitor floorMonitor = new FloorMonitor();

    public final void init() {

        if (!floorMonitor.loadMessageResource(getParameter(MonitorConfig.LANGUAGE))) {
            return;
        }

        Properties config = floorMonitor.loadApplicationConfig(this);
        MonitorConfig.initConfig(config);
        MonitorConfig.initAlertConfig(this, config);
        MonitorConfig.initWipConfig(this, config);
        MonitorConfig.initSummaryConfig(config);
        if (!MonitorConfig.initApplicationImage(this)) {
            return;
        }

        CSVReceiver.setCookie(getParameter(MonitorConfig.COOKIE));

        floorMonitor.monitorPanel = new MonitorPanel(this);
        getContentPane().add(floorMonitor.monitorPanel, BorderLayout.CENTER);
    }

    public final void start() {

        if (floorMonitor.monitorPanel != null)
            floorMonitor.monitorPanel.start();

        floorMonitor.monitorPanel.initScrollText();
    }

    public final void stop() {
        cleanup();
    }

    public final void destroy() {
        cleanup();
    }

    private void cleanup() {
        if (floorMonitor.monitorPanel != null)
            floorMonitor.monitorPanel.disposeAll();
    }
}
