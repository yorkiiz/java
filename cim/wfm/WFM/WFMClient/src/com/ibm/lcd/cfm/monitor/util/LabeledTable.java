package com.ibm.lcd.cfm.monitor.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.ibm.lcd.cfm.monitor.config.DevicePanelConfig;

public class LabeledTable extends GenericTable {
	private static final long serialVersionUID = -7884863532822383939L;

	final String CLASS_NAME = "LabeledTable";

	private ArrayList lstLabel;

	public LabeledTable() {
		super();

		lstLabel = new ArrayList();
	}

	public LabeledTable(GenericTable table, String Name, ArrayList arrayList) {
		this();


		if (table == null)
			return;
		int columns = table.getColumns();
		int rows = table.getRows();

		if (rows > 0 && columns > 0) {
			int row, column;
			addColumn(0, columns);
			addRow(0, rows - 1);
			for (column = 0; column < columns; column++) {
				try {
					setLabel(column, (String) table.get(0, column));
					arrayList.add(column, table.get(0, column));
				} catch (Exception ex) {
				}
			}
			for (row = 1; row < rows; row++) {
				for (column = 0; column < columns; column++)
					set(row - 1, column, table.get(row, column));
			}
		}
	}

	public LabeledTable(GenericTable table, String key, HashMap deviceMap) {
		this();

		if (table == null)
			return;
		int columns = table.getColumns();
		int rows = table.getRows();

		if (rows > 0 && columns > 0) {
			int row, column;
			addColumn(0, columns);
			addRow(0, rows - 1);
			for (column = 0; column < columns; column++) {
				try {
					setLabel(column, (String) table.get(0, column));

					if (key.equals(DevicePanelConfig.keyOIN)) {
						deviceMap.put(DevicePanelConfig.keyOIN, table.get(0, column));
					} else if (key.equals(DevicePanelConfig.keyADN)) {
						deviceMap.put(DevicePanelConfig.keyADN, table.get(0, column));
					} else if (key.equals(DevicePanelConfig.keyWLN)) {
						deviceMap.put(DevicePanelConfig.keyWLN, table.get(0, column));
					} else if (key.equals(DevicePanelConfig.keyDLN)) {
						deviceMap.put(DevicePanelConfig.keyDLN, table.get(0, column));
					} else if (key.equals(DevicePanelConfig.keyNWLN)) {
						deviceMap.put(DevicePanelConfig.keyNWLN, table.get(0, column));
					} else if (key.equals(DevicePanelConfig.keyNDN)) {
						deviceMap.put(DevicePanelConfig.keyNDN, table.get(0, column));
					} else if (key.equals(DevicePanelConfig.keyDWLN)) {
						deviceMap.put(DevicePanelConfig.keyDWLN, table.get(0, column));
					} else if (key.equals(DevicePanelConfig.keyDDN)) {
						deviceMap.put(DevicePanelConfig.keyDDN, table.get(0, column));
					}
				} catch (Exception ex) {
				}
			}
			for (row = 1; row < rows; row++) {
				for (column = 0; column < columns; column++)
					set(row - 1, column, table.get(row, column));
			}
		}
	}

	public LabeledTable(GenericTable table) {
		this();

		if (table == null)
			return;
		int columns = table.getColumns();
		int rows = table.getRows();
		if (rows > 0 && columns > 0) {
			int row, column;
			addColumn(0, columns);
			addRow(0, rows - 1);
			for (column = 0; column < columns; column++) {
				try {
					setLabel(column, (String) table.get(0, column));
				} catch (Exception ex) {
				}
			}
			for (row = 1; row < rows; row++) {
				for (column = 0; column < columns; column++)
					set(row - 1, column, table.get(row, column));
			}
		}
	}

	public void addColumn() {
		super.addColumn();
		lstLabel.add(null);
	}

	public void addColumn(int index) throws IndexOutOfBoundsException {
		super.addColumn(index);
		lstLabel.add(index, null);
	}

	public void addColumn(int index, int size) throws IndexOutOfBoundsException {
		super.addColumn(index, size);
		for (int i = 0; i < size; i++)
			lstLabel.add(index, null);
	}

	public void removeColumn(int index) throws IndexOutOfBoundsException {
		super.removeColumn(index);
		lstLabel.remove(index);
	}

	public void clear() {
		super.clear();
		lstLabel.clear();
	}

	public void setLabel(int column, String label) throws IndexOutOfBoundsException {
		lstLabel.set(column, label);
	}

	public String getLabel(int column) throws IndexOutOfBoundsException {
		return (String) lstLabel.get(column);
	}

	public String[] getLabels() {
		String[] labels = new String[0];
		return (String[]) lstLabel.toArray(labels);
	}

	public int labelIndexOf(String label) {
		return lstLabel.indexOf(label);
	}

	public int labelLastIndexOf(String label) {
		return lstLabel.lastIndexOf(label);
	}

	public Object get(int row, String label) throws IndexOutOfBoundsException {
		int column = labelIndexOf(label);
		return ((column >= 0) ? super.get(row, column) : null);
	}

	public void set(int row, String label, Object obj) {
		int column = labelIndexOf(label);
		if (column >= 0)
			super.set(row, column, obj);
	}

}
