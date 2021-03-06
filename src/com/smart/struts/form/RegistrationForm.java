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
 * Creation date: 09-24-2011
 * 
 * XDoclet definition:
 * @struts.form name="registrationForm"
 */
public class RegistrationForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** password property */
	private String password;

	/** sex property */
	private String sex;

	/** login_id property */
	private String login_id;

	/** confirmpwd property */
	private String confirmpwd;

	/** security_id property */
	private String security_id;

	/** middle_name property */
	private String middle_name;

	/** illoftype property */
	private String illoftype;

	/** last_name property */
	private String last_name;

	/** sequrity_type property */
	private String sequrity_type;

	/** security_question property */
	private String security_question;

	/** name property */
	private String answer;

	/** first_name property */
	private String first_name;

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
	 * Returns the sex.
	 * @return String
	 */
	public String getSex() {
		return sex;
	}

	/** 
	 * Set the sex.
	 * @param sex The sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/** 
	 * Returns the login_id.
	 * @return String
	 */
	public String getLogin_id() {
		return login_id;
	}

	/** 
	 * Set the login_id.
	 * @param login_id The login_id to set
	 */
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	/** 
	 * Returns the confirmpwd.
	 * @return String
	 */
	public String getConfirmpwd() {
		return confirmpwd;
	}

	/** 
	 * Set the confirmpwd.
	 * @param confirmpwd The confirmpwd to set
	 */
	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}

	/** 
	 * Returns the security_id.
	 * @return String
	 */
	public String getSecurity_id() {
		return security_id;
	}

	/** 
	 * Set the security_id.
	 * @param security_id The security_id to set
	 */
	public void setSecurity_id(String security_id) {
		this.security_id = security_id;
	}

	/** 
	 * Returns the middle_name.
	 * @return String
	 */
	public String getMiddle_name() {
		return middle_name;
	}

	/** 
	 * Set the middle_name.
	 * @param middle_name The middle_name to set
	 */
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	/** 
	 * Returns the illoftype.
	 * @return String
	 */
	public String getIlloftype() {
		return illoftype;
	}

	/** 
	 * Set the illoftype.
	 * @param illoftype The illoftype to set
	 */
	public void setIlloftype(String illoftype) {
		this.illoftype = illoftype;
	}

	/** 
	 * Returns the last_name.
	 * @return String
	 */
	public String getLast_name() {
		return last_name;
	}

	/** 
	 * Set the last_name.
	 * @param last_name The last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/** 
	 * Returns the sequrity_type.
	 * @return String
	 */
	public String getSequrity_type() {
		return sequrity_type;
	}

	/** 
	 * Set the sequrity_type.
	 * @param sequrity_type The sequrity_type to set
	 */
	public void setSequrity_type(String sequrity_type) {
		this.sequrity_type = sequrity_type;
	}

	/** 
	 * Returns the security_question.
	 * @return String
	 */
	public String getSecurity_question() {
		return security_question;
	}

	/** 
	 * Set the security_question.
	 * @param security_question The security_question to set
	 */
	public void setSecurity_question(String security_question) {
		this.security_question = security_question;
	}

	/** 
	 * Returns the name.
	 * @return String
	 */
	
	/** 
	 * Returns the first_name.
	 * @return String
	 */
	public String getFirst_name() {
		return first_name;
	}

	/** 
	 * Set the first_name.
	 * @param first_name The first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}