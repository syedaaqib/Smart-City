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
 * @struts.form name="busRouteForm"
 */
public class BusRouteForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** destination property */
	private String destination;

	/** via property */
	private String via;

	/** origin property */
	private String origin;

	/** cityid property */
	private int cityid;

	/** cityname property */
	private String cityname;

	/** busno property */
	private String busno;
	

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
	 * Returns the destination.
	 * @return String
	 */
	public String getDestination() {
		return destination;
	}

	/** 
	 * Set the destination.
	 * @param destination The destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/** 
	 * Returns the via.
	 * @return String
	 */
	public String getVia() {
		return via;
	}

	/** 
	 * Set the via.
	 * @param via The via to set
	 */
	public void setVia(String via) {
		this.via = via;
	}

	/** 
	 * Returns the origin.
	 * @return String
	 */
	public String getOrigin() {
		return origin;
	}

	/** 
	 * Set the origin.
	 * @param origin The origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
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
	 * Returns the busno.
	 * @return String
	 */
	public String getBusno() {
		return busno;
	}

	/** 
	 * Set the busno.
	 * @param busno The busno to set
	 */
	public void setBusno(String busno) {
		this.busno = busno;
	}
}