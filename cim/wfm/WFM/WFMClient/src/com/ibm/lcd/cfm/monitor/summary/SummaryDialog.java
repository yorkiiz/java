
package com.ibm.lcd.cfm.monitor.summary;

import javax.swing.JDialog;

import com.ibm.lcd.cfm.monitor.config.*;

public class SummaryDialog extends JDialog {


	private static final long serialVersionUID = -4365645521806766535L;

    private SummaryPanel summaryPanel = null;
    public SummaryDialog(SummaryConfig config) {
        super();
        if (config != null) {
            setTitle(MonitorConfig.getMessage(config.titleKey));
            summaryPanel = new SummaryPanel(config);
            getContentPane().add(summaryPanel);
        }
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public final void setInterval(long interval) {
        if (summaryPanel != null)
            summaryPanel.setInterval(interval);
    }

    public final void clear() {
        if (summaryPanel != null)
            summaryPanel.clear();
    }

    public final void start() {
        if (summaryPanel != null)
            summaryPanel.start();
    }

    public final void restart(boolean refreshFlag) {
        if (summaryPanel != null)
            summaryPanel.restart(refreshFlag);
    }

    public final void stop() {
        if (summaryPanel != null)
            summaryPanel.stop();
    }

}
