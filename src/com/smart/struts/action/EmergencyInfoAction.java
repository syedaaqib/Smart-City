/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.smart.struts.action;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smart.struts.dao.EmergencyDao;
import com.smart.struts.form.EmergencyInfoForm;

/** 
 * MyEclipse Struts
 * Creation date: 09-12-2011
 * 
 * XDoclet definition:
 * @struts.action path="/emergencyInfo" name="emergencyInfoForm" input="/form/emergencyInfo.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/emergency.jsp"
 * @struts.action-forward name="failure" path="/emergency.jsp"
 */
public class EmergencyInfoAction extends Action {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws FileNotFoundException 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, SQLException {
		EmergencyInfoForm ef = (EmergencyInfoForm) form;// TODO Auto-generated method stub
		  boolean flag=new EmergencyDao().emergency_insert(ef);
			if(flag)
				return mapping.findForward("success");
			else
				return mapping.findForward("failure");
	}
}