/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.smart.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 08-13-2011
 * 
 * XDoclet definition:
 * @struts.form name="LoginForm"
 */
public class LoginForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** password property */
	private String password;

	/** loginname property */
	private String loginname;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/** 
	 * Returns the password.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * Set the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 
	 * Returns the loginname.
	 * @return String
	 */
	public String getLoginname() {
		return loginname;
	}

	/** 
	 * Set the loginname.
	 * @param loginname The loginname to set
	 */
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
}