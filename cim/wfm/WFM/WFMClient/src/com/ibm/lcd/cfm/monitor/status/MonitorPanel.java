//*********************************************************************************
//
// SYSTEM        : IBM MES system
//
// PROGRAM NAME  : MonitorPanel.java
//
// Outline       :
//
// (c) Copyright 2005, International Business Machines Corp
//
// Modification history:
//
// DATE        LEVEL  NAME             COMMENT
// ----------  -----  ---------------  --------------------------------------------
// 2005/08/01  A0.00  IBM              Initial Release
// 2014/03/14  C0.00  Nico Cai         Add loaded carrier info on port
// 2014/09/02  WFM007 Li Kai           get eqpt Color by sub state
//*********************************************************************************
package com.ibm.lcd.cfm.monitor.status;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
//import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane; //import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ProgressMonitor;
import javax.swing.ProgressMonitorInputStream;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

import com.ibm.lcd.cfm.monitor.config.LinkConfig;
import com.ibm.lcd.cfm.monitor.config.MonitorConfig;
//import com.ibm.lcd.cfm.monitor.config.MonitorListConfig;
import com.ibm.lcd.cfm.monitor.config.SummaryConfig;
import com.ibm.lcd.cfm.monitor.layout.AreaLayer;
import com.ibm.lcd.cfm.monitor.layout.BaseLayer;
import com.ibm.lcd.cfm.monitor.layout.ColorDefinition;
import com.ibm.lcd.cfm.monitor.layout.EquipmentLayer;
import com.ibm.lcd.cfm.monitor.layout.EquipmentState;
import com.ibm.lcd.cfm.monitor.layout.ImageDefinition;
import com.ibm.lcd.cfm.monitor.layout.LayoutLoader;
import com.ibm.lcd.cfm.monitor.layout.LayoutType;
import com.ibm.lcd.cfm.monitor.layout.MonitorLayout;
import com.ibm.lcd.cfm.monitor.layout.PortLayer;
import com.ibm.lcd.cfm.monitor.layout.PortState;
import com.ibm.lcd.cfm.monitor.layout.StockerLayer;
import com.ibm.lcd.cfm.monitor.layout.UnitLayer;
import com.ibm.lcd.cfm.monitor.summary.SummaryDialog;
import com.ibm.lcd.cfm.monitor.util.CSVReceiver;
import com.ibm.lcd.cfm.monitor.util.GenericTable;
import com.ibm.lcd.cfm.monitor.util.LabelRunner;
import com.ibm.lcd.cfm.monitor.util.LabeledTable;
import com.ibm.lcd.cfm.monitor.util.MultiColorFilter;
import com.ibm.lcd.cfm.monitor.util.NumberUtil;
import com.ibm.lcd.cfm.monitor.util.StringUtil;
import com.ibm.lcd.cfm.monitor.util.TextStringItem;
import com.ibm.lcd.cfm.monitor.util.ThreadPanel;
import com.ibm.lcd.cfm.monitor.util.URLRequester;

public final class MonitorPanel extends ThreadPanel implements ActionListener, ItemListener, Runnable {
	private static final long serialVersionUID = -1967582343560697274L;

	final String CLASS_NAME = "MonitorPanel";

	private final static String CMD_LOGOFF 			= "0";
	private final static String CMD_VIEW_TOOLBAR 	= "1";
	private final static String CMD_VIEW_REFRESH 	= "2";
	private final static String CMD_LEGEND 			= "3";
	private final static String CMD_AUTO_SCALE 		= "4";
	private final static String CMD_SCALE_UP 		= "5";
	private final static String CMD_SCALE_ACTUAL 	= "6";
	private final static String CMD_SCALE_DOWN 		= "7";
	private final static String CMD_DEVICE 			= "8";
	private final static String CMD_PREFERENCE 		= "A";
	private final static String CMD_STAT 			= "S";
	private final static String CMD_STAT_ALERT 		= CMD_STAT + "0";
	private final static String CMD_STAT_SHELF 		= CMD_STAT + "1";
	private final static String CMD_STAT_WIP 		= CMD_STAT + "2";
	private final static String CMD_STAT_WIPNUM 	= CMD_STAT + "3";
	private final static String CMD_STAT_ABNORMAL 	= CMD_STAT + "4";
	private final static String CMD_STAT_ASSIGN 	= CMD_STAT + "5";
	private final static String CMD_STAT_ASSIGN_LD 	= CMD_STAT + "6";
	private final static String CMD_STAT_ASSIGN_UL 	= CMD_STAT + "7";
	//private final static String CMD_STAT_E84 = CMD_STAT + "8";
	private final static String CMD_STAT_CHAMBER 	= CMD_STAT + "9";

	// ------------------------------------------------------ Constant Values
	private final static Color invisibleColor = new Color(0, true);

	// ------------------------------------------------------ Private Members
	private String eqptStatusUri = MonitorConfig.getAbsoluteURI(MonitorConfig.PATH_STATUS);
	//private String trackingE84Uri = MonitorConfig.getAbsoluteURI(MonitorConfig.PATH_E84);
	private String marqueeTextUriUri = MonitorConfig.getAbsoluteURI(MonitorConfig.MARQUEE_TEXT);

	private final int ALRT_LVL_OVERIDLETIME = 8;
	private final int ALRT_LVL_HIGH 		= 3;
	private final int ALRT_LVL_MIDDLE 		= 2;
	private final int ALRT_LVL_LOW 			= 1;
	private final int ALRT_LVL_C 			= 4;
	private final int ALRT_LVL_NORMAL       = 0;

	//private String wipList1 = MonitorConfig.getAbsoluteURI(MonitorConfig.PATH_WIPLIST1);
	//private String shelfList = MonitorConfig.getAbsoluteURI(MonitorConfig.PATH_SHELFLIST);
	//private String bufferStockList = MonitorConfig.getAbsoluteURI(MonitorConfig.PATH_BUFFERSTOCKLIST);

	private Timer timer1 = null;
	private boolean blinkFlg = false;

	private boolean itemStateChangedFlg = false;
	
	private Timer m_marqueeTextTimer = null;

	private CSVReceiver m_receiver = null;
	private URLRequester m_marqueeTextRequester = null;	
	private LabelRunner titleLabelRunner = null;
	private AreaLayer currentLayer = null;
	private boolean errorFlag = false;
	private boolean imageLoaded = false;
	private boolean statusLoaded = false;
	private MonitorLayout m_layoutFileFlg = null;
	private long monitorInterval = 0;
	private int scrollingSpeed = 0;

	private boolean statAbnormal = false;
	private boolean statAssign = false;
	private boolean statAssignLd = false;
	private boolean statAssignUl = false;
	private boolean statAlert = true;
	private boolean statShelf = false;
	//private boolean statE84 = true;
	private boolean statWip = false;
	private boolean statWipNum = true;
	private boolean statChamber = false;

	public static boolean statDevice = true; /* L2.3 */
//	public static boolean statLegendF = false; /* L2.3 */
	public static boolean refreshFlg = false; /* L2.3 */
	public static boolean logonFlg = true; /* L2.3 */

	private SummaryInfo[] summaryInfo = null;
	private HashMap m_eqpMap = null;

	// ----------------------------------------------------------- Components
	private MonitorPanel thisPanel = null;
	private StatusPanel statusPanel = null;
	private JPanel m_centerP = null;
	// tw private TransferPanel transferPanel = null;
	private JPanel m_toolBarP = null;
	private JToolBar toolBar = null;
	private JLabel titleLabel = null;
	private JLabel statusLabel = null;
	private ScrollingTextPanel scrollTextP;
	private ScrollingTextPanel scrollText2P;
	private JComponent m_legendComponent = null;
	// private JComponent deviceComponent = null;
	// private JComponent deviceComponentBk= null;
	private JComboBox areaComboBox = null;
	// private JComboBox eqptComboBox = null;
	private ProgressMonitor progressMonitor = null;
	private Timer waitTimer = null;
	private InputListener inputListener = null;
	
	// tw private JSplitPane splitPane = null;
	
	private Container m_parent;

