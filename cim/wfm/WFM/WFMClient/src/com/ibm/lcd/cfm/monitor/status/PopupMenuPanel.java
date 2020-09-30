package com.ibm.lcd.cfm.monitor.status;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ibm.lcd.cfm.monitor.config.MonitorConfig;
import com.ibm.lcd.cfm.monitor.layout.BaseLayer;
import com.ibm.lcd.cfm.monitor.layout.EquipmentLayer;
import com.ibm.lcd.cfm.monitor.layout.EquipmentState;
import com.ibm.lcd.cfm.monitor.layout.PortLayer;
import com.ibm.lcd.cfm.monitor.layout.PortState;
import com.ibm.lcd.cfm.monitor.layout.StockerLayer;
import com.ibm.lcd.cfm.monitor.layout.UnitLayer;

public final class PopupMenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	final String CLASS_NAME = "PopupMenuPanel";

	private JLabel[] dataLabel = null;
	private BaseLayer currentLayer = null;

	public PopupMenuPanel(BaseLayer layer) {
		super();
		initPanel(layer);
		currentLayer = layer;
		refresh();
	}

	private void initPanel(BaseLayer layer) {

		JLabel label = new JLabel();
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		if (layer != null)
			label.setText(layer.toString());
		setLayout(new BorderLayout());
		add(label, BorderLayout.NORTH);
		ArrayList labelKey = new ArrayList();
		labelKey.add("popup.data.type");

		if (layer instanceof PortLayer) {
			labelKey.add("popup.data.status");
		} else if (layer instanceof UnitLayer) {
			labelKey.add("popup.data.status");
			labelKey.add("popup.data.mode");
			labelKey.add("popup.data.alert");
		} else if (layer instanceof StockerLayer) {
			labelKey.add("popup.data.status");
			labelKey.add("popup.data.mode");
			labelKey.add("popup.data.alert");
			labelKey.add("popup.data.stock");
			labelKey.add("popup.data.stockNumber");
			EquipmentState equipmentState = ((EquipmentLayer) layer).state;
			if (equipmentState.alertD) {
				labelKey.add("popup.alert.spc");
			}
			if (equipmentState.alertP) {
				labelKey.add("popup.alert.long_p");
			}
			if (equipmentState.alertU) {
				labelKey.add("popup.alert.long_u");
			}
			if (equipmentState.alertE) {
				labelKey.add("popup.alert.mms");
			}
		} else if (layer instanceof EquipmentLayer) {
			EquipmentState g_eqptState = ((EquipmentLayer) layer).state;
			if (g_eqptState.getId().startsWith("GHI")) {
				labelKey.add("popup.data.status");
				labelKey.add("popup.data.mode");
				labelKey.add("popup.data.alert");
			} else {
				labelKey.add("popup.data.status");
				labelKey.add("popup.data.mode");
				labelKey.add("popup.data.alert");
				labelKey.add("popup.data.wip");
				// labelKey.add("popup.data.abnormal");
				// labelKey.add("popup.data.assign.load");
				// labelKey.add("popup.data.assign.unload");
				labelKey.add("popup.data.inprCount");
				EquipmentState equipmentState = ((EquipmentLayer) layer).state;
				if (equipmentState.alertD) {
					labelKey.add("popup.alert.spc");
				}
				if (equipmentState.alertP) {
					labelKey.add("popup.alert.long_p");
				}
				if (equipmentState.alertU) {
					labelKey.add("popup.alert.long_u");
				}
				if (equipmentState.alertE) {
					labelKey.add("popup.alert.mms");
				}
			}
		}

		GridLayout gridLayout = new GridLayout(labelKey.size(), 2);
		gridLayout.setHgap(5);
		JPanel panel = new JPanel(gridLayout);
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createEtchedBorder());

		dataLabel = new JLabel[labelKey.size()];
		for (int i = 0; i < labelKey.size(); i++) {
			String keyword = (String) labelKey.get(i);

			if (keyword.startsWith("popup.alert")) {
				panel.add(makeHeaderLabel(MonitorConfig.getMessage(keyword), Color.RED));
			} else {
				panel.add(makeHeaderLabel(MonitorConfig.getMessage(keyword), Color.BLUE));
			}

			dataLabel[i] = new JLabel();
			panel.add(dataLabel[i]);
		}
		add(panel, BorderLayout.CENTER);
	}

	public final void refresh() {
		if (currentLayer instanceof PortLayer) {
			PortState portState = ((PortLayer) currentLayer).state;
			setDataLabel(0, MonitorConfig.getMessage("popup.data.type.port"));
			setDataLabel(1, portState.state);
		} else if (currentLayer instanceof EquipmentLayer) {
			EquipmentState equipmentState = ((EquipmentLayer) currentLayer).state;
			if (equipmentState.subState != null && equipmentState.subState.length() > 0) {
				setDataLabel(1, equipmentState.state + " (" + equipmentState.subState + ")");
			} else {
				setDataLabel(1, equipmentState.state);
			}
			setDataLabel(2, equipmentState.mode);
			setDataLabel(3, equipmentState.alertFlag);

			if (equipmentState.getId().startsWith("GHI")) {
				setDataLabel(0, MonitorConfig.getMessage("popup.data.type.g_line"));
			} else if (currentLayer instanceof UnitLayer) {
				setDataLabel(0, MonitorConfig.getMessage("popup.data.type.unit"));
			} else if (currentLayer instanceof StockerLayer) {
				setDataLabel(0, MonitorConfig.getMessage("popup.data.type.stocker"));
				setDataLabel(4, String.valueOf(equipmentState.bufferStockCnt));
				setDataLabel(5, String.valueOf(equipmentState.shelfLabelCnt));
			} else {
				setDataLabel(0, MonitorConfig.getMessage("popup.data.type.eqiupment"));
				setDataLabel(4, String.valueOf(equipmentState.wipCount));
				// setDataLabel(5, "" + equipmentState.abnormalCount);
				// setDataLabel(6, equipmentState.assignLoad);
				// setDataLabel(7, equipmentState.assignUnload);
				setDataLabel(5, equipmentState.inprCount);
			}
		} else {
			setDataLabel(0, MonitorConfig.getMessage("popup.data.type.unknown"));
		}
	}

	private JLabel makeHeaderLabel(String string, Color color) {

		JLabel label = new JLabel(string);

		Font font = label.getFont().deriveFont(Font.BOLD);
		label.setFont(font);
		label.setForeground(color);

		return label;
	}

	private void setDataLabel(int index, String text) {
		if (index >= 0 && index < dataLabel.length)
			dataLabel[index].setText(text);
	}

}
