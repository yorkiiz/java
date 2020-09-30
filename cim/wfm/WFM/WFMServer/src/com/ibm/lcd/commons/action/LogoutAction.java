
package com.ibm.lcd.commons.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


/**
 * Performs logout.
 * 
 * @author user
 */
public class LogoutAction extends Action {

	// -------------------------------------------------------- Class Variables
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		HttpSession session = request.getSession(false);
		if (session != null) {
			//String id = session.getId();
			session.invalidate();
		}
		return mapping.findForward("success");
	}

}
