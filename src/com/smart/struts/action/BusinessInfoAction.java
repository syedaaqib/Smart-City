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

import com.smart.struts.dao.BusinessInfoDao;
import com.smart.struts.form.BusInfoForm;
import com.smart.struts.form.BusinessInfoForm;

/** 
 * MyEclipse Struts
 * Creation date: 09-10-2011
 * 
 * XDoclet definition:
 * @struts.action path="/businessInfo" name="businessInfoForm" input="/form/businessInfo.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/business_info.jsp"
 * @struts.action-forward name="failure" path="/business_info.jsp"
 */
public class BusinessInfoAction extends Action {
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
	 * @throws FileNotFoundException 
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, SQLException {
		BusInfoForm bf = (BusInfoForm) form;// TODO Auto-generated method stub
		
	    boolean flag=new BusinessInfoDao().businessisert(bf);
		if(flag)
			return mapping.findForward("success");
		else
			return mapping.findForward("failure");
	}
}