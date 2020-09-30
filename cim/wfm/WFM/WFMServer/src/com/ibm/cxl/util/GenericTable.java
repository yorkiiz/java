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

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a table contains objects.
 *
 * @author IBM Developer
 * @version $Revision 1.0 $ $Date: 2003/01/01 %
 */
public class GenericTable implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2136904737608539618L;
	private int iColumns;
    private ArrayList lstRow;

    /**
     * Constructs a new, empty object.
     *
     */
    public GenericTable() {
        iColumns = 0;
        lstRow = new ArrayList();
    }

    /**
     * Constructs an object with the specified table.
     *
     * @param tbl the table.
     */
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

    /**
     * Returns the number of rows in this table.
     *
     * @return the number of rows in this table.
     */
    public int getRows() {
        return lstRow.size();
    }

    /**
     * Returns the number of columns in this table.
     *
     * @return the number of columns in this table.
     */
    public int getColumns() {
        return iColumns;
    }

    /**
     * Appends a new row to the end of this table.
     *
     */
    public void addRow() {
        lstRow.add(newRow());
    }

    /**
     * Inserts a new row at the specified position in this table.
     *
     * @param index an index at which a new row is to be inserted.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getRows()).
     */
    public void addRow(int index) throws IndexOutOfBoundsException {
        lstRow.add(index, newRow());
    }

    /**
     * Inserts new rows with the specified size at the specified position in this table.
     *
     * @param index an index at which new rows are to be inserted.
     * @param size the size of rows.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getRows()).
     */
    public void addRow(int index, int size) throws IndexOutOfBoundsException {
        for (int i = 0; i < size; i++)
            lstRow.add(index, newRow());
    }

    /**
     * Removes a row at the specified position in this table.
     *
     * @param index an index of the row to be removed.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getRows()).
     */
    public void removeRow(int index) throws IndexOutOfBoundsException {
        lstRow.remove(index);
    }

    /**
     * Appends a new column to the end of this table.
     *
     */
    public void addColumn() {
        for (int i = lstRow.size() - 1; i >= 0; i--)
            ((ArrayList)lstRow.get(i)).add(null);
        iColumns++;
    }

    /**
     * Inserts a new column at the specified position in this table.
     *
     * @param index an index at which a new column is to be inserted.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getCols()).
     */
    public void addColumn(int index) throws IndexOutOfBoundsException {
        for (int i = lstRow.size() - 1; i >= 0; i--)
            ((ArrayList)lstRow.get(i)).add(index, null);
        iColumns++;
    }

    /**
     * Inserts new columns with the specified size at the specified position in this table.
     *
     * @param index an index at which new columns are to be inserted.
     * @param size the size of columns.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getCols()).
     */
    public void addColumn(int index, int size) throws IndexOutOfBoundsException {
        ArrayList lst = null;
        int j = 0;
        for (int i = lstRow.size() - 1; i >= 0; i--) {
            lst = (ArrayList)lstRow.get(i);
            for (j = 0; j < size; j++)
                lst.add(index, null);
        }
        if (size > 0)
            iColumns += size;
    }

    /**
     * Removes a column at the specified position in this table.
     *
     * @param index an index of the column to be removed.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getCols()).
     */
    public void removeColumn(int index) throws IndexOutOfBoundsException {
        for (int i = lstRow.size() - 1; i >= 0; i--)
            ((ArrayList)lstRow.get(i)).remove(index);
        iColumns--;
    }

    /**
     * Removes all of the elements from this table.
     *
     */
    public void clear() {
        lstRow.clear();
        iColumns = 0;
    }

    /**
     * Returns an element at the specified position.
     *
     * @param row the row index.
     * @param column the column index.
     * @return an object at the specified position.
     * @exception IndexOutOfBoundsException if index is out of range.
     */
    public Object get(int row, int column) throws IndexOutOfBoundsException {
        return ((ArrayList)lstRow.get(row)).get(column);
    }

    /**
     * Replaces the element at the specified position in this table with the specified element.
     *
     * @param row the row index.
     * @param column the column index.
     * @param obj an object to be stored at the specified position.
     * @exception IndexOutOfBoundsException if index is out of range.
     */
    public void set(int row, int column, Object obj) throws IndexOutOfBoundsException {
        ((ArrayList)lstRow.get(row)).set(column, obj);
    }

    /**
     * Searches for the first occurence of the given argument, testing for equality using the equals method.
     *
     * @param column the column index.
     * @param obj an object.
     * @return the row index of the first occurrence of the argument in this table; returns -1 if the object is not found.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getCols()).
     */
    public int rowIndexOf(int column, Object obj) throws IndexOutOfBoundsException {
        Object columnObj = null;
        int i, j;
        for (i = 0, j = lstRow.size(); i < j; i++) {
            columnObj = ((ArrayList)lstRow.get(i)).get(column);
            if ((columnObj != null && columnObj.equals(obj)) || (columnObj == null && obj == null))
                break;
        }
        return ((i < j) ? i : -1);
    }

    /**
     * Returns the row index of the last occurrence of the specified object in this table.
     *
     * @param column the column index.
     * @param obj an object.
     * @return the row index of the first occurrence of the argument in this table; returns -1 if the object is not found.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getCols()).
     */
    public int rowLastIndexOf(int column, Object obj) throws IndexOutOfBoundsException {
        Object columnObj = null;
        int i = 0;
        for (i = lstRow.size() - 1; i >= 0; i--) {
            columnObj = ((ArrayList)lstRow.get(i)).get(column);
            if ((columnObj != null && columnObj.equals(obj)) || (columnObj == null && obj == null))
                break;
        }
        return i;
    }

    /**
     * Searches for the first occurence of the given argument, testing for equality using the equals method.
     *
     * @param row the row index.
     * @param obj an object.
     * @return the column index of the first occurrence of the argument in this table; returns -1 if the object is not found.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getRows()).
     */
    public int columnIndexOf(int row, Object obj) throws IndexOutOfBoundsException {
        return ((ArrayList)lstRow.get(row)).indexOf(obj);
    }

    /**
     * Returns the column index of the last occurrence of the specified object in this table.
     *
     * @param row the row index.
     * @param obj an object.
     * @return the column index of the first occurrence of the argument in this table; returns -1 if the object is not found.
     * @exception IndexOutOfBoundsException if index is out of range (index < 0 || index >= getRows()).
     */
    public int columnLastIndexOf(int row, Object obj) throws IndexOutOfBoundsException {
        return ((ArrayList)lstRow.get(row)).lastIndexOf(obj);
    }

    /**
     * Returns a new row.
     *
     * @return an ArrayList object represents a new row.
     */
    private ArrayList newRow() {
        ArrayList lst = new ArrayList();
        for (int i = 0; i < iColumns; i++)
            lst.add(null);
        return lst;
    }

} // end of class