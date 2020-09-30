package com.ibm.lcd.ppt.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ibm.cxl.struct.DefaultStructFactory;
import com.ibm.cxl.struct.StructElement;
import com.ibm.cxl.struct.StructParser;
import com.ibm.cxl.struct.StructTransformer;
import com.ibm.cxl.util.PropertiesWrapper;
import com.ibm.lcd.commons.LcdConfig;

/**
 * This class is an abstract class which provides functions for PPT process.
 * 
 * @author user
 */
public abstract class PPTAction extends Action {

	// -------------------------------------------------------- Class Variables

	/** Configuration. */
	protected static PropertiesWrapper config = LcdConfig.getConfig();

	/** Structure factory. */
	protected static DefaultStructFactory structFactory = DefaultStructFactory.getInstance(config);


	/** Paramters for message service. */
	private static boolean usexml = config.getBoolean(LcdConfig.MS_USEXML, true);

	// ------------------------------------------------------ Protected Methods


	protected StructElement exchange(String sendName, StructElement struct,
			String replyName, String replyStruct) throws Exception {

		// Convert the specified structure to an array of bytes.
		byte[] data = null;

		if (usexml) {
			StructTransformer trans = new StructTransformer();
			//trans.setEncode("GB2312");
			data = trans.toByteArray(struct);
			
		} else {
			data = struct.getBytes();
		}
		long start = System.currentTimeMillis();
		data = TrxXML.sendRecv(sendName, data);
		long end = System.currentTimeMillis();
		System.out.println("TX Time Cost(ms): "+ String.valueOf(end-start));
		if (data != null) {

			if (usexml) {
				StructParser parser = new StructParser();
				parser.setConvertXML(true);
				struct = parser.parse(data);

			} else {
				struct = structFactory.getStruct(replyStruct);
				struct.setBytes(data);
			}
		} else {
			struct = null;
		}
		return struct;
	}


	protected ActionForward checkResult(ActionMapping mapping,
			HttpServletRequest request, StructElement struct) {
		if (struct == null) {
			return mapping.findForward("ppt_transactionTimeout");
		}
		long n = struct.getLong("rtn_code", -1);
		if (n != 0) {

			request.setAttribute("result", struct);
			return mapping.findForward("ppt_transactionError");
		}
		return null;
	}

}
