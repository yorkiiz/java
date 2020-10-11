package com.ibm.lcd.cfm.monitor.util;

import java.io.Serializable;
import java.util.ArrayList;

public class GenericTable implements Serializable {
	private static final long serialVersionUID = 8573089628046977121L;

	final String CLASS_NAME = "GenericTable";

	private int iColumns;
	private ArrayList lstRow;

	public GenericTable() {

		iColumns = 0;
		lstRow = new ArrayList();
	}

	public GenericTable(GenericTable tbl) {
		this();

		if (tbl == null)
			return;
		int rows = getRows();
		int cols = getColumns();
		addRow(0, rows);
		addColumn(0, cols);
		int row, col;
		for (row = 0; row < rows; row++) {
			for (col = 0; col < cols; col++)
				set(row, col, tbl.get(row, col));
		}
	}

	public int getRows() {
		return lstRow.size();
	}

	public int getColumns() {
		return iColumns;
	}

	public void addRow() {
		lstRow.add(newRow());
	}

	public void addRow(int index) throws IndexOutOfBoundsException {
		lstRow.add(index, newRow());
	}

	public void addRow(int index, int size) throws IndexOutOfBoundsException {
		for (int i = 0; i < size; i++)
			lstRow.add(index, newRow());
	}

	public void removeRow(int index) throws IndexOutOfBoundsException {
		lstRow.remove(index);
	}

	public void addColumn() {
		for (int i = lstRow.size() - 1; i >= 0; i--)
			((ArrayList) lstRow.get(i)).add(null);
		iColumns++;
	}

	public void addColumn(int index) throws IndexOutOfBoundsException {
		for (int i = lstRow.size() - 1; i >= 0; i--)
			((ArrayList) lstRow.get(i)).add(index, null);
		iColumns++;
	}

	public void addColumn(int index, int size) throws IndexOutOfBoundsException {
		ArrayList lst = null;
		int j = 0;
		for (int i = lstRow.size() - 1; i >= 0; i--) {
			lst = (ArrayList) lstRow.get(i);
			for (j = 0; j < size; j++)
				lst.add(index, null);
		}
		if (size > 0)
			iColumns += size;
	}

	public void removeColumn(int index) throws IndexOutOfBoundsException {
		for (int i = lstRow.size() - 1; i >= 0; i--)
			((ArrayList) lstRow.get(i)).remove(index);
		iColumns--;
	}

	public void clear() {
		lstRow.clear();
		iColumns = 0;
	}

	public Object get(int row, int column) throws IndexOutOfBoundsException {
		return ((ArrayList) lstRow.get(row)).get(column);
	}

	public void set(int row, int column, Object obj) throws IndexOutOfBoundsException {
		((ArrayList) lstRow.get(row)).set(column, obj);
	}

	public int rowIndexOf(int column, Object obj) throws IndexOutOfBoundsException {
		Object columnObj = null;
		int i, j;
		for (i = 0, j = lstRow.size(); i < j; i++) {
			columnObj = ((ArrayList) lstRow.get(i)).get(column);
			if ((columnObj != null && columnObj.equals(obj)) || (columnObj == null && obj == null))
				break;
		}
		return ((i < j) ? i : -1);
	}

	public int rowLastIndexOf(int column, Object obj) throws IndexOutOfBoundsException {
		Object columnObj = null;
		int i = 0;
		for (i = lstRow.size() - 1; i >= 0; i--) {
			columnObj = ((ArrayList) lstRow.get(i)).get(column);
			if ((columnObj != null && columnObj.equals(obj)) || (columnObj == null && obj == null))
				break;
		}
		return i;
	}

	public int columnIndexOf(int row, Object obj) throws IndexOutOfBoundsException {
		return ((ArrayList) lstRow.get(row)).indexOf(obj);
	}

	public int columnLastIndexOf(int row, Object obj) throws IndexOutOfBoundsException {
		return ((ArrayList) lstRow.get(row)).lastIndexOf(obj);
	}

	private ArrayList newRow() {
		ArrayList lst = new ArrayList();
		for (int i = 0; i < iColumns; i++)
			lst.add(null);
		return lst;
	}

}
