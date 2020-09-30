package com.ibm.lcd.cfm.monitor.status;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
//import javax.swing.JSplitPane;

import com.ibm.lcd.cfm.monitor.config.MonitorConfig;
import com.ibm.lcd.cfm.monitor.layout.AreaLayer;
import com.ibm.lcd.cfm.monitor.layout.BaseLayer;
import com.ibm.lcd.cfm.monitor.layout.EquipmentLayer;
//import com.ibm.lcd.cfm.monitor.layout.EquipmentState;
import com.ibm.lcd.cfm.monitor.layout.LinkDefinition;
import com.ibm.lcd.cfm.monitor.layout.MonitorLayout;
import com.ibm.lcd.cfm.monitor.util.URLRequester;

public class StatusPanel extends JLayeredPane implements MouseListener, MouseMotionListener{
	private static final long serialVersionUID = -3176316520080548165L;

	private MonitorLayout layout = null;

	// private final TransferPanel transferPanel;
	// private JSplitPane splitPane;

	private boolean autoScale = false;
	private double enlargeRate = 1;//fix can zoom out bug 
	private double dbl_width;
	private double dbl_height;
	private volatile AreaLayer currentLayer = null;
	private volatile boolean canPaint = false;
//	private String lastLayer = null;
//	private int horizontalLocation;

//	private boolean ON_OFF_FLG = false;
//	private boolean ON_OFF_FLG_BK = true;