	public MonitorPanel(Container parent) {
		super();
		
		m_parent = parent;
		
		thisPanel = this;
		
		monitorInterval = MonitorConfig.getAsLong(MonitorConfig.INTERVAL, 10000);
		scrollingSpeed = MonitorConfig.getAsInteger(MonitorConfig.SCROLLING_SPEED, 1);
		
		// Load the layout.
		ProgressMonitorInputStream is = null;
		try {
			LayoutLoader loader = new LayoutLoader();
			String layoutFile = MonitorConfig.getAbsoluteURI(MonitorConfig.LAYOUT_URL, MonitorConfig.LAYOUT_XML);

			//System.out.println("layoutFile: " + layoutFile);

			if (layoutFile != null && layoutFile.length() > 0) 
			{
				URL url = new URL(layoutFile);
				is = new ProgressMonitorInputStream(null, MonitorConfig.getMessage("progress.layout"), url.openStream());
				is.getProgressMonitor().setMillisToDecideToPopup(500);
				m_layoutFileFlg = loader.load(is);
				
				makeUpEqpMap();
			} else {
				// System.out.println(MonitorConfig.getMessage("error.layout_not_defined"));
			}
		} catch (InterruptedIOException ex) {
			// Loading is canceled.
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		// Initialize this panel.
		calculateShape();
		m_toolBarP = new JPanel(new BorderLayout());
		initPanel(parent);
		initMenu(parent);
		initToolBar();

		// Prepare the progress monitor.
		int i = (m_layoutFileFlg != null) ? m_layoutFileFlg.getImageDefinitionCount() : 0;
		if (i > 0) {
			progressMonitor = new ProgressMonitor(this, MonitorConfig.getMessage("progress.message"), "", 0, i + 1);
			progressMonitor.setMillisToDecideToPopup(1000);
		}

		// Initialize the wait timer.
		if ((i = MonitorConfig.getAsInteger(MonitorConfig.WAIT_TIME, 0)) > 0) {
			waitTimer = new Timer(i, new WaitTimerListener());
			waitTimer.setRepeats(false);
			waitTimer.setCoalesce(true);
			waitTimer.start();

			inputListener = new InputListener();
			inputListener.regist(parent);
		}
	}
	
	private void makeUpEqpMap(){
		StringBuffer eqptListSB = new StringBuffer();

		for (int areaidcnt = 0; areaidcnt < m_layoutFileFlg.getAreaCount(); areaidcnt++) {

			AreaLayer coverlayer = m_layoutFileFlg.getArea(areaidcnt);

			for (int i = 0; i < coverlayer.getEquipmentCount(); i++) {
				String eqptid = coverlayer.getEquipment(i).getId();

				if (areaidcnt != 0 || i != 0) {
					eqptListSB.append(",");
				}
				eqptListSB.append(eqptid);
			}
		}
		m_eqpMap = new HashMap();
		m_eqpMap.put("eqpt_id", eqptListSB.toString());
		
		eqptListSB = null;
	}

	private void initMenu(Container parent) {

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = null;
		JMenuItem menuItem = null;
		//int i = 0, j = 0;

		// ----- File menu.
		if (parent instanceof Applet) {
			menu = new JMenu(MonitorConfig.getMessage("menu.file"));
			menu.setMnemonic(KeyEvent.VK_F);
			menuBar.add(menu);

			menuItem = new JMenuItem(MonitorConfig.getMessage("menu.file.logoff"), KeyEvent.VK_L);
			menuItem.setActionCommand(CMD_LOGOFF);
			menuItem.addActionListener(this);
			menu.add(menuItem);
		}

//		// ----- Monitor menu.
//		MonitorListConfig[] monitors = MonitorConfig.getMonitorListConfig();
//		if (monitors != null && monitors.length > 0) {
//			menu = new JMenu(MonitorConfig.getMessage("menu.monitor"));
//
//			menu.setMnemonic(KeyEvent.VK_M);
//			menuBar.add(menu);
//			String target = MonitorConfig.get(MonitorConfig.LINK_TARGET, null);
//
//			for (i = 0, j = monitors.length; i < j; i++) {
//				menuItem = new JMenuItem(MonitorConfig.getMessage(monitors[i].titleKey));
//
//				menuItem.addActionListener(new MonitorListActionListener(parent, monitors[i].uri, target));
//				menu.add(menuItem);
//			}
//		}

		// ----- View menu.
		menu = new JMenu(MonitorConfig.getMessage("menu.view"));
		menu.setMnemonic(KeyEvent.VK_V);
		menuBar.add(menu);

		menuItem = new JMenuItem("Preference");
		menuItem.setActionCommand(CMD_PREFERENCE);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JCheckBoxMenuItem(MonitorConfig.getMessage("menu.view.toolbar"), true);
		menuItem.setActionCommand(CMD_VIEW_TOOLBAR);
		menuItem.addItemListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem(MonitorConfig.getMessage("menu.view.refresh"), KeyEvent.VK_R);
		menuItem.setActionCommand(CMD_VIEW_REFRESH);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		menuItem.addActionListener(this);
		menu.add(menuItem);

		/*
		menu = new JMenu(LinkConfig.EQPT_INFO_LINK);
		menu.setMnemonic(KeyEvent.VK_L);
		menuBar.add(menu);

		if (LinkConfig.LINK_ACTION_KEY.length > 0 && LinkConfig.LINK_NAME.length > 0 && LinkConfig.LINK_PATH.length > 0
				&& LinkConfig.LINK_ACTION_KEY.length == LinkConfig.LINK_NAME.length
				&& LinkConfig.LINK_NAME.length == LinkConfig.LINK_PATH.length) {

			for (int cnt = 0; cnt < LinkConfig.LINK_ACTION_KEY.length; cnt++) {
				menuItem = new JMenuItem(LinkConfig.LINK_NAME[cnt], KeyEvent.VK_L);
				menuItem.setActionCommand(LinkConfig.LINK_ACTION_KEY[cnt]);
				menuItem.addActionListener(this);
				menu.add(menuItem);
			}
		}
		*/
		menu = new JMenu(LinkConfig.ALARM_INFO_LINK);
		menu.setMnemonic(KeyEvent.VK_L);
		menuBar.add(menu);

		if (LinkConfig.ALARM_LINK_ACTION_KEY.length > 0 && LinkConfig.ALARM_NAME.length > 0
				&& LinkConfig.ALARM_LINK_PATH.length > 0
				&& LinkConfig.ALARM_LINK_ACTION_KEY.length == LinkConfig.ALARM_NAME.length
				&& LinkConfig.ALARM_NAME.length == LinkConfig.ALARM_LINK_PATH.length) {

			for (int cnt = 0; cnt < LinkConfig.ALARM_LINK_ACTION_KEY.length; cnt++) {
				menuItem = new JMenuItem(LinkConfig.ALARM_NAME[cnt], KeyEvent.VK_L);
				menuItem.setActionCommand(LinkConfig.ALARM_LINK_ACTION_KEY[cnt]);
				menuItem.addActionListener(this);
				menu.add(menuItem);
			}
		}

//		menu = new JMenu(LinkConfig.MASK_INFO_LINK);
//		menu.setMnemonic(KeyEvent.VK_L);
//
//		if (LinkConfig.MASK_LINK_ACTION_KEY.length > 0 && LinkConfig.MASK_NAME.length > 0
//				&& LinkConfig.MASK_LINK_PATH.length > 0
//				&& LinkConfig.MASK_LINK_ACTION_KEY.length == LinkConfig.MASK_NAME.length
//				&& LinkConfig.MASK_NAME.length == LinkConfig.MASK_LINK_PATH.length) {
//
//			for (int cnt = 0; cnt < LinkConfig.MASK_LINK_ACTION_KEY.length; cnt++) {
//				menuItem = new JMenuItem(LinkConfig.MASK_NAME[cnt], KeyEvent.VK_L);
//				menuItem.setActionCommand(LinkConfig.MASK_LINK_ACTION_KEY[cnt]);
//				menuItem.addActionListener(this);
//				menu.add(menuItem);
//			}
//		}
		/*
		 * UIManager.LookAndFeelInfo[] info =
		 * UIManager.getInstalledLookAndFeels();
		 * 
		 * j = info.length; if (j > 1) { LookAndFeel laf =
		 * UIManager.getLookAndFeel(); String defLaf = (laf != null) ?
		 * laf.getName() : ""; menu = new
		 * JMenu(MonitorConfig.getMessage("menu.lookandfeel"));
		 * menu.setMnemonic(KeyEvent.VK_L); ButtonGroup buttonGroup = new
		 * ButtonGroup();
		 * 
		 * for (i = 0; i < j; i++) { menuItem = new
		 * JRadioButtonMenuItem(info[i].getName());
		 * menuItem.addActionListener(new
		 * ChangeLookAndFeelAction(info[i].getClassName()));
		 * 
		 * if (defLaf.equals(info[i].getName())) menuItem.setSelected(true);
		 * menu.add(menuItem); buttonGroup.add(menuItem); } menuBar.add(menu); }
		 */

		if (parent instanceof JApplet)
			((JApplet) parent).setJMenuBar(menuBar);
		else if (parent instanceof JFrame)
			((JFrame) parent).setJMenuBar(menuBar);
	}


	private void initPanel(Container parent) {
		setLayout(new BorderLayout());

		// Calculate splitter location.
		Dimension parentSize = parent.getSize();
		int horizontalLocation = 0;

		if (logonFlg) {
			logonFlg = false;
		}
		horizontalLocation = (int) ((double) parentSize.width * 1);

		// Create each internal panel.
		// transferPanel = new TransferPanel();
		// transferPanel.setMonitorLayout(layout);
		// tw statusPanel = new StatusPanel(transferPanel);
		statusPanel = new StatusPanel();
		statusPanel.setMonitorLayout(m_layoutFileFlg);

		// tw splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		// tw statusPanel.setSplitPanel(splitPane);
		statusPanel.setHorizontalLocation(horizontalLocation);

		// Set the monitor interval to each panel.
		// transferPanel.setInterval(monitorInterval);
		setInterval(monitorInterval);

		// Construct status panel.
		titleLabel = new JLabel(MonitorConfig.getMessage("status.title"));
		titleLabel.setVerticalAlignment(JLabel.CENTER);
		titleLabel.setBorder(BorderFactory.createRaisedBevelBorder());
		titleLabel.setIcon(MonitorConfig.getIcon(MonitorConfig.IMG_STAT_OFF));
		titleLabelRunner = new LabelRunner(titleLabel);

		JScrollPane scrollPane = new JScrollPane(statusPanel);
		JPanel panel = new JPanel(new BorderLayout());
		m_centerP = panel;
		//panel.add(titleLabel, BorderLayout.NORTH);
		m_toolBarP.add(titleLabel, BorderLayout.SOUTH);
		panel.add(scrollPane, BorderLayout.CENTER);

		// Construct splitter pane.
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		scrollPane.getHorizontalScrollBar().setUnitIncrement(20);

		// splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel,
		// transferPanel);
		// tw splitPane.setLeftComponent(panel);
		// tw splitPane.setRightComponent(transferPanel);
		// tw splitPane.setDividerLocation(horizontalLocation);// N99
		// tw splitPane.setOneTouchExpandable(true);

		// Create status label.
		statusLabel = new JLabel();
		statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());

		// Appends components to the content pane.
		// tw. add(splitPane, BorderLayout.CENTER);
		add(panel, BorderLayout.CENTER);
		
		JPanel footerP = new JPanel(new GridLayout(2, 1));
		scrollTextP = new ScrollingTextPanel(scrollingSpeed); 
		//scrollTextP.setSize(800, 30);
		scrollTextP.setBorder(BorderFactory.createEtchedBorder());
		footerP.add(scrollTextP);
		footerP.add(statusLabel);
		add(footerP, BorderLayout.SOUTH);
		
//		JPanel footer2P = new JPanel(new GridLayout(2, 1));
//		scrollText2P = new ScrollingTextPanel(scrollingSpeed); 
//		//scrollTextP.setSize(800, 30);
//		scrollText2P.setBorder(BorderFactory.createEtchedBorder());
//		footer2P.add(scrollText2P);
//		footer2P.add(statusLabel);
//		add(footer2P, BorderLayout.SOUTH);


		
		setStatusText(MonitorConfig.getMessage("statusbar.text.initialize"), Color.blue);
	}

