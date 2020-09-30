
package com.ibm.lcd.cfm.monitor.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;

public class CSVReceiver extends URLRequester {
	final String CLASS_NAME = "CSVReceiver";
    public CSVReceiver() {
        super();
    }

    public final GenericTable receive(String spec) throws IOException {
        return receiveArray(spec, null, true)[0];
    }

    public final GenericTable receive(String spec, Map map) throws IOException {
        return receiveArray(spec, map, true)[0];
    }

    public final GenericTable[] receiveArray(String spec) throws IOException {
        return receiveArray(spec, null, false);
    }

    public final GenericTable[] receiveArray(String spec, Map map) throws IOException {
        return receiveArray(spec, map, false);
    }


    private final GenericTable[] receiveArray(String spec, Map map, boolean singleFlag) throws IOException {
    	
        ArrayList list = new ArrayList();
        URLConnection conn = null;
        BufferedReader reader = null;
        
        try {        	
        	//System.out.println(METHOD_NAME+" spec: "+spec);
            conn = request(spec, map);

            int idx, len, start, end, column, row = 0;
            char[] array = null;
            String str = null, line = null;
            GenericTable tbl = new GenericTable();
            list.add(tbl);
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
          	
                if (!singleFlag && line.length() <= 0) {
                    tbl = null;
                    continue;
                }
                if (tbl == null) {
                    row = 0;
                    tbl = new GenericTable();
                    list.add(tbl);                   
                }

                row++;
                tbl.addRow();
                array = line.toCharArray();
             
                len = array.length;
                idx = start = end = column = 0;
                while (idx < len) {
                    for (; idx < len && Character.isSpaceChar(array[idx]); idx++);
                    if (idx >= len)
                        end = len;
                    else {
                        if (array[idx] == '"') {
                            start = idx + 1;
                            for (idx += 1; idx < len && array[idx] != '"'; idx++);
                            end = idx;
                            for (idx += 1; idx < len && array[idx] != ','; idx++);
                        }
                        else {
                            for (; idx < len && array[idx] != ','; idx++);
                            end = idx;
                        }
                    }
                    str = new String(array, start, end - start);
                    column++;
                    if (tbl.getColumns() < column)
                        tbl.addColumn();
                    tbl.set(row - 1, column - 1, str);

                    idx++;
                    start = idx;
                }
            }
            
        }
        catch (IOException ex) {
            throw ex;
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (Exception ex) {}
            }
            else if (conn != null) {
                try {
                    conn.getInputStream().close();
                }
                catch (Exception ex) {}
            }
        }

        GenericTable[] tables = new GenericTable[1];
        return (GenericTable[])list.toArray(tables);
    }
}
