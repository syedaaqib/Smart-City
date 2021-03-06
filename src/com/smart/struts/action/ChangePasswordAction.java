/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.smart.struts.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.smart.struts.dao.SecurityDAO;
import com.smart.struts.form.RegisterForm;

/** 
 * MyEclipse Struts
 * Creation date: 08-17-2011
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="success" path="/ChangePassword.jsp"
 * @struts.action-forward name="failure" path="/ChangePassword.jsp"
 */
public class ChangePasswordAction extends Action {
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
	          RegisterForm rf=(RegisterForm)form;
	          HttpSession session=request.getSession();
	          rf.setNewPassword(request.getParameter("newpassword"));
	          System.out.println("nnnn"+rf.getNewPassword());
	          boolean flag=new SecurityDAO().changePassword(rf);
	          String pathstring="LoginForm.jsp";
	          if(((String)session.getAttribute("role")).equals("admin"))
	            pathstring="admin";
	          else if(((String)session.getAttribute("role")).equals("emp"))
	            pathstring="emp";
	          else
	        	  pathstring="ngo";
	          if(flag) 
	             return mapping.findForward(pathstring);
	          else  
	              return mapping.findForward(pathstring);  
	}
}