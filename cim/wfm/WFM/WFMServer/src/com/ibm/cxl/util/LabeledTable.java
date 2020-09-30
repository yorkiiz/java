/*
 * (C) Copyright IBM Corp. 2003 All rights reserved.
 *
 * US Government Users Restricted Rights Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 *
 * The program is provided "as is" without any warranty express or
 * implied, including the warranty of non-infringement and the implied
 * warranties of merchantibility and fitness for a particular purpose.
 * IBM will not be liable for any damages suffered by you as a result
 * of using the Program. In no event will IBM be liable for any
 * special, indirect or consequential damages or lost profits even if
 * IBM has been advised of the possibility of their occurrence. IBM
 * will not be liable for any third party claims against you.
 */
package com.ibm.cxl.util;

import java.util.ArrayList;

/**
 * This class represents a labled table contains objects.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public class LabeledTable extends GenericTable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9174234564832507633L;
	/** The ArrayList contains labels. */
    private ArrayList lstLabel;

    /**
     * Constructs a new, empty object.
     *
     */
    public LabeledTable() {
        super();
        lstLabel = new ArrayList();
    }

    /**
     * Constructs a new object with the specified table.
     *
     * @param table source table.
     */
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
                    setLabel(column, (String)table.get(0, column));
                }
                catch (Exception ex) {}
            }
            for (row = 1; row < rows; row++) {
                for (column = 0; column < columns; column++)
                    set(row - 1, column, table.get(row, column));
            }
        }
    }

    /**
     * Appends a new column to the end of this table.
     *
     */
    public void addColumn() {
        super.addColumn();
        lstLabel.add(null);
    }

    /**
     * Inserts a new column at the specified position in this table.
     *
     * @param index an index at which a new column is to be inserted.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getCols()).
     */
    public void addColumn(int index) throws IndexOutOfBoundsException {
        super.addColumn(index);
        lstLabel.add(index, null);
    }

    /**
     * Inserts new columns with the specified size at the specified position in this table.
     *
     * @param index an index at which new columns are to be inserted.
     * @param size the size of columns.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getCols()).
     */
    public void addColumn(int index, int size) throws IndexOutOfBoundsException {
        super.addColumn(index, size);
        for (int i = 0; i < size; i++)
            lstLabel.add(index, null);
    }

    /**
     * Removes a column at the specified position in this table.
     *
     * @param index an index of the column to be removed.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getCols()).
     */
    public void removeColumn(int index) throws IndexOutOfBoundsException {
        super.removeColumn(index);
        lstLabel.remove(index);
    }

    /**
     * Removes all of the elements from this table.
     *
     */
    public void clear() {
        super.clear();
        lstLabel.clear();
    }

    /**
     * Replaces the label at the specified position in this table with the specified label.
     *
     * @param column the column index.
     * @param label the label to be stored at the specified position.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getCols()).
     */
    public void setLabel(int column, String label) throws IndexOutOfBoundsException {
        lstLabel.set(column, label);
    }

    /**
     * Returns a label at the specified position.
     *
     * @param column the column index.
     * @return a label at the specified position.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getCols()).
     */
    public String getLabel(int column) throws IndexOutOfBoundsException {
        return (String)lstLabel.get(column);
    }

    /**
     * Returns all labels in this table.
     *
     * @param the array of labels.
     */
    public String[] getLabels() {
        String[] labels = new String[0];
        return (String[])lstLabel.toArray(labels);
    }

    /**
     * Searches for the first occurence of the given argument, testing for equality using the equals method.
     *
     * @param label the label to be tested.
     * @return the label index of the first occurrence of the argument in this table; returns -1 if the label is not found.
     */
    public int labelIndexOf(String label) {
        return lstLabel.indexOf(label);
    }

    /**
     * Returns the label index of the last occurrence of the specified label in this table.
     *
     * @param label the label to be tested.
     * @return the label index of the first occurrence of the argument in this table; returns -1 if the label is not found.
     */
    public int labelLastIndexOf(String label) {
        return lstLabel.lastIndexOf(label);
    }

    /**
     * Returns an element at the specified position.
     *
     * @param row the row index.
     * @param label the label.
     * @return an object at the specified position; returns null if the label is not found.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getRows()).
     */
    public Object get(int row, String label) throws IndexOutOfBoundsException {
        int column = labelIndexOf(label);
        return ((column >= 0) ? super.get(row, column) : null);
    }

    /**
     * Replaces the element at the specified position in this table with the specified element.
     * There will be no effect if the label is not found.
     *
     * @param row an index of a row.
     * @param label the label.
     * @param obj an object to be stored at the specified position.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getRows()).
     */
    public void set(int row, String label, Object obj) {
        int column = labelIndexOf(label);
        if (column >= 0)
            super.set(row, column, obj);
    }

}
