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
 * Creation date: 09-09-2011
 * 
 * XDoclet definition:
 * @struts.form name="theatreForm"
 */
public class TheatreForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** phone property */
	private String phone;

	/** mattime property */
	private String mattime;

	/** mshotime property */
	private String mshotime;

	/** theatrename property */
	private String theatrename;

	/** address property */
	private String address;

	/** theatreid property */
	private int theatreid;

	/** firshowtime property */
	private String firshowtime;

	/** cinema property */
	private String cinema;

	/** cityid property */
	private int cityid;

	/** cityname property */
	private String cityname;

	/** secshowtime property */
	private String secshowtime;

	private String cinemaimg;
	/*
	 * Generated Methods
	 */

	public String getCinemaimg() {
		return cinemaimg;
	}

	public void setCinemaimg(String cinemaimg) {
		this.cinemaimg = cinemaimg;
	}

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
	 * Returns the phone.
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/** 
	 * Set the phone.
	 * @param phone The phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/** 
	 * Returns the mattime.
	 * @return String
	 */
	public String getMattime() {
		return mattime;
	}

	/** 
	 * Set the mattime.
	 * @param mattime The mattime to set
	 */
	public void setMattime(String mattime) {
		this.mattime = mattime;
	}

	/** 
	 * Returns the mshotime.
	 * @return String
	 */
	public String getMshotime() {
		return mshotime;
	}

	/** 
	 * Set the mshotime.
	 * @param mshotime The mshotime to set
	 */
	public void setMshotime(String mshotime) {
		this.mshotime = mshotime;
	}

	/** 
	 * Returns the theatrename.
	 * @return String
	 */
	public String getTheatrename() {
		return theatrename;
	}

	/** 
	 * Set the theatrename.
	 * @param theatrename The theatrename to set
	 */
	public void setTheatrename(String theatrename) {
		this.theatrename = theatrename;
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
	 * Returns the theatreid.
	 * @return int
	 */
	public int getTheatreid() {
		return theatreid;
	}

	/** 
	 * Set the theatreid.
	 * @param theatreid The theatreid to set
	 */
	public void setTheatreid(int theatreid) {
		this.theatreid = theatreid;
	}

	/** 
	 * Returns the firshowtime.
	 * @return String
	 */
	public String getFirshowtime() {
		return firshowtime;
	}

	/** 
	 * Set the firshowtime.
	 * @param firshowtime The firshowtime to set
	 */
	public void setFirshowtime(String firshowtime) {
		this.firshowtime = firshowtime;
	}

	/** 
	 * Returns the cinema.
	 * @return String
	 */
	public String getCinema() {
		return cinema;
	}

	/** 
	 * Set the cinema.
	 * @param cinema The cinema to set
	 */
	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

	/** 
	 * Returns the cityid.
	 * @return int
	 */
	public int getCityid() {
		return cityid;
	}

	/** 
	 * Set the cityid.
	 * @param cityid The cityid to set
	 */
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	/** 
	 * Returns the cityname.
	 * @return String
	 */
	public String getCityname() {
		return cityname;
	}

	/** 
	 * Set the cityname.
	 * @param cityname The cityname to set
	 */
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	/** 
	 * Returns the secshowtime.
	 * @return String
	 */
	public String getSecshowtime() {
		return secshowtime;
	}

	/** 
	 * Set the secshowtime.
	 * @param secshowtime The secshowtime to set
	 */
	public void setSecshowtime(String secshowtime) {
		this.secshowtime = secshowtime;
	}
}