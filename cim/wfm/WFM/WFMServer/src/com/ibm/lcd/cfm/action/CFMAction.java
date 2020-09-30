
package com.ibm.lcd.cfm.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.upload.MultipartIterator;
import org.apache.struts.upload.MultipartElement;

import com.ibm.cxl.util.PropertiesWrapper;
import com.ibm.lcd.commons.LcdConfig;

public abstract class CFMAction extends Action {

	protected static PropertiesWrapper config = LcdConfig.getConfig();

	protected Map getParametersMap(HttpServletRequest request) throws IOException {
		String contentType = request.getContentType();
		String method = request.getMethod();
		String name = null;
		String[] values = null;
		HashMap textElements = new HashMap();

		if ((contentType != null) && (contentType.startsWith("multipart/form-data")) && (method.equalsIgnoreCase("POST"))) 
		{
			MultipartIterator iterator = new MultipartIterator(request);
			MultipartElement element = null;
			while ((element = iterator.getNextElement()) != null) {
				name = element.getName();
				values = (String[])textElements.get(name);
				if (values != null) {
					String[] newValues = new String[values.length + 1];
					System.arraycopy(values, 0, newValues, 0, values.length);
					newValues[values.length] = element.isFile() ? element.getFileName() : element.getValue();
					values = newValues;
				}
				else {
					values = new String[1];
					values[0] = element.isFile() ? element.getFileName() : element.getValue();
				}
				textElements.put(name, values);
			}
		}
		else {
			Enumeration enum1 = request.getParameterNames();
			while (enum1.hasMoreElements()) {
				name = (String)enum1.nextElement();
				values = request.getParameterValues(name);
				textElements.put(name, values);
			}
		}
		return textElements;
	}

	protected void respondWithClass(HttpServletResponse response, byte[] data) throws IOException {
		respondWithData(response, data, "application/octet-stream");
	}

	protected void respondWithText(HttpServletResponse response, byte[] data) throws IOException {
		respondWithData(response, data, "text/plain");
	}

	protected void respondWithData(HttpServletResponse response, byte[] data, String type) throws IOException {
		ServletOutputStream stream = null;
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(type);
			response.setContentLength(data.length);
			stream = response.getOutputStream();
			stream.write(data);
			stream.flush();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException ex) {}
			}
		}
	}

}
