package com.ibm.lcd.cfm.monitor.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class URLRequester {

	private final static String HEADER_COOKIE = "Cookie";
	private final static String HEADER_SET_COOKIE = "Set-Cookie";

	private static volatile String cookie = null;
	private boolean isPost = true;

	public final void setPostMethod(boolean flag) {
		this.isPost = flag;
	}

	public final boolean isPostMethod() {
		return this.isPost;
	}

	public final static void setCookie(String newCookie) {
		cookie = newCookie;
	}

	public final static String getCookie() {
		return cookie;
	}

	public final URLConnection request(String spec) throws IOException {
		return request(spec, null);
	}

	public final URLConnection request(String spec, Map map) throws IOException {
		boolean parameterExists = (map != null && !map.isEmpty());

		if (!isPost && parameterExists)
			spec = getURLString(spec, map);

		URL url = new URL(spec);
		URLConnection conn = url.openConnection();
		PrintWriter stream = null;

		try {
			conn.setAllowUserInteraction(false);
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			String currentCookie = getCookie();

			if (currentCookie != null) {
				conn.setRequestProperty(HEADER_COOKIE, currentCookie);
			}

			if (isPost && parameterExists) {
				String str = getParameterString(map);
				if (str.length() > 0) {
					stream = new PrintWriter(conn.getOutputStream());
					stream.print(str);
					stream.flush();
				}
			}

			if (conn instanceof HttpURLConnection) {
				int responseCode = ((HttpURLConnection) conn).getResponseCode();
				if (responseCode != HttpURLConnection.HTTP_OK)
					throw new IOException(responseCode + " " + ((HttpURLConnection) conn).getResponseMessage());
			}

			if (currentCookie != null) {
				setCookie(currentCookie);
			} else if ((currentCookie = conn.getHeaderField(HEADER_SET_COOKIE)) != null) {
				setCookie(currentCookie);
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (Exception ex) {
				}
			}
		}
		return conn;
	}

	public final static String getURLString(String spec, Map map) {

		StringBuffer sb = new StringBuffer();
		if (spec != null && spec.length() > 0) {
			sb.append(spec);
			if (map != null) {
				String str = getParameterString(map);
				if (str.length() > 0) {
					if (spec.indexOf("?") >= 0) {
						if (!spec.endsWith("&"))
							sb.append('&');
					} else
						sb.append('?');
					sb.append(str);
				}
			}
		}
		return sb.toString();
	}

	private static String getParameterString(Map map) {

		StringBuffer sb = new StringBuffer();
		String name = null, value = null;
		Iterator iterator = map.keySet().iterator();

		boolean errorF = false;
		while (iterator.hasNext()) {
			try {
				name = (String) iterator.next();
				value = (String) map.get(name);
			} catch (Exception ex) {
				errorF = true;
			}
			if(errorF) continue;
			
			if (sb.length() > 0) sb.append('&');
			try {
				sb.append(URLEncoder.encode(name, "UTF-8"));
				sb.append('=');
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				errorF = true;
			}
			if(errorF) continue;
		}
		return sb.toString();
	}
}
