/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.smartcity.struts.form;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/** 
 * MyEclipse Struts
 * Creation date: 12-17-2011
 * 
 * XDoclet definition:
 * @struts.form name="businessInfoForm"
 */
public class BusinessInfoForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** phno property */
	private String phno;

	/** state_id property */
	private Integer state_id;

	/** comp_name property */
	private String comp_name;

	/** city_id property */
	private Integer city_id;

	/** address property */
	private String address;

	/** description property */
	private String description;

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
	 * Returns the phno.
	 * @return String
	 */
	public String getPhno() {
		return phno;
	}

	/** 
	 * Set the phno.
	 * @param phno The phno to set
	 */
	public void setPhno(String phno) {
		this.phno = phno;
	}

	/** 
	 * Returns the state_id.
	 * @return Integer
	 */
	public Integer getState_id() {
		return state_id;
	}

	/** 
	 * Set the state_id.
	 * @param state_id The state_id to set
	 */
	public void setState_id(Integer state_id) {
		this.state_id = state_id;
	}

	/** 
	 * Returns the comp_name.
	 * @return String
	 */
	public String getComp_name() {
		return comp_name;
	}

	/** 
	 * Set the comp_name.
	 * @param comp_name The comp_name to set
	 */
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	/** 
	 * Returns the city_id.
	 * @return Integer
	 */
	public Integer getCity_id() {
		return city_id;
	}

	/** 
	 * Set the city_id.
	 * @param city_id The city_id to set
	 */
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	/** 
	 * Returns the address.
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/** 
	 * Set the address.
	 * @param address The address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/** 
	 * Returns the description.
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/** 
	 * Set the description.
	 * @param description The description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}