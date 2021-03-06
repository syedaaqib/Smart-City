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
 * Creation date: 03-01-2011
 * 
 * XDoclet definition:
 * @struts.form name="stateForm"
 */
public class StateForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** governor property */
	private String governor;

	/** stateid property */
	private Integer stateid;

	/** statename property */
	private String statename;

	/** cm property */
	private String cm;

	/** capital property */
	private String capital;

	/** language property */
	private String language;

	/** sitemap property */
	private String sitemap;
	
	private String state_name;
	
	private String languages;

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
	 * Returns the governor.
	 * @return String
	 */
	public String getGovernor() {
		return governor;
	}

	/** 
	 * Set the governor.
	 * @param governor The governor to set
	 */
	public void setGovernor(String governor) {
		this.governor = governor;
	}

	/** 
	 * Returns the stateid.
	 * @return Integer
	 */
	public Integer getStateid() {
		return stateid;
	}

	/** 
	 * Set the stateid.
	 * @param stateid The stateid to set
	 */
	public void setStateid(Integer stateid) {
		this.stateid = stateid;
	}

	/** 
	 * Returns the statename.
	 * @return String
	 */
	public String getStatename() {
		return statename;
	}

	/** 
	 * Set the statename.
	 * @param statename The statename to set
	 */
	public void setStatename(String statename) {
		this.statename = statename;
	}

	/** 
	 * Returns the cm.
	 * @return String
	 */
	public String getCm() {
		return cm;
	}

	/** 
	 * Set the cm.
	 * @param cm The cm to set
	 */
	public void setCm(String cm) {
		this.cm = cm;
	}

	/** 
	 * Returns the capital.
	 * @return String
	 */
	public String getCapital() {
		return capital;
	}

	/** 
	 * Set the capital.
	 * @param capital The capital to set
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}

	/** 
	 * Returns the language.
	 * @return String
	 */
	public String getLanguage() {
		return language;
	}

	/** 
	 * Set the language.
	 * @param language The language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/** 
	 * Returns the sitemap.
	 * @return String
	 */
	public String getSitemap() {
		return sitemap;
	}

	/** 
	 * Set the sitemap.
	 * @param sitemap The sitemap to set
	 */
	public void setSitemap(String sitemap) {
		this.sitemap = sitemap;
	}

	public String getState_name() {
		return state_name;
	}

	public void setState_name(String state_name) {
		this.state_name = state_name;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}
}