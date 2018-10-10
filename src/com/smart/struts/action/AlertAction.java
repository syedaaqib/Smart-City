/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.smart.struts.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smart.struts.dao.CityInfoDao;
import com.smart.struts.form.AlertForm;

/** 
 * MyEclipse Struts
 * Creation date: 10-13-2011
 * 
 * XDoclet definition:
 * @struts.action path="/alert" name="alertForm" input="/form/alert.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/PostAlert.jsp"
 * @struts.action-forward name="failure" path="/index.jsp"
 */
public class AlertAction extends Action {
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
	 * @throws SQLException 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws SQLException {
		AlertForm af = (AlertForm) form;// TODO Auto-generated method stub
		boolean flag=new CityInfoDao().addAlert(af);
		if(flag)
			return mapping.findForward("success");
		else
			return mapping.findForward("failure");
	}
}