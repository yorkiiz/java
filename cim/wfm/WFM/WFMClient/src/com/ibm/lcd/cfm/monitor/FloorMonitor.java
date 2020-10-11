package com.ibm.lcd.cfm.monitor;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JFrame;

import com.ibm.lcd.cfm.monitor.config.MonitorConfig;
import com.ibm.lcd.cfm.monitor.status.MonitorPanel;
import com.ibm.lcd.cfm.monitor.util.PropertyLoader;
import com.ibm.lcd.cfm.monitor.util.URLRequester;

public final class FloorMonitor implements WindowListener {

	private final static String CONFIG_FILE = "/FloorMonitor.config";
	public MonitorPanel monitorPanel = null;

	public final void windowOpened(WindowEvent e) {

		if (monitorPanel != null)
			monitorPanel.start();
	}

	public final void windowClosing(WindowEvent e) {

		if (monitorPanel != null)
			monitorPanel.disposeAll();
		try {
			logoff();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}

	public final void windowClosed(WindowEvent e) {
		System.exit(0);
	}

	public final void windowIconified(WindowEvent e) {
	}

	public final void windowDeiconified(WindowEvent e) {
	}

	public final void windowActivated(WindowEvent e) {
	}

	public final void windowDeactivated(WindowEvent e) {
	}

	public final boolean loadMessageResource(String localeName) {

		Locale locale = null;
		if (localeName != null && localeName.length() > 0) {
			String language = localeName, country = "", variant = "";
			int i = localeName.indexOf('_');
			if (i >= 0) {
				language = localeName.substring(0, i);
				country = localeName.substring(i + 1);
				if ((i = country.indexOf('_')) >= 0) {
					variant = country.substring(i + 1);
					country = country.substring(0, i);
				}
			}
			try {
				locale = new Locale(language, country, variant);
			} catch (Exception ex) {
				locale = null;
			}
		}
		if (locale == null)
			locale = Locale.getDefault();

		return MonitorConfig.loadMessageResource(locale);
	}

	public final Properties loadApplicationConfig(Component component) {

		Properties properties = PropertyLoader.load(this, CONFIG_FILE);

		if (component instanceof Applet) {
			Applet applet = (Applet) component;
			Enumeration enum1 = properties.propertyNames();
			String name = null, value = null;
			while (enum1.hasMoreElements()) {
				name = (String) enum1.nextElement();
				if (!name.startsWith("monitor_"))
					continue;
				if ((value = applet.getParameter(name)) != null)
					properties.setProperty(name, value);
			}
		}

		return properties;
	}

	public final void logoff() throws IOException {

		URLRequester requester = new URLRequester();
		URLConnection conn = null;
		try {
			String str = MonitorConfig.getAbsoluteURI(MonitorConfig.PATH_LOGOFF);
			if (str.length() > 0) {
				conn = requester.request(str);
				conn.connect();
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (conn != null) {
				try {
					conn.getInputStream().close();
				} catch (Exception ex) {
				}
			}
		}
	}

	private JFrame createApplicationFrame() {

		JFrame frame = new JFrame(MonitorConfig.getMessage("frame.title"));
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (screenSize.width * 0.8);
		int height = (int) (screenSize.height * 0.8);

		frame.setSize(width, height);
		frame.setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);

		return frame;
	}

	public final static void main(String[] args) {

		FloorMonitor floorMonitor = new FloorMonitor();
		if (!floorMonitor.loadMessageResource((args.length > 0) ? args[0] : null)) {
			return;
		}

		JFrame frame = floorMonitor.createApplicationFrame();
		frame.addWindowListener(floorMonitor);

		Properties config = floorMonitor.loadApplicationConfig(null);
		MonitorConfig.initConfig(config);
		MonitorConfig.initAlertConfig(frame, config);
		MonitorConfig.initWipConfig(frame, config);
		MonitorConfig.initSummaryConfig(config);
		if (!MonitorConfig.initApplicationImage(frame)) {
			return;
		}

		floorMonitor.monitorPanel = new MonitorPanel(frame);
		frame.getContentPane().add(floorMonitor.monitorPanel, BorderLayout.CENTER);
		frame.setVisible(true);
		
		floorMonitor.monitorPanel.initScrollText();
	}


}