	/**
	 * Initializes the tool bar.
	 * 
	 */
	private void initToolBar() {
		toolBar = new JToolBar();
		toolBar.setFloatable(false);

		// Create area combo box.
		areaComboBox = new JComboBox();
		if (m_layoutFileFlg != null) {
			AreaLayer area = null;
			TextStringItem item = null;
			int cnt = m_layoutFileFlg.getAreaCount();
			
			for (int i = 0; i < cnt; i++) {
				area = m_layoutFileFlg.getArea(i);
				item = new TextStringItem(area.toString(), area.getId());
				areaComboBox.addItem(item);
			}
			
			if (cnt > 0) {
				areaComboBox.setSelectedIndex(0);
				currentLayer = m_layoutFileFlg.getArea(0);
				statusPanel.setLayer(currentLayer);
			}
		}

		areaComboBox.addItemListener(new ChangeAreaListener());
		areaComboBox.setMinimumSize(new Dimension(100, 30));
		areaComboBox.setMaximumSize(new Dimension(250, 30));
		toolBar.add(new JLabel(MonitorConfig.getMessage("toolbar.area") + " "));
		toolBar.add(areaComboBox);
		toolBar.addSeparator();

		// Create status toggle buttons.
		toolBar.add(makeToggle(MonitorConfig.IMG_ALERT, "toolbar.alert", CMD_STAT_ALERT, statAlert));
		// L3.3 toolBar.add(makeToggle( MonitorConfig.IMG_LONG_TIME_ALERT,
		// "toolbar.long.time.alert", CMD_STAT_LONG_TIME_ALERT,
		// statAlertLongTime));
		// T.01 toolBar.add(makeToggle(MonitorConfig.IMG_SHELF, "toolbar.shelf",
		// CMD_STAT_SHELF, statShelf)); /* L1.8 *//* L3.3 */
		toolBar.add(makeToggle(MonitorConfig.IMG_WIPNUM, "toolbar.wip.number", CMD_STAT_WIPNUM, statWipNum)); /* L1.1 */
		// T.02 toolBar.add(makeToggle(MonitorConfig.IMG_ABNORMAL,
		// "toolbar.abnormal", CMD_STAT_ABNORMAL, statAbnormal));
		// T.03
		// toolBar.add(makeToggle(MonitorConfig.IMG_ASSIGN_LD,"toolbar.assign.load",
		// CMD_STAT_ASSIGN_LD,statAssignLd));
		// T.04
		// toolBar.add(makeToggle(MonitorConfig.IMG_ASSIGN_UL,"toolbar.assign.unload",CMD_STAT_ASSIGN_UL,statAssignUl));
		// T.05 toolBar.add(makeToggle(MonitorConfig.IMG_CHAMBER,
		// "toolbar.chamber", CMD_STAT_CHAMBER, statChamber));
		/*
		 * toolBar.add(makeToggle(MonitorConfig.IMG_ASSIGN, "toolbar.assign",
		 * CMD_STAT_ASSIGN, statAssign)); L1.2
		 */
		// toolBar.add(makeToggle(MonitorConfig.IMG_E84, "toolbar.e84",
		// CMD_STAT_E84, statE84)); /* L1.9 */
		// toolBar.add(makeToggle(MonitorConfig.IMG_WIP, "toolbar.wip",
		// CMD_STAT_WIP, statWip));
		toolBar.addSeparator();

		// N0.05 toolBar.add(makeToggle(null, "toolbar.device", CMD_DEVICE,
		// statDevice));

		// Create legend toggle button.
		toolBar.add(makeToggle(null, "toolbar.legend", CMD_LEGEND, false));
		toolBar.add(makeToggle(null, "toolbar.auto.scale", CMD_AUTO_SCALE, false));
		toolBar.addSeparator();

		// Create scale up button.
		toolBar.add(makeButton(MonitorConfig.IMG_ZOOM_IN, "toolbar.scale.up", CMD_SCALE_UP));
		// Create actual scale button.
		toolBar.add(makeButton(MonitorConfig.IMG_ORIGINAL_SIZE, "toolbar.scale.actual", CMD_SCALE_ACTUAL));
		// Create scale down button.
		toolBar.add(makeButton(MonitorConfig.IMG_ZOOM_OUT, "toolbar.scale.down", CMD_SCALE_DOWN));
		
		m_toolBarP.add(toolBar, BorderLayout.NORTH);
		add(m_toolBarP, BorderLayout.NORTH);
	}

//	private JButton makeButton(String msgKey, String cmd) {
//		final String METHOD_NAME = "makeButton";
//		LogUtil.TRACE(true, CLASS_NAME, METHOD_NAME, "START");
//
//		JButton button = new JButton(MonitorConfig.getMessage(msgKey));
//		button.setActionCommand(cmd);
//		button.addActionListener(this);
//
//		LogUtil.TRACE(true, CLASS_NAME, METHOD_NAME, "END");
//		return button;
//	}

	private JButton makeButton(String imgKey, String msgKey, String cmd) {

		Icon icon = (imgKey != null) ? MonitorConfig.getIcon(imgKey) : null;
		
		JButton button = null;
		if(icon != null)
			button = new JButton(icon);
		else
			button = new JButton(MonitorConfig.getMessage(msgKey));
		button.setToolTipText(MonitorConfig.getMessage(msgKey));
		button.setActionCommand(cmd);
		button.addActionListener(this);

		return button;
	}	
	
	private JToggleButton makeToggle(String imgKey, String msgKey, String cmd, boolean statFlag) {

		Icon icon = (imgKey != null) ? MonitorConfig.getIcon(imgKey) : null;
		String msg = MonitorConfig.getMessage(msgKey);
		JToggleButton toggle = null;
		if (icon != null) {
			toggle = new JToggleButton(icon, statFlag);
		} else {
			toggle = new JToggleButton(msg, statFlag);
		}
		toggle.setToolTipText(msg);
		toggle.setActionCommand(cmd);
		toggle.addItemListener(this);

		return toggle;
	}

	private void calculateShape() {

		if (m_layoutFileFlg == null)
			return;

		AreaLayer area = null;
		EquipmentLayer equipment = null;
		Rectangle rect = null;
		int i, j, k;
		int areaCnt, equipmentCnt, cnt;
		
		for (i = 0, areaCnt = m_layoutFileFlg.getAreaCount(); i < areaCnt; i++) {
			area = m_layoutFileFlg.getArea(i);
			if (area.shape != null)
				continue;
			rect = new Rectangle();
			for (j = 0, equipmentCnt = area.getEquipmentCount(); j < equipmentCnt; j++) {
				equipment = area.getEquipment(j);
				rect = getMaxBounds(rect, equipment.shape);
				for (k = 0, cnt = equipment.getUnitCount(); k < cnt; k++)
					rect = getMaxBounds(rect, equipment.getUnit(k).shape);
				for (k = 0, cnt = equipment.getPortCount(); k < cnt; k++)
					rect = getMaxBounds(rect, equipment.getPort(k).shape);
			}
			area.shape = rect;
		}
	}

	private Rectangle getMaxBounds(Rectangle rect, Shape shape) {
		// final String METHOD_NAME = "getMaxBounds";
		// LogUtil.TRACE(true, CLASS_NAME, METHOD_NAME, "START");

		if (rect == null)
			rect = new Rectangle();
		if (shape != null) {
			Rectangle shapeRect = shape.getBounds();
			if (!rect.contains(shapeRect)) {
				int w = (rect.getMaxX() > shapeRect.getMaxX()) ? rect.width : shapeRect.x + shapeRect.width;
				int h = (rect.getMaxY() > shapeRect.getMaxY()) ? rect.height : shapeRect.y + shapeRect.height;
				rect.setSize(w, h);
			}
		}
		// LogUtil.TRACE(true, CLASS_NAME, METHOD_NAME, "END");
		return rect;
	}

	// ------------------------------------------------------- Public Methods

	public final Container getTopLevelContainer() {

		Container container = getTopLevelAncestor();
		if (container == null)
			container = this;

		return container;
	}

	public final void disposeAll() {
		blinkFlg = false;
//		if (waitTimer != null)
//			waitTimer.stop();
		stopAll();
		if (summaryInfo != null) {
			SummaryDialog dialog = null;
			for (int i = 0, j = summaryInfo.length; i < j; i++) {
				if ((dialog = summaryInfo[i].dialog) != null)
					dialog.dispose();
			}
		}

		m_legendComponent = null;
	}

	public final void setStatusText(String text, Color color) {

		LabelRunner runner = new LabelRunner(statusLabel);
		runner.setText(text);
		runner.setForeground(color);
		SwingUtilities.invokeLater(runner);

	}

	public final void startAll() {
		blinkFlg = false;
		restartAll(true);
	}

	public final void restartAll(boolean refreshFlag) {
		blinkFlg = false;
		if (summaryInfo != null) {
			SummaryDialog dialog = null;
			for (int i = 0, j = summaryInfo.length; i < j; i++) {
				if ((dialog = summaryInfo[i].dialog) != null)
					dialog.restart(refreshFlag);
			}
		}
		/*
		 * tw if (transferPanel != null) transferPanel.restart(refreshFlag);
		 */
		restart(refreshFlag);

	}

	public final void stopAll() {
		blinkFlg = false;
		if (summaryInfo != null) {
			SummaryDialog dialog = null;
			for (int i = 0, j = summaryInfo.length; i < j; i++) {
				if ((dialog = summaryInfo[i].dialog) != null)
					dialog.stop();
			}
		}
		/*
		 * tw if (transferPanel != null) transferPanel.stop();
		 */
		stop();
	}