	// public StatusPanel(TransferPanel transferPanel)
	public StatusPanel() {
		super();
		// this.transferPanel = transferPanel;
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public final boolean contains(int x, int y) {
		BaseLayer layer = hitTest(new Point(x, y), false);
		if (layer != null) {
			setToolTipText(layer.toString());
			return true;
		}
		return false;
	}

	public final void setPaint(boolean flag) {
		this.canPaint = flag;
	}

	 public final void setMonitorLayout(MonitorLayout layout) {
		 this.layout = layout;
	 }

	// public final void setSplitPanel(JSplitPane splitPane) {
	// this.splitPane = splitPane;
	// }

	public final void setHorizontalLocation(int horizontalLocation) { /* L1.2 */
		// this.horizontalLocation = horizontalLocation; /* L1.2 */
	}

	public final void setLayer(AreaLayer layer) {
		currentLayer = layer;
		repaint();
		// transferPanel.setLayer(null);
	}

	public final void setAutoScale(boolean flag) {
		autoScale = flag;
		enlargeRate = 1;
	}

	public final void setEnlargeRate(double rate) {//fix can zoom out bug
		if (autoScale)
			return;
		if (rate < 0)
			rate = 1;
		enlargeRate = rate;
	}

	public final double getEnlargeRate() {//fix can zoom out bug
		return enlargeRate;
	}

	public final void paintComponent(Graphics g) {
		super.paintComponent(g);

		AreaLayer layer = currentLayer;
		Image layerImage = (layer != null) ? layer.drawImage : null;
		if (canPaint && layerImage != null) {
			if (autoScale) {
				if (getPreferredSize() != null) {
					setPreferredSize(null);
					revalidate();
				}
				double image_width = layerImage.getWidth(null);
				double image_height = layerImage.getHeight(null);
				double panel_width = getWidth();
				double panel_height = getHeight();
				double width_point = panel_width / image_width;
				double height_point = panel_height / image_height;
				double times;
				if (width_point > height_point) {
					times = height_point;
				} else {
					times = width_point;
				}
				int int_width = (int) Math.round(image_width * times);
				int int_height = (int) Math.round(image_height * times);
				dbl_width = Math.round(image_width * times);
				dbl_height = Math.round(image_height * times);
				g.drawImage(layerImage, 0, 0, int_width, int_height, null);
			} else {
				Dimension imageSize = new Dimension(layerImage.getWidth(null), layerImage.getHeight(null));
				if (enlargeRate != 1)//fix can zoom out bug
					imageSize.setSize(imageSize.width * enlargeRate, imageSize.height * enlargeRate);
				if (!imageSize.equals(getPreferredSize())) {
					setPreferredSize(imageSize);
					revalidate();
				}
				if (enlargeRate != 1)//fix can zoom out bug
					g.drawImage(layerImage, 0, 0, imageSize.width, imageSize.height, null);
				else
					g.drawImage(layerImage, 0, 0, null);
			}
		} else {
			drawMessage(g, null, null);
		}

	}

	public final void mouseClicked(MouseEvent e) {
		e.consume();
		
		BaseLayer layer = hitTest(e.getPoint(), false);
		if(layer == null) return;
		
		if(this.getCursor() != HAND_CURSOR) return;;

        String str = URLRequester.getURLString(layer.getMainLinkURI(), null);
        if (str.length() <= 0)
            return;
        
        if (str.startsWith("/")) {
            String contextPath = MonitorConfig.get(MonitorConfig.CONTEXT_PATH, "");
            if (contextPath.endsWith("/"))
                str = contextPath + str.substring(1);
            else
                str = contextPath + str;
        }
    	Container ancestor = getTopLevelAncestor();
    	if(ancestor instanceof Applet)
    	{
	        Applet applet = (Applet)ancestor;
	        URL url = null;
			try {
				url = new URL(str);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
				url = null;
			}
	        
			if(url == null) return;
			String target = layer.getLinkTarget();
	        applet.getAppletContext().showDocument(url, target);
    	}
	}

	public final void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger())
			launchPopupMenu(e.getPoint());
	}

	public final void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger())
			launchPopupMenu(e.getPoint());
	}

	public final void mouseEntered(MouseEvent e) {
	}

	public final void mouseExited(MouseEvent e) {
	}

	private final void drawMessage(Graphics g, String message, Color color) {

		int panelWidth = getWidth();
		int panelHeight = getHeight();

		setPreferredSize(null);
		if (panelWidth <= 0 || panelHeight <= 0)
			return;

		revalidate();
		g.clearRect(0, 0, panelWidth, panelHeight);

		if (message == null || message.length() <= 0)
			return;

		Color oldColor = g.getColor();
		if (color != null)
			g.setColor(color);

		FontMetrics metrics = g.getFontMetrics();
		Rectangle rect = metrics.getStringBounds(message, g).getBounds();

		int x = (panelWidth - rect.width) / 2;
		int y = (panelHeight - rect.height) / 2;
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;

		g.drawString(message, x, y);
		g.setColor(oldColor);
	}

	private BaseLayer hitTest(Point point, boolean equipmentOnly) {

		if (!canPaint)
			return null;

		AreaLayer areaLayer = currentLayer;
		if (areaLayer == null || point == null)
			return null;
		Image image = areaLayer.drawImage;
		if (image == null)
			return null;

		Dimension panelSize = getSize();
		if (panelSize.getWidth() <= 0 || panelSize.getHeight() <= 0)
			return null;

		double x = point.getX();
		double y = point.getY();
		BaseLayer layer = null, hitLayer = null;
		EquipmentLayer equipmentLayer = null;
		int i, j;
		int equipmentCnt, cnt;

		if (autoScale) {
			int image_width = image.getWidth(null);
			int image_height = image.getHeight(null);
			x = x * (image_width / dbl_width);
			y = y * (image_height / dbl_height);
		} else {
			x = x / enlargeRate;
			y = y / enlargeRate;
		}

		if (!equipmentOnly) {
			for (i = 0, equipmentCnt = areaLayer.getEquipmentCount(); i < equipmentCnt; i++) {
				hitLayer = null;
				equipmentLayer = areaLayer.getEquipment(i);
				if (equipmentLayer == null)
					continue;

				for (j = 0, cnt = equipmentLayer.getPortCount(); j < cnt; j++) {
					layer = equipmentLayer.getPort(j);
					if (layer != null && layer.shape != null && layer.shape.contains(x, y)) {
						hitLayer = layer;

						return hitLayer;
					}
				}
			}
			for (i = 0, equipmentCnt = areaLayer.getEquipmentCount(); i < equipmentCnt; i++) {
				
				equipmentLayer = areaLayer.getEquipment(i);
				if (equipmentLayer == null)
					continue;

				for (j = 0, cnt = equipmentLayer.getUnitCount(); j < cnt; j++) {
					hitLayer = null;
					layer = equipmentLayer.getUnit(j);
					if (layer != null && layer.shape != null && layer.shape.contains(x, y)) {
						hitLayer = layer;
						return hitLayer;
					}
				}
			}
		}

		for (i = 0, equipmentCnt = areaLayer.getEquipmentCount(); i < equipmentCnt; i++) {
			hitLayer = null;
			equipmentLayer = areaLayer.getEquipment(i);
			if (equipmentLayer == null)
				continue;
			if (equipmentLayer.shape != null && equipmentLayer.shape.contains(x, y))
			{
				hitLayer = equipmentLayer;
				break;
			}
		}

		return hitLayer;
	}

	private void launchPopupMenu(Point point) {

		BaseLayer layer = hitTest(point, false);
		if (layer == null)
			return;

//		EquipmentState equipmentState = null;
//		if (layer instanceof EquipmentLayer) {
//			equipmentState = ((EquipmentLayer) layer).state;
//		}

		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		JPopupMenu popupMenu = new JPopupMenu();
		JLabel label = new JLabel(layer.toString());
		label.setBorder(BorderFactory.createRaisedBevelBorder());

		PopupMenuPanel panel = new PopupMenuPanel(layer);
		popupMenu.add(panel);
		popupMenu.addSeparator();
		popupMenu.add(MonitorConfig.getMessage("popup.close"));

		Container ancestor = getTopLevelAncestor();
//		if (ancestor instanceof java.applet.Applet) {
			LinkDefinition[] link = null;
			if (layout != null){
				link = layout.getLinkDefinition(layer.layoutType);
			}
			if (link != null && link.length > 0) {
				String target = MonitorConfig.get(MonitorConfig.LINK_TARGET, null);
				JMenu menu = new JMenu(MonitorConfig.getMessage("popup.link"));
				JMenuItem menuItem = null;

				for (int i = 0; i < link.length; i++) {
//					if ((layer instanceof EquipmentLayer) && equipmentState.getId().startsWith("GHI")
//							&& !link[i].url.startsWith("/ppt_eqptAlrtList")) {
//						continue;
//					}
					menuItem = new JMenuItem(link[i].toString());
					menuItem.addActionListener(new LinkActionListener(ancestor, layer, link[i], target));
					menu.add(menuItem);
				}
				popupMenu.add(menu);
			}
//		}

		popupMenu.show(this, point.x, point.y);
		setCursor(null);
	}

	public void mouseDragged(MouseEvent e) {
		
	}

	private static final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
	private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	
	public void mouseMoved(MouseEvent e) {
		
		
		BaseLayer layer = hitTest(e.getPoint(), false);
		if(layer != null && layer.getMainLinkURI() != null &&  layer.getMainLinkURI().length() != 0){
			setCursor(HAND_CURSOR);
		}
		else
		{
			setCursor(DEFAULT_CURSOR);
		}	
		
	}
}
