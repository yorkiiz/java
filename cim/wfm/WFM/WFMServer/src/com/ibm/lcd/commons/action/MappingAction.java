/********************************************************************************/
/*  System :             WCFM 3.0                                               */
/*  File Name :          MappingAction.java                                     */
/*  File Description :   マッピングアクションクラス                             */
/*                                                                              */
/*  Classes :            MappingAction                                          */
/*   DataColumn                                                                 */
/*                                                                              */
/*  5100-0317 (C) Copyright IBMJapan Industrial Solutions, Co., LTD. 2004       */
/*            (C) Copyright IBM Corp. 2004                                      */
/*                                                                              */
/*  Modification History :                                                      */
/*    Date        Level  Modified By       Description                          */
/*    ----------  -----  ----------------  -----------------------------        */
/*    unknown     S000   unknown           First Version                        */
/*    03/24/2005  S001   H.Inada           ログレベル変更対応                   */
/*                                                                              */
/********************************************************************************/
package com.ibm.lcd.commons.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * An abstract Action that dispatches to a public method that is named by the request action mapping.
 * 
 * @author user
 */
public abstract class MappingAction extends Action {

	// -------------------------------------------------------- Class Variables

	/** Commons loggin instance. */
	private static Log log = LogFactory.getLog(MappingAction.class);

	/** The set of argument type classes for the reflected method call. */
	protected static final Class[] types = {ActionMapping.class, ActionForm.class, HttpServletRequest.class, HttpServletResponse.class};

	// ----------------------------------------------------- Instance Variables

	/** The Class instance of this class. */
	protected Class clazz = this.getClass();

	/** The set of Method objects introspected for this class. */
	protected HashMap methods = new HashMap();

	// --------------------------------------------------------- Public Methods

	/**
	 * Processes the specified HTTP request, and create the corresponding HTTP response.
	 * Returns an ActionForward instance describing where and how control should be forwarded,
	 * or null if the response has already been completed.
	 * 
	 * @param mapping The ActionMapping used to select this instance.
	 * @param form The optional ActionForm bean for this request (if any)
	 * @param request The HTTP request we are processing.
	 * @param response The HTTP response we are creating.
	 * @return the ActionForward object, or null.
	 * @exception Exception if an exception occurs.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// Instrospect the method with the specified name.
		String name = mapping.getPath();
		int i = name.lastIndexOf('/');
		if (i >= 0)
			name = name.substring(i + 1);
		Method method = null;
		try {
			method = getMethod(name);
		} catch (NoSuchMethodException ex) {
			method = null;
		}
		if (method == null) {
			try {
				method = getMethod("unspecified");
			} catch (NoSuchMethodException ex) {
//S001				log.error("Dispatch error '" + name + "'", ex);
				log.warn("Dispatch error '" + name + "'", ex);//S001
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return null;
			}
		}

		// Invoke the introspected method.
		ActionForward forward = null;
		try {
			Object[] args = {mapping, form, request, response};
			forward = (ActionForward)method.invoke(this, args);
		} catch (ClassCastException ex) {
//S001				log.error("Dispatch error '" + name + "'", ex);
			log.warn("Dispatch error '" + name + "'", ex);//S001
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		} catch (IllegalAccessException ex) {
//S001				log.error("Dispatch error '" + name + "'", ex);
			log.warn("Dispatch error '" + name + "'", ex);//S001
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		} catch (InvocationTargetException ex) {
//S001				log.error("Dispatch error '" + name + "'", ex);
			log.warn("Dispatch error '" + name + "'", ex);//S001
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return null;
		}

		return forward;
	}

	/**
	 * Method which is dispatched to when there is no dispatched method.
	 * 
	 * @param mapping The ActionMapping used to select this instance.
	 * @param form The optional ActionForm bean for this request (if any)
	 * @param request The HTTP request we are processing.
	 * @param response The HTTP response we are creating.
	 * @return the ActionForward object, or null.
	 * @exception Exception if an exception occurs.
	 */
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

//S001		log.error("Default dispatch method was invoked.");
		log.warn("Default dispatch method was invoked.");//S001
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return null;
	}

	// ------------------------------------------------------ Protected Methods

	/**
	 * Introspects the current class to identify a method of the specified name.
	 * 
	 * @param name The name of the method.
	 * @return a Method object.
	 * @exception NoSuchMethodException if no such method can be found.
	 */
	protected Method getMethod(String name)
		throws NoSuchMethodException {

		synchronized (methods) {
			Method method = (Method)methods.get(name);
			if (method == null) {
				method = clazz.getMethod(name, types);
				methods.put(name, method);
			}
			return method;
		}
	}

}