	public final void actionPerformed(ActionEvent e) {

		Container container = getTopLevelContainer();
		String cmd = e.getActionCommand();

		// ----- (Empty Command)
		if (cmd == null || cmd.length() <= 0) {
			// Do nothing
		} else if (CMD_LOGOFF.equals(cmd)) {
			if (container instanceof Applet) {
				try {
					URL url = new URL(MonitorConfig.getAbsoluteURI(MonitorConfig.PATH_LOGOFF));
					disposeAll();
					((Applet) container).getAppletContext().showDocument(url, "_top");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(container, ex.toString(), MonitorConfig.getMessage("error.title"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (CMD_VIEW_REFRESH.equals(cmd)) {
			container.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			stopAll();
			restartAll(true);
			container.setCursor(null);

		} else if (CMD_SCALE_UP.equals(cmd)) {
			double rate = statusPanel.getEnlargeRate();//fix can zoom out bug
			if (rate < 5) {
				statusPanel.setEnlargeRate(rate + 0.1);
				statusPanel.repaint();
			}
		} else if (CMD_SCALE_ACTUAL.equals(cmd)) {
			if (statusPanel.getEnlargeRate() != 1) {
				statusPanel.setEnlargeRate(1);
				statusPanel.repaint();
			}
		} else if (CMD_SCALE_DOWN.equals(cmd)) {
			double rate = statusPanel.getEnlargeRate();//fix can zoom out bug
			if (rate > 0.2) {
				statusPanel.setEnlargeRate(rate - 0.1);
				statusPanel.repaint();
			}
		} else if(CMD_PREFERENCE.equals(cmd)){
			Frame owner = null;
			if( m_parent instanceof JApplet)
				owner = (Frame)SwingUtilities.getAncestorOfClass(Frame.class, m_parent); 
			else
				owner = (JFrame)m_parent;
			
			PreferenceDialog dialog = new PreferenceDialog(owner, true, (int)monitorInterval/1000, scrollingSpeed);
			dialog.setVisible(true);
			 
			 if(!dialog.isOKPressed()){
				 dialog.dispose();
				 return;
			 }else{
				 monitorInterval = dialog.getUpdatedMonitorInterval()*1000;
				 setInterval(monitorInterval);
				 scrollingSpeed = dialog.getUpdatedScrollingSpeed();
				 scrollTextP.setSpeed(scrollingSpeed);
				 dialog.dispose();
			 }
		}

		for (int linksize = 0; linksize < LinkConfig.ALARM_LINK_ACTION_KEY.length; linksize++) {
			if (LinkConfig.ALARM_LINK_ACTION_KEY[linksize].equals(cmd)) {
				if (container instanceof Applet) {
					try {
						final String target = LinkConfig.TARGET;

						String doServletPath = "";

						Applet applet = (Applet) container;

						doServletPath = StringUtil.getCheckedValue(URLRequester.getURLString(
								LinkConfig.ALARM_LINK_PATH[linksize], null), "");

						if (doServletPath.length() <= 0) {
							return;
						}
						if (doServletPath.startsWith("/")) {
							String contextPath = MonitorConfig.get(MonitorConfig.CONTEXT_PATH, "");
							if (contextPath.endsWith("/")) {
								doServletPath = contextPath + doServletPath.substring(1);
							} else {
								doServletPath = contextPath + doServletPath;
							}
						}

						URL url = new URL(applet.getDocumentBase(), doServletPath);

						doServletPath = (target != null && target.length() > 0) ? target : LinkConfig.TARGET_BLANK;

						applet.getAppletContext().showDocument(url, target);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(container, ex.toString(),
								MonitorConfig.getMessage("error.title"), JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	public final void itemStateChanged(ItemEvent e) {
		Container container = getTopLevelContainer();
		boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
		String cmd = "";
		Object source = e.getSource();
		if (source instanceof AbstractButton)
			cmd = ((AbstractButton) source).getActionCommand();

		// ----- (Empty Command)
		if (cmd == null || cmd.length() <= 0) {
			// Do nothing
		}
		// ----- ON/OFF Tool Bar
		else if (CMD_VIEW_TOOLBAR.equals(cmd)) {
			if (toolBar != null)
				toolBar.setVisible(selected);
		}
		// ----- ON/OFF Legend
		else if (CMD_LEGEND.equals(cmd)) {
			container.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
			if(m_legendComponent == null){
				
				String legendLocation = MonitorConfig.get(MonitorConfig.MONITOR_LEGEND_LOCATION, "");
				
				if(legendLocation.compareToIgnoreCase(BorderLayout.SOUTH) == 0)
					legendLocation = BorderLayout.SOUTH;
				else if(legendLocation.compareToIgnoreCase(BorderLayout.NORTH) == 0)
					legendLocation = BorderLayout.NORTH;
				else if(legendLocation.compareToIgnoreCase(BorderLayout.EAST) == 0)
					legendLocation = BorderLayout.EAST;
				else if(legendLocation.compareToIgnoreCase(BorderLayout.WEST) == 0)
					legendLocation = BorderLayout.WEST;
				else
					legendLocation = BorderLayout.WEST;
				
				if(legendLocation == BorderLayout.NORTH || legendLocation == BorderLayout.SOUTH)
					m_legendComponent = new LegendPanel(m_layoutFileFlg, LegendPanel.HORIZONTAL_LEGEND);
				else
					m_legendComponent = new LegendPanel(m_layoutFileFlg, LegendPanel.VERTICAL_LEGEND);
				
				m_legendComponent.setVisible(false);
				m_centerP.add(m_legendComponent, legendLocation);
			}
			
			if (selected) {
				m_legendComponent.setVisible(true);
			} else{
				m_legendComponent.setVisible(false);
			}
			
			revalidate();
			container.setCursor(null);
		}
		// ----- ON/OFF Device /* L2.3 */
		else if (CMD_DEVICE.equals(cmd)) {
			container.setCursor(new Cursor(Cursor.WAIT_CURSOR));

			statDevice = selected;

			revalidate();
			container.setCursor(null);
		}
		// ----- Status ON/OFF
		else if (cmd.startsWith(CMD_STAT)) {
			container.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			boolean runFlag = isRunning();
			if (runFlag)
				stopAll();
			if (CMD_STAT_ALERT.equals(cmd))
				statAlert = selected;
			else if (CMD_STAT_SHELF.equals(cmd))
				statShelf = selected;
			else if (CMD_STAT_WIP.equals(cmd)) {
				statWip = selected;
			} else if (CMD_STAT_WIPNUM.equals(cmd)) {
				statWipNum = selected;
			}

			else if (CMD_STAT_ABNORMAL.equals(cmd))
				statAbnormal = selected;
			else if (CMD_STAT_ASSIGN.equals(cmd))
				statAssign = selected;
			else if (CMD_STAT_ASSIGN_LD.equals(cmd))
				statAssignLd = selected;
			else if (CMD_STAT_ASSIGN_UL.equals(cmd))
				statAssignUl = selected;
			else if (CMD_STAT_CHAMBER.equals(cmd))
				statChamber = selected;
			if (runFlag)
				restartAll(true);
			container.setCursor(null);
		}
		// ----- ON/OFF Auto Scale
		else if (CMD_AUTO_SCALE.equals(cmd)) {
			statusPanel.setAutoScale(selected);
			statusPanel.repaint();
		}
		
	}

	protected final boolean beforeRun() {
		if (m_layoutFileFlg == null) {
			setStatusText(MonitorConfig.getMessage("statusbar.text.layout_not_load"), Color.red);
			return false;
		}

		// Load images.
		if (!imageLoaded) {
			ProgressMonitorRunner runner = new ProgressMonitorRunner(progressMonitor);
			try {
				int i = 0, j = m_layoutFileFlg.getImageDefinitionCount();
				MediaTracker tracker = null;
				ImageDefinition imageDef = null;

				for (i = 0; i < j; i++) {
					//doYield();
					imageDef = m_layoutFileFlg.getImageDefinition(i);
					if ((tracker = loadImage(imageDef)) == null)
						continue;

					runner.progress = i + 1;
					runner.note = (imageDef != null) ? imageDef.path : "";
					while (tracker.statusAll(true) == MediaTracker.LOADING) {
						SwingUtilities.invokeAndWait(runner);
						if (runner.cancelFlag)
							break;
						tracker.waitForAll(1000);
					}
					if (runner.cancelFlag)
						break;
					if (tracker.statusAll(true) != MediaTracker.COMPLETE) {
						imageDef.image = null;
						setStatusText(MonitorConfig.getMessage("statusbar.text.image_not_load"), Color.red);
						break;
					}
					imageDef.loaded = true;
				}
				if (i >= j) {
					//progressMonitor = null;
					imageLoaded = true;
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} catch (InvocationTargetException ex) {
				ex.printStackTrace();
			} finally {
				runner.closeFlag = true;
				SwingUtilities.invokeLater(runner);
			}
			if (!imageLoaded)
				return false;
		}

		errorFlag = false;

		if(m_receiver == null) m_receiver = new CSVReceiver();

		setStatusText("Last Refresh Time : "
				+ DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(new Date()), Color.blue);
		return true;
	}

	protected final boolean doRun(Thread thisThread, boolean workerRefresh) throws InterruptedException, InvocationTargetException 
	{

		GenericTable eqptStatusTbl = null;
		//GenericTable[] tbls = new GenericTable[2];

		try {
			titleLabelRunner.setIcon(MonitorConfig.getIcon(MonitorConfig.IMG_STAT_ON));
			SwingUtilities.invokeAndWait(titleLabelRunner);

			if (!statusLoaded || workerRefresh) {
				//wipTbl = m_receiver.receive(wipList1, m_eqpMap);
				eqptStatusTbl = m_receiver.receive(eqptStatusUri);

				//tbls[0] = eqptStatusTbl;
				//tbls[1] = wipTbl;

				updateStatus(eqptStatusTbl);

				if (!statusLoaded)
					statusLoaded = true;
			}

			/** ************************************************************ */
			if (timer1 != null) {
				timer1.stop();
				timer1.removeActionListener(this);
				timer1 = null;
			}
			if (itemStateChangedFlg) {
				itemStateChangedFlg = false;
			}
			blink();
			/** ************************************************************ */

			statusPanel.setPaint(true);
			statusPanel.repaint();

			setStatusText("Last Refresh Time : "
					+ DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(new Date()),
					Color.blue);

			titleLabelRunner.setIcon(MonitorConfig.getIcon(MonitorConfig.IMG_STAT_OK));
			SwingUtilities.invokeAndWait(titleLabelRunner);

			return true;

		} catch (IOException ex) {
			ex.printStackTrace();
			statusPanel.setPaint(true);
			statusPanel.repaint();
			setStatusText(ex.toString(), Color.red);
			errorFlag = true;
		} 
		return true;
	}

	/**
	 * This method will be invoked after the thread is stopped.
	 * 
	 */
	protected final void afterRun() {
		if (errorFlag)
			titleLabelRunner.setIcon(MonitorConfig.getIcon(MonitorConfig.IMG_STAT_NG));
		else
			titleLabelRunner.setIcon(MonitorConfig.getIcon(MonitorConfig.IMG_STAT_OFF));
		SwingUtilities.invokeLater(titleLabelRunner);
	}

	private MediaTracker loadImage(ImageDefinition imageDef) {

		if (m_layoutFileFlg == null)
			return null;

		if (imageDef == null || imageDef.path == null || imageDef.path.length() <= 0 || imageDef.loaded)
			return null;

		URL contextURL = null;
		URL imageURL = null;
		String loadPath = null;
		Applet applet = null;
		Toolkit toolkit = null;

		String contextPath = MonitorConfig.get(MonitorConfig.LAYOUT_URL, "");
		if (contextPath.length() > 0 && !contextPath.endsWith("/"))
			contextPath += "/";

		Container container = getTopLevelContainer();
		if (container instanceof Applet) {
			applet = (Applet) container;
			try {
				contextURL = new URL(contextPath);
				contextPath = "";
			} catch (Exception ex) {
				contextURL = applet.getDocumentBase();
			}
		} else {
			toolkit = Toolkit.getDefaultToolkit();
		}

		MediaTracker mt = new MediaTracker(this);
		if (imageDef.path.startsWith("/"))
			loadPath = contextPath + imageDef.path.substring(1);
		else
			loadPath = contextPath + imageDef.path;

		if (applet != null) {
			imageDef.image = applet.getImage(contextURL, loadPath);
		} else {
			try {
				imageURL = new URL(loadPath);
			} catch (Exception ex) {
				imageURL = null;
			}
			if (imageURL != null)
				imageDef.image = toolkit.getImage(imageURL);
			else
				imageDef.image = toolkit.getImage(loadPath);
		}

		mt.addImage(imageDef.image, 0);

		return mt;
	}

	private void updateStatus(GenericTable tbl) throws InterruptedException {

		//HashMap wipLabelCntMap = new HashMap();

		if (m_layoutFileFlg == null) {
			return;
		}
		LabeledTable statusTbl = new LabeledTable(tbl);
		//LabeledTable wipLabel = new LabeledTable(tbl[1]);
		EquipmentState equipmentState = null;
		PortState portState = null;
		String id = null;

//		for (int i = 0, rows = wipLabel.getRows(); i < rows; i++) {
//			wipLabelCntMap.put(wipLabel.get(i, MonitorConfig.EQPT_ID), wipLabel.get(i, "LOT_ID_CNT"));
//		}

		blinkFlg = false;

		for (int row = 0, rows = statusTbl.getRows(); row < rows; row++) {
			// Equipment status.
			id = (String) statusTbl.get(row, MonitorConfig.EQPT_ID);

			if (id == null || id.length() <= 0)
				continue;
			if ((equipmentState = m_layoutFileFlg.getEquipmentState(id)) == null)
				continue;

			equipmentState.state 	= (String) statusTbl.get(row, "EQPT_STAT");
			if(equipmentState.state != null ) equipmentState.state = equipmentState.state.trim();
			
			//if EQPT_STAT is down ==> blink 
			String targetChar = equipmentState.state;
			equipmentState.statDown = false;
			
				if ("DOWN".equals(targetChar)) {
					equipmentState.statDown = true;
					if (!blinkFlg) {
						blinkFlg = true;
					}
				}
				
			equipmentState.subState = (String) statusTbl.get(row, "EQPT_SUB_STAT");
			equipmentState.mode 	= (String) statusTbl.get(row, "EQPT_MODE");
			equipmentState.alertFlag = (String) statusTbl.get(row, "ALRT_FLG");

			equipmentState.alertD = false;/* L2.8 */
			equipmentState.alertP = false;/* L2.8 */
			equipmentState.alertU = false;/* L2.8 */
			equipmentState.alertM = false;/* L2.8 */
			equipmentState.alertE = false;/* L2.8 */

			equipmentState.alertO = false; // N0.01
			
			equipmentState.alertL = false;/* L2.8 */
			equipmentState.alertH = false;/* L2.8 */
			equipmentState.alertC = false;/* L2.8 */
			equipmentState.alertN = false;/* L2.8 */

			String alrt_type = (String) statusTbl.get(row, "ALRT_TYPE");

			for (int char_alrt = 0; alrt_type != null && char_alrt < alrt_type.length(); char_alrt++) {
				String target_char = String.valueOf(alrt_type.charAt(char_alrt));

				if ("D".equals(target_char)) {
					equipmentState.alertD = true;
					if (!blinkFlg) {
						blinkFlg = true;
					}
				}

				if ("O".equals(target_char)) { // N0.01
					equipmentState.alertO = true; // N0.01
					if (!blinkFlg) {
						blinkFlg = true;
					} // N0.01
				} // N0.01

				if ("H".equals(target_char)) { // N0.02
					// equipmentState.alertLevel = ALRT_LVL_HIGH; //N0.02
					equipmentState.alertH = true; // N0.02
					if (!blinkFlg) {
						blinkFlg = true;
					} // N0.02
				} // N0.02
				if ("M".equals(target_char)) { // N0.02
					// equipmentState.alertLevel = ALRT_LVL_MIDDLE; //N0.02
					equipmentState.alertM = true; // N0.02
					if (!blinkFlg) {
						blinkFlg = true;
					} // N0.02
				} // N0.02
				
				if ("1".equals(target_char)) { // N0.02
					// equipmentState.alertLevel = ALRT_LVL_MIDDLE; //N0.02
					equipmentState.alertL = true; // N0.02
					if (!blinkFlg) {
						blinkFlg = true;
					} // N0.02
				} // N0.02
				
				if ("2".equals(target_char)) { // N0.02
					// equipmentState.alertLevel = ALRT_LVL_MIDDLE; //N0.02
					equipmentState.alertM = true; // N0.02
					if (!blinkFlg) {
						blinkFlg = true;
					} // N0.02
				} // N0.02
				
				if ("3".equals(target_char)) { // N0.02
					// equipmentState.alertLevel = ALRT_LVL_MIDDLE; //N0.02
					equipmentState.alertH = true; // N0.02
					if (!blinkFlg) {
						blinkFlg = true;
					} // N0.02
				} // N0.02
				
				if ("4".equals(target_char)) { // N0.02
					// equipmentState.alertLevel = ALRT_LVL_MIDDLE; //N0.02
					equipmentState.alertC = true; // N0.02
					if (!blinkFlg) {
						blinkFlg = true;
					} // N0.02
				} // N0.02
				
				if ("0".equals(target_char)) { // N0.02
					// equipmentState.alertLevel = ALRT_LVL_MIDDLE; //N0.02
					equipmentState.alertN = true; // N0.02
					if (!blinkFlg) {
						blinkFlg = true;
					} // N0.02
				} // N0.02
				if ("L".equals(target_char)) { // N0.02
					// equipmentState.alertLevel = ALRT_LVL_LOW; //N0.02
					equipmentState.alertL = true; // N0.02
					if (!blinkFlg) {
						blinkFlg = true;
					} // N0.02
				} // N0.02

				// if("P".equals(target_char)){
				// equipmentState.alertP = true;
				// if(!blinkFlg){ blinkFlg = true; }
				// }
				// if("U".equals(target_char)){
				// equipmentState.alertU = true;
				// if(!blinkFlg){ blinkFlg = true; }
				// }
				// if("M".equals(target_char)){
				// equipmentState.alertM = true;
				// if(!blinkFlg){ blinkFlg = true; }
				// }
				// if("E".equals(target_char)){
				// equipmentState.alertE = true;
				// if(!blinkFlg){ blinkFlg = true; }
				// }

			}

			equipmentState.alertLevel = 0;

//			equipmentState.assign 		= (String) statusTbl.get(row, MonitorConfig.CRR_ASGN);
//			equipmentState.assignLoad 	= (String) statusTbl.get(row, MonitorConfig.LD_CRR_ASGN);
//			equipmentState.assignUnload = (String) statusTbl.get(row, MonitorConfig.UL_CRR_ASGN);

//			try {
				equipmentState.wipCount = NumberUtil.parseInt((String) statusTbl.get(row, MonitorConfig.WIP_CNT));
				//equipmentState.inprCount = (String) wipLabelCntMap.get(id);
				int inprCnt = NumberUtil.parseInt( (String) statusTbl.get(row, MonitorConfig.INPR_CNT));
				equipmentState.inprCount = String.valueOf(inprCnt);
//			}
//			catch (Exception ex)
//			{
//				equipmentState.wipCount = 0;
//				equipmentState.inprCount = "0";
//			}

//			try {
//				equipmentState.abnormalCount = Integer.parseInt((String) statusTbl.get(row, MonitorConfig.ABND_CNT));
//			} catch (Exception ex) {
//				equipmentState.abnormalCount = 0;
//			}

			id = (String) statusTbl.get(row, MonitorConfig.PORT_ID);
			if (id == null || id.length() <= 0)
				continue;
			if ((portState = equipmentState.getPort(id)) == null)
				continue;
			portState.state = (String) statusTbl.get(row, MonitorConfig.PORT_STAT);
			portState.crr_id = (String) statusTbl.get(row, MonitorConfig.CRR_ID);	//C0.00
			//doYield();
			
			portState.portDOWN = false;/* L2.8 */
			portState.portFREE = false;/* L2.8 */
			portState.portLDCM = false;/* L2.8 */
			portState.portLDRQ = false;/* L2.8 */
			portState.portUDCM = false;/* L2.8 */
			portState.portUDRQ = false;/* L2.8 */

			String port_stat = (String) statusTbl.get(row, "PORT_STAT");
//			for (int char_stat = 0; port_stat != null && char_stat < port_stat.length(); char_stat++) {
				String target_char = portState.state;

				if ("DOWN".equals(target_char)) {
					portState.portDOWN = true;
					if (!blinkFlg) {
						blinkFlg = true;
					}
				}
				
				if ("FREE".equals(target_char)) {
					portState.portFREE = true;
					if (!blinkFlg) {
						blinkFlg = true;
					}
				}
				
				if ("LDCM".equals(target_char)) {
					portState.portLDCM = true;
					if (!blinkFlg) {
						blinkFlg = true;
					}
				}
				
				if ("LDRQ".equals(target_char)) {
					portState.portLDRQ = true;
					if (!blinkFlg) {
						blinkFlg = true;
					}
				}
				
				if ("UDCM".equals(target_char)) {
					portState.portUDCM = true;
					if (!blinkFlg) {
						blinkFlg = true;
					}
				}
				
				if ("UDRQ".equals(target_char)) {
					portState.portUDRQ = true;
					if (!blinkFlg) {
						blinkFlg = true;
					}
				}
//			}
		}
	}

	private void penetrateEffect(EquipmentLayer layer, BufferedImage offScreen, AreaLayer area) 
	{
		if (layer.shape == null)
			return;
		EquipmentState state = layer.state;
		if (state == null)
			return;
		Rectangle rect = layer.shape.getBounds();
		if (rect.width <= 0 || rect.height <= 0)
			return;

		Graphics g = offScreen.createGraphics();
		Image image = null;

		int chamberX = 0;
		int chamberY = 0;
		if (area.imageId != null && (area.imageId.length() != 0) && layer.getId() != null && (layer.getId().length() != 0)) 
		{
			chamberX = NumberUtil.parseInt(StringUtil.getCheckedValue(MonitorConfig.getMessage("monitor.stat."
					+ area.imageId + ".X." + layer.getId()), "0"));
			chamberY = NumberUtil.parseInt(StringUtil.getCheckedValue(MonitorConfig.getMessage("monitor.stat."
					+ area.imageId + ".Y." + layer.getId()), "0"));
		}

		if (!(layer instanceof UnitLayer) && !(layer instanceof StockerLayer)) 
		//Process Equipment Layer only
		{
			int gap = 0;

			if (statAssignUl && "N".equals(state.assignUnload)) {
				if ((image = MonitorConfig.getImage(MonitorConfig.IMG_ASSIGN_UL)) != null) {
//					try {
						int w = image.getWidth(null);
						g.drawImage(image, rect.x + rect.width - w, rect.y, null);
						gap += 10;
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
				}
			}

			if (statAssignLd && "N".equals(state.assignLoad)) {
				if ((image = MonitorConfig.getImage(MonitorConfig.IMG_ASSIGN_LD)) != null) {
//					try {
						int w = image.getWidth(null);
						g.drawImage(image, rect.x + rect.width - w - gap, rect.y + gap, null);
						gap += 10;
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
				}
			}

			if (statAssign && "N".equals(state.assign)) {
				if ((image = MonitorConfig.getImage(MonitorConfig.IMG_ASSIGN)) != null) {
//					try {
						int w = image.getWidth(null);
						g.drawImage(image, rect.x + rect.width - w - gap, rect.y + gap, null);
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
				}
			}

			if (statWip && state.wipCount > 0) {
				image = MonitorConfig.getImage(MonitorConfig.getWipKey(state.wipCount));
				if (image != null) {
//					try {
						int w = image.getWidth(null);
						int h = image.getHeight(null);
						g.drawImage(image, rect.x + rect.width - w, rect.y + rect.height - h, null);
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
				}
			}

			state.inprCount = StringUtil.getCheckedValue(state.inprCount, "0"); // N0.04
			if (statWipNum && (state.wipCount > 0 || Integer.parseInt(state.inprCount) > 0)) { // N0.04
//				try {
					drawWipCount(g, rect.x + rect.width, rect.y + rect.height, state.wipCount, state.inprCount,
							MonitorConfig.IMG_WIP_OVAL);

//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
			}

			if (state.shelfLabelCnt > 0) {
				image = MonitorConfig.getImage(MonitorConfig.IMG_WIP_OVAL_EQP);
//				try {
					int w = image.getWidth(null) / 2;
					int h = image.getHeight(null) / 2;
					drawShelfCount(g, rect.x + rect.width - w, rect.y + rect.height - h, state.shelfLabelCnt,
							MonitorConfig.IMG_WIP_OVAL_EQP);
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
			}

			if (statChamber && (state.chamberCnt > 0 || state.chamberMaxCnt > 0)) {
				image = MonitorConfig.getImage(MonitorConfig.IMG_CHAMBER);
//				try {
					if (image != null) {
						int w = image.getWidth(null);

						drawChamberCount(g,
								(rect.x - w + chamberX),
								(rect.y + chamberY), state.chamberCnt, state.chamberMaxCnt, "");
					}
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
			}


			// Draw abnormal.
			if (statAbnormal && state.abnormalCount > 0) {
				if ((image = MonitorConfig.getImage(MonitorConfig.IMG_ABNORMAL)) != null) {
//					try {
						int h = image.getHeight(null);
						g.drawImage(image, rect.x, rect.y + rect.height - h, null);
//					} catch (Exception ex) {
//						ex.printStackTrace();
//					}
				}
			}
		} 
		else if (layer instanceof StockerLayer) 
		//Process Stocker Layer only
		{

			// Effects stocker only. /* L1.3 */
			int gap = 10; /* L1.3 */
			// Draw wip count. /* L1.1 */
			if (statWipNum && state.wipCount > 0) {
//				try {
					drawWipCount(g, rect.x + rect.width, rect.y + rect.height, state.wipCount, state.inprCount,
							MonitorConfig.IMG_WIP_OVAL);
					/* L1.4 */
//				} catch (Exception ex) { /* L1.1 */
//					ex.printStackTrace(); /* L1.1 */
//				} /* L1.1 */
			} /* L1.3 */

			if (statShelf && // L3.5
					state.shelfLabelCnt > 0) {
				image = MonitorConfig.getImage(MonitorConfig.IMG_WIP_OVAL_EQP);
//				try {
					int w = image.getWidth(null);// L3.5
					int h = image.getHeight(null);// L3.5
					drawShelfCount(g, rect.x + rect.width - w, rect.y + rect.height - h, state.shelfLabelCnt,
							MonitorConfig.IMG_WIP_OVAL_EQP);

//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
			}

			if (statChamber && (state.chamberCnt > 0 || state.chamberMaxCnt > 0)) {

//				try {
					if (image != null) {
						int w = image.getWidth(null);
						drawChamberCount(g, (rect.x - w - gap + chamberX), (rect.y + chamberY), state.chamberCnt,
								state.chamberMaxCnt, "");
					}
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
			}

			// <--- L2.0 2004/09/27 T.Miyazaki add

		} // end if equipment only /* L1.3 */

		// Draw alert. (left-top)
		// L2.5 -->
		String alertFlagIndex = state.alertFlag;
		Vector typeV = new Vector();
		typeV.add("0");
		typeV.add("1");
		typeV.add("2");
		typeV.add("3");
		typeV.add("4");
		
		
//		if (statAlert && "Y".equals(state.alertFlag)) {
		if (statAlert && typeV.contains(alertFlagIndex)) {
			// && state.alertM) {

			// N0.01 image =
			// MonitorConfig.getImage(MonitorConfig.getAlertKey(3));

			// N0.01 ->
			if (state.alertO) {
				image = MonitorConfig.getImage(MonitorConfig.getAlertKey(ALRT_LVL_OVERIDLETIME));
				// -> N0.01
				// N0.02 ->
			} else if (state.alertH) {
				image = MonitorConfig.getImage(MonitorConfig.getAlertKey(ALRT_LVL_HIGH));
			} else if (state.alertM) {
				image = MonitorConfig.getImage(MonitorConfig.getAlertKey(ALRT_LVL_MIDDLE));
			} else if (state.alertL) {
				image = MonitorConfig.getImage(MonitorConfig.getAlertKey(ALRT_LVL_LOW));
			} else if (state.alertC) {
				image = MonitorConfig.getImage(MonitorConfig.getAlertKey(ALRT_LVL_C));
			} else if (state.alertN) {
				image = MonitorConfig.getImage(MonitorConfig.getAlertKey(ALRT_LVL_NORMAL));
			}
			// -> N0.02
			// System.out.println(image.toString()); //debug
			if (image != null) {
//				try {
					g.drawImage(image, rect.x, rect.y, null);
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
			}
		}

		// if (statAlert
		// && "Y".equals(state.alertFlag)
		// //L2.4 && state.alertLevel > 0) { /* L1.8 */
		// ) {//L2.4
		// //System.out.println(layer.toString()+"<alertLevel>" +
		// state.alertLevel );
		// //Show alert icon(1,2,3)
		// image =
		// MonitorConfig.getImage(
		// // MonitorConfig.getAlertKey(state.alertLevel));
		// MonitorConfig.getAlertKey(3));
		// if (image != null) {
		// try {
		// //Hsiaoya 2004/02/03
		// g.drawImage(image, rect.x, rect.y, null); /* L1.8 */
		// } catch (Exception ex) { /* L1.8 */
		// ex.printStackTrace(); /* L1.8 */
		// } /* L1.8 */
		// } /* L1.8 */
		// }

		// if (statAlertLongTime
		// && "Y".equals(state.alertFlag)
		// //L2.4 && state.longAlertLevel > 0) { /* L1.8 */
		// ) {//L2.4
		// //System.out.println(layer.toString()+"<longAlertLevel>" +
		// state.longAlertLevel );
		// //Show long alert time icon(5,6,7)
		// image =
		// MonitorConfig.getImage(
		// MonitorConfig.getAlertKey(state.longAlertLevel));
		// /* L1.8 */
		// if (image != null) { /* L1.8 */
		// try { /* L1.8 */
		// g.drawImage(image, rect.x, rect.y + 15, null); /* L1.8 */
		// } catch (Exception ex) { /* L1.8 */
		// ex.printStackTrace(); /* L1.8 */
		// } /* L1.8 */
		// } /* L1.8 */
		// }
		// L2.5 <--

//		if (statE84 && state.e84Flag) { /* L1.9 */
//			image = MonitorConfig.getImage(MonitorConfig.IMG_E84);
//			if (image != null) {
//				try {
//					g.drawImage(image, rect.x + 16, rect.y, null); /* L1.9 */
//				} catch (Exception ex) { /* L1.9 */
//					ex.printStackTrace(); /* L1.9 */
//				} /* L1.9 */
//			} /* L1.9 */
//		}

		g.dispose();
		offScreen.flush();

	}

	private void drawShelfCount(Graphics g, int x, int y, int shelfCount, String bgImg) { /* L1.1 */


		Color color = new Color(0, 0, 139);
		Image image = null;
		int height = 0;
		int fontSize = 9;

		String num = new Integer(shelfCount).toString();

		while (num.length() < 5) {
			num = " " + num;
		}

		if ((image = MonitorConfig.getImage(bgImg)) != null) {
			try {
				height = image.getHeight(null);
				g.drawImage(image, x, y, null);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		g.setColor(color);
		g.setFont(new Font("Arial", Font.PLAIN, fontSize));
		g.drawString(num, x, y + height - 2);

	}

	
	//Draw In-Process & WIP Count Test String
	private void drawWipCount(Graphics g, int x, int y, int wipCount, String wipLabelId, String bgImg) {

		Color color = new Color(0, 0, 0);
		Image image;

		int fontSize = 12;
		int height = 0;
		int width = 0;

		String num = new Integer(wipCount).toString();
		num = StringUtil.getCheckedValue(wipLabelId, "0") + "(" + StringUtil.getCheckedValue(num, "0") + ")";
		while (num.length() < 8) 
		{
			num = " ".concat(num);
		}
		// wip_oval.gifÇï\é¶
		if ((image = MonitorConfig.getImage(bgImg)) != null) {
//			try {
				height = image.getHeight(null);
				width = image.getWidth(null);
				height = height / 2;
				g.drawImage(image, x - width, y - (height * 3), null);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
		}

		g.setColor(color);
		g.setFont(new Font("Arial", Font.PLAIN, fontSize));
		g.drawString(num, x - width, y - height - 4);
	}

	private void drawChamberCount(Graphics g, int x, int y, int chamberCount, int chamberMaxCount, String bgImg) {

		final String SLASH = "/";
		final String BLANK = " ";

		Color color = new Color(0, 100, 0);

		int fontSize = 9;

		String num = new Integer(chamberCount).toString();
		String maxnum = new Integer(chamberMaxCount).toString();
		num = StringUtil.getCheckedValue(num, "0");
		maxnum = StringUtil.getCheckedValue(maxnum, "0");

		g.setColor(color);

		while (num.length() < 5) {
			num = BLANK + num;
		}

		g.setFont(new Font("Arial", Font.PLAIN, fontSize));
		g.drawString(num + SLASH + maxnum, x, y);
	}

	// private void launchSummary(int index, double sizeRate) {
	// final String METHOD_NAME = "launchSummary";
	// LogUtil.TRACE(true, CLASS_NAME, METHOD_NAME, "START");
	//
	// if (summaryInfo == null)
	// return;
	// if (index < 0 || index >= summaryInfo.length)
	// return;
	// SummaryDialog dialog = summaryInfo[index].dialog;
	// if (dialog == null) {
	// dialog = new SummaryDialog(summaryInfo[index].config);
	// summaryInfo[index].dialog = dialog;
	// if (inputListener != null)
	// inputListener.regist(dialog);
	// Container container = getTopLevelContainer();
	// Dimension size = container.getSize();
	// dialog.setSize(
	// (int) (size.width * sizeRate),
	// (int) (size.height * sizeRate));
	// dialog.addWindowListener(new SummaryDialogListener(index));
	// dialog.setLocationRelativeTo(container);
	// dialog.setVisible(true);
	// dialog.setInterval(monitorInterval);
	// dialog.start();
	// } else {
	// dialog.requestFocus();
	// }
	//
	// LogUtil.TRACE(true, CLASS_NAME, METHOD_NAME, "END");
	// }
	// ----------------------------------------------------- Internal Classes
	/**
	 * 
	 */
	private final class ChangeAreaListener implements ItemListener {
		final String CLASS_NAME = "MonitorPanel$ChangeAreaListener";

		public final void itemStateChanged(ItemEvent e) {

			if (e.getStateChange() == ItemEvent.SELECTED) {
				thisPanel.setCursor(new Cursor(Cursor.WAIT_CURSOR));
				boolean isRunning = thisPanel.isRunning();
				if (isRunning)
					thisPanel.stop();
				
//				try {
					itemStateChangedFlg = true;
					TextStringItem item = (TextStringItem) e.getItem();
					if (thisPanel.currentLayer != null)
						thisPanel.currentLayer.drawImage = null;

					thisPanel.currentLayer = m_layoutFileFlg.getArea(item.getString());

					statusPanel.setLayer(thisPanel.currentLayer);
//				} catch (Exception ex) {
//					ex.printStackTrace();
//				}
				
				if (isRunning)
					thisPanel.restart(false);
				
				thisPanel.setCursor(null);
			}
		}
	}

	private final class ProgressMonitorRunner implements Runnable {

		public String note = "";
		public int progress = 0;
		public boolean cancelFlag = false;
		public boolean closeFlag = false;
		private final ProgressMonitor monitor;

		public ProgressMonitorRunner(ProgressMonitor monitor) {
			this.monitor = monitor;
		}

		public final void run() {
			if (monitor == null) {
				return;
			}
			monitor.setProgress(progress);
			monitor.setNote(note);
			cancelFlag = monitor.isCanceled();
			if (closeFlag)
				monitor.close();
		}
	} // ProgressMonitorRunner

	private final class SummaryInfo {

		public SummaryConfig config = null;
		public SummaryDialog dialog = null;

		public SummaryInfo(SummaryConfig config) {

			this.config = config;
		}
	} // SummaryInfo

	private final class WaitTimerListener implements ActionListener {

		public final void actionPerformed(ActionEvent e) {

			waitTimer = null;
			Container container = getTopLevelContainer();
			if (container instanceof Applet) {
				ActionEvent ae = new ActionEvent(thisPanel, ActionEvent.ACTION_PERFORMED, CMD_LOGOFF);
				thisPanel.actionPerformed(ae);
			} else if (container instanceof Window) {
				WindowEvent we = new WindowEvent((Window) container, WindowEvent.WINDOW_CLOSING);
				container.dispatchEvent(we);
			}
		}
	} // WaitTimerListener

	private final class InputListener extends MouseInputAdapter implements ContainerListener {

		public InputListener() {
		}

		public final void regist(Component c) {

			if (c != null) {
				c.addMouseListener(this);
				c.addMouseMotionListener(this);
				if (c instanceof Container) {
					((Container) c).addContainerListener(this);
					Component[] ca = ((Container) c).getComponents();
					for (int i = 0; ca != null && i < ca.length; i++)
						regist(ca[i]);
				}
			}
		}

		public void inputDetected(InputEvent e) {
			if (waitTimer != null)
				waitTimer.restart();
		}

		/**
		 * Container events
		 */
		public final void componentAdded(ContainerEvent e) {
			regist(e.getChild());
		}

		public final void componentRemoved(ContainerEvent e) {
		}

		/**
		 * Mouse events
		 */
		public final void mouseMoved(MouseEvent e) {
			inputDetected(e);
		}

		public final void mousePressed(MouseEvent e) {
			inputDetected(e);
		}

		public final void mouseReleased(MouseEvent e) {
			inputDetected(e);
		}
	} // InputListener

	public void blink() {

		if (blinkFlg) {
			String blink_interval = MonitorConfig.get("alert.blink.interval", "1");
			timer1 = new javax.swing.Timer(Integer.parseInt(blink_interval)*1000, new ActionListener() {
				boolean flg = false;

				public void actionPerformed(ActionEvent e) {

					try {
						penetrateStatus(flg);
						flg = !flg;
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			});
			timer1.start();
		} else {
			boolean flg = false;
			try {
				penetrateStatus(flg);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void penetrateStatus(boolean FLG) throws InterruptedException 
	{
		if (m_layoutFileFlg == null)
			return;
		
		AreaLayer area = currentLayer;
		if (area == null)
			return;

		BufferedImage offScreen = null, defImage = null;
		EquipmentLayer equipment = null;
		Image areaImage = null;
		ImageDefinition imageDef = null;
		int screenWidth, screenHeight;
		int i, j, cnt;

		if ((imageDef = m_layoutFileFlg.getImageDefinition(area.imageId)) != null)
			areaImage = imageDef.image;

		// Create off screen.
		screenWidth = screenHeight = 0;
		if (areaImage != null) {
			screenWidth = areaImage.getWidth(null);
			screenHeight = areaImage.getHeight(null);
		}
		if (area.shape != null) {
			Rectangle rect = area.shape.getBounds();
			if (screenWidth < rect.width)
				screenWidth = rect.width;
			if (screenHeight < rect.height)
				screenHeight = rect.height;
		}
		if (screenWidth <= 0 || screenHeight <= 0)
			return;

		offScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);

		if (areaImage != null) {
			try {
				// Create off screen.
				Graphics g = offScreen.createGraphics();
				g.drawImage(areaImage, 0, 0, null);
				g.dispose();
				offScreen.flush();

				// Create default image.
				defImage = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
				g = defImage.createGraphics();
				g.drawImage(areaImage, 0, 0, null);
				g.dispose();
				defImage.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		//Equipment Unit 
		for (i = area.getEquipmentCount() - 1; i >= 0; i--) {

			equipment = area.getEquipment(i);
			penetrateLayerStatus(equipment, offScreen, imageDef, defImage, FLG);

			for (j = 0, cnt = equipment.getUnitCount(); j < cnt; j++) {
				penetrateLayerStatus(equipment.getUnit(j), offScreen, imageDef, defImage, FLG);
				//doYield();
			}
		}

		//Equipment Port
		for (i = area.getEquipmentCount() - 1; i >= 0; i--) {

			equipment = area.getEquipment(i);

			for (j = 0, cnt = equipment.getPortCount(); j < cnt; j++) {
				penetrateLayerStatus(equipment.getPort(j), offScreen, imageDef, defImage, FLG);
				//doYield();
			}
		}

		
		for (i = area.getEquipmentCount() - 1; i >= 0; i--) {

			equipment = area.getEquipment(i);

			for (j = 0, cnt = equipment.getUnitCount(); j < cnt; j++) {
				penetrateEffect(equipment.getUnit(j), offScreen, area);
				//doYield();
			}

			penetrateEffect(equipment, offScreen, area);
		}

		area.drawImage = offScreen;
		System.gc();
	}

	private void penetrateLayerStatus(BaseLayer layer, BufferedImage offScreen, ImageDefinition defImageDef,
			BufferedImage defImage, boolean FLG) 
	{
		if (layer.shape == null)
			return;

		BufferedImage sourceImage = null;
		Graphics g = null;
		Image targetImage = null;
		ImageDefinition imageDef = m_layoutFileFlg.getImageDefinition(layer.imageId);

		Rectangle rect = layer.shape.getBounds();
		if (rect.width <= 0 || rect.height <= 0)
			return;

		// Generate source image.
		if (imageDef != null && imageDef.image != null) { // from image.
			sourceImage = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_ARGB);
			g = sourceImage.createGraphics();
			g.drawImage(imageDef.image, 0, 0, rect.width, rect.height, invisibleColor, null);
			g.dispose();
		}

		// Generate source image from default image if the source image is not
		// specified.
		if (sourceImage == null && defImage != null) {
			sourceImage = new BufferedImage(rect.width, rect.height, BufferedImage.TYPE_INT_RGB);
			BufferedImage subImage = defImage.getSubimage(rect.x, rect.y, rect.width, rect.height);
			g = sourceImage.createGraphics();
			g.drawImage(subImage, 0, 0, rect.width, rect.height, null);
			g.dispose();
		}

		if (sourceImage == null)
			return;
		sourceImage.flush();

		MultiColorFilter colorFilter = new MultiColorFilter(0x10);
		ColorDefinition colorDef = null;
		ColorDefinition alrtcolorDef = null;
		ColorDefinition portstatcolorDef = null;
		Color baseColor = null;

		if (layer instanceof EquipmentLayer) {
			// for equipment (contains units)
			EquipmentState equipmentState = ((EquipmentLayer) layer).state;

			// Add status color filter.
//			colorDef = m_layoutFileFlg.getColorDefinition(ColorDefinition.STATUS, equipmentState.state, equipmentState.subState); // stateÇÃíl
//			if (colorDef == null)
			
			//WFM007 start
			if(layer.layoutType != LayoutType.STOCKER){
				colorDef =  m_layoutFileFlg.getColorDefinition(
						ColorDefinition.STATUS, equipmentState.subState);
				if(null == colorDef){
					colorDef = m_layoutFileFlg.getColorDefinition(
							ColorDefinition.STATUS, equipmentState.state);
				}
			}else {
				colorDef = m_layoutFileFlg.getColorDefinition(
						ColorDefinition.STATUS, equipmentState.state);
			}
			//WFM007 end

//			if(colorDef == null)
//				System.out.println("colorDef is null");
			
			if (colorDef != null && colorDef.color != null) {
				if (imageDef != null && imageDef.baseColor != null) {
					baseColor = imageDef.baseColor;
				} else if (defImageDef != null && defImageDef.baseColor != null) {
					baseColor = defImageDef.baseColor;
				} else {
					baseColor = null;
				}

				if (baseColor != null) {
					// if eqpt_stat is DOWN ==> blink
					if(equipmentState.currentStatus == 0){
						if (equipmentState.statDown) {
							equipmentState.currentStatus = 1; 
						}
					}else{
						equipmentState.currentStatus = 0;
					}					
					
					// alert enable
					if (equipmentState.currentAlarm == 0) {
						if (equipmentState.alertL) {
							equipmentState.currentAlarm = 1;
						} else if (equipmentState.alertM) {
							equipmentState.currentAlarm = 2;
						} else if (equipmentState.alertH) {
							equipmentState.currentAlarm = 3;
						} else if (equipmentState.alertC) {
							equipmentState.currentAlarm = 4;
						} else if (equipmentState.alertN) {
							equipmentState.currentAlarm = 0;
						} else {
							equipmentState.currentAlarm = 0;
						}
					} else if (equipmentState.currentAlarm == 1) {
						if (equipmentState.alertM) {
							equipmentState.currentAlarm = 2;
						} else if (equipmentState.alertH) {
							equipmentState.currentAlarm = 3;
						} else if (equipmentState.alertC) {
							equipmentState.currentAlarm = 4;
						} else {
							equipmentState.currentAlarm = 0;
						}
					} else if (equipmentState.currentAlarm == 2) {
						if (equipmentState.alertH) {
							equipmentState.currentAlarm = 3;
						} else if (equipmentState.alertC) {
							equipmentState.currentAlarm = 4;
						} else {
							equipmentState.currentAlarm = 0;
						}
					} else if (equipmentState.currentAlarm == 3) {
						if (equipmentState.alertC) {
							equipmentState.currentAlarm = 4;
						} else {
							equipmentState.currentAlarm = 0;
						}
					} else {
						equipmentState.currentAlarm = 0;
					}
					
					
					if (equipmentState.currentAlarm == 0 & equipmentState.currentStatus == 0) {
						colorFilter.add(baseColor, colorDef.color);
					} else if (equipmentState.currentAlarm == 1) {
						alrtcolorDef = m_layoutFileFlg.getColorDefinition(ColorDefinition.STATUS, "ALRT_L");
						if(null != alrtcolorDef){
							colorFilter.add(baseColor, alrtcolorDef.color);
						}else {
							colorFilter.add(baseColor, colorDef.color);
						}
					} else if (equipmentState.currentAlarm == 2) {
						alrtcolorDef = m_layoutFileFlg.getColorDefinition(ColorDefinition.STATUS, "ALRT_M");
						if(null != alrtcolorDef){
							colorFilter.add(baseColor, alrtcolorDef.color);
						}else {
							colorFilter.add(baseColor, colorDef.color);
						}
					} else if (equipmentState.currentAlarm == 3 || equipmentState.currentStatus == 1) {
						alrtcolorDef = m_layoutFileFlg.getColorDefinition(ColorDefinition.STATUS, "ALRT_H");
						if(null != alrtcolorDef){
							colorFilter.add(baseColor, alrtcolorDef.color);
						}else {
							colorFilter.add(baseColor, colorDef.color);
						}
					} else if (equipmentState.currentAlarm == 4) {
						alrtcolorDef = m_layoutFileFlg.getColorDefinition(ColorDefinition.STATUS, "ALRT_C");
						if(null != alrtcolorDef){
							colorFilter.add(baseColor, alrtcolorDef.color);
						}else {
							colorFilter.add(baseColor, colorDef.color);
						}
					}
									
					statusPanel.repaint();
				}
			}

			// Add mode color filter.
			colorDef = m_layoutFileFlg.getColorDefinition(ColorDefinition.MODE, equipmentState.mode);
			if (colorDef != null && colorDef.color != null) {
				if (imageDef != null && imageDef.borderColor != null)
					baseColor = imageDef.borderColor;
				else if (defImageDef != null && defImageDef.borderColor != null)
					baseColor = defImageDef.borderColor;
				else
					baseColor = null;
				if (baseColor != null)
					// ògÇÃêF
					colorFilter.add(baseColor, colorDef.color);
			}
		} else if (layer instanceof PortLayer) { // for ports
			PortState portState = ((PortLayer) layer).state;

			colorDef = m_layoutFileFlg.getColorDefinition(ColorDefinition.PORT_STATUS, portState.state);
			//System.out.println("T: "+((PortLayer) layer).state+" - "+ ((PortLayer) layer).state.state);
			// Add status color filter.
			if (colorDef != null && colorDef.color != null) {
				if (imageDef != null && imageDef.baseColor != null)
					baseColor = imageDef.baseColor;
				else
					baseColor = null;

//				if (baseColor != null)
//				colorFilter.add(baseColor, colorDef.color);

				if (baseColor != null) {

					if(portState.currentStatus == 0){
						if (portState.portLDCM) {
							//portState.currentStatus = 3; //WFM008
							portState.currentStatus = 0;//port don't blink WFM008
						}else{
							portState.currentStatus = 0;
						}
					}else{
						portState.currentStatus = 0;
					}		

					if (portState.currentStatus == 0) {
						colorFilter.add(baseColor, colorDef.color);
					} else if (portState.currentStatus == 3) {
						alrtcolorDef = m_layoutFileFlg.getColorDefinition(ColorDefinition.STATUS, "ALRT_L");
						colorFilter.add(baseColor, baseColor);
					}

					statusPanel.repaint();
				}
			}
		}
		
		if (colorFilter.isEmpty()) {
			targetImage = sourceImage;
		} else {
			targetImage = createImage(new FilteredImageSource(sourceImage.getSource(), colorFilter));
			if (targetImage != null)
				targetImage.flush();
		}

		// Draw off screen.
		if (targetImage != null) {
			try {
				g = offScreen.createGraphics();
				g.drawImage(targetImage, rect.x, rect.y, rect.width, rect.height, null);
				g.dispose();
				offScreen.flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private void initMarqueeTextRefreshTimer(){

		String refresh_interval = MonitorConfig.get("scroll.text.refreshinterval", "60");
		m_marqueeTextTimer = new javax.swing.Timer(Integer.parseInt(refresh_interval)*1000, new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				refreshMarqueeText();
			}
		});
		m_marqueeTextTimer.start();
	}
	
	private void refreshMarqueeText(){
		
		if(m_marqueeTextRequester == null) m_marqueeTextRequester = new URLRequester();
		URLConnection urlc = null;
		try {
			urlc = m_marqueeTextRequester.request(marqueeTextUriUri);
		} catch (IOException e) {
			e.printStackTrace();
			urlc = null;
		}
		if(urlc == null) return;
		
		StringBuffer strbfr = new StringBuffer();
		String line  = null;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
			while( (line = reader.readLine()) != null){
				strbfr.append(line);
				strbfr.append("  ");
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        scrollTextP.setMsg(strbfr.toString());
        
	}
	
	public void initScrollText() {
//		scrollTextP.setMsg("LCDView WFM Scrolling Text §§§Â¥˙∏’.");
//		scrollTextP.initPos();
//		scrollTextP.move();
		
		String backGroundStr = MonitorConfig.get(MonitorConfig.SCROLLING_BACKGROUND, "000080");
		String foreGroundStr = MonitorConfig.get(MonitorConfig.SCROLLING_FOREGROUND, "FFFF00");
		
		Color backColor = new Color(Integer.parseInt(backGroundStr, 16));
		Color foreColor = new Color(Integer.parseInt(foreGroundStr, 16));
		
		scrollTextP.setBackground(backColor);
		scrollTextP.setFontColor(foreColor);
		
		refreshMarqueeText();
		initMarqueeTextRefreshTimer();
		scrollTextP.initPos();
		scrollTextP.move();
	}
}
