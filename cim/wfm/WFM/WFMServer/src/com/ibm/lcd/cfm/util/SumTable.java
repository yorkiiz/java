
package com.ibm.lcd.cfm.util;

import com.ibm.cxl.util.GenericTable;

public class SumTable extends GenericTable {

	private static final long serialVersionUID = 4527039290262463834L;

	public SumTable() {
		super();
		addRow();
		addColumn();
	}

	public void clear() {
		super.clear();
		addRow();
		addColumn();
	}

	public int addAxisX(String axisX) {
		addColumn();
		int index = getColumns() - 1;
		for (int i = getRows() - 1; i > 0; i--)
			set(i, index, new Integer(0));
		set(0, index, axisX);
		return index;
	}

	public int addAxisX(int index, String axisX) throws IndexOutOfBoundsException {
		if (index == 0)
			throw new IndexOutOfBoundsException("Index 0 is reserved for labels.");
		addColumn(index);
		for (int i = getRows() - 1; i > 0; i--)
			set(i, index, new Integer(0));
		set(0, index, axisX);
		return index;
	}

	public int addAxisY(String axisY) {
		addRow();
		int index = getRows() - 1;
		for (int i = getColumns() - 1; i > 0; i--)
			set(index, i, new Integer(0));
		set(index, 0, axisY);
		return index;
	}

	public int addAxisY(int index, String axisY) throws IndexOutOfBoundsException {
		if (index == 0)
			throw new IndexOutOfBoundsException("Index 0 is reserved for labels.");
		addRow(index);
		for (int i = getColumns() - 1; i > 0; i--)
			set(index, i, new Integer(0));
		set(index, 0, axisY);
		return index;
	}

	public void sumup(String axisX, String axisY, Object obj) {
		int column = 0, row = 0;
		Object cellObject = null;
		if ((column = columnIndexOf(0, axisX)) == -1)
			column = addAxisX(axisX);
		if ((row = rowIndexOf(0, axisY)) == -1)
			row = addAxisY(axisY);
		if ((cellObject = get(row, column)) == null)
			cellObject = new Integer(0);
		if (cellObject instanceof Integer && obj instanceof Integer)
			cellObject = new Integer(((Integer)cellObject).intValue() + ((Integer)obj).intValue());
		else
			cellObject = obj; // Cannot sum up ! Replace value.
		set(row, column, cellObject);
	}

	/**
	 * Returns the total amount of the specified axis X.
	 * Returns 0 if if index is out of range (index <= 0 || index >= getRows()).
	 *
	 * @param index the row index.
	 * @return the total amount.
	 */
	public int totalAxisX(int index) {
		if (index <= 0 || index >= getRows())
			return 0;
		int total = 0;
		for (int i = getColumns() - 1; i > 0; i--)
			total += ((Integer)get(index, i)).intValue();
		return total;
	}

	/**
	 * Returns the total amount of the specified axis Y.
	 * Returns 0 if if index is out of range (index <= 0 || index >= getColumns()).
	 *
	 * @param index the row index.
	 * @return the total amount.
	 */
	public int totalAxisY(int index) {
		if (index <= 0 || index >= getColumns())
			return 0;
		int total = 0;
		for (int i = getRows() - 1; i > 0; i--)
			total += ((Integer)get(i, index)).intValue();
		return total;
	}

}
