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
 * Creation date: 03-01-2011
 * 
 * XDoclet definition:
 * @struts.form name="hotelInfosForm"
 */
public class HotelInfosForm extends ActionForm {
	/*
	 * Generated fields
	 */

	/** state_id property */
	private Integer state_id;

	/** hotel_name property */
	private String hotel_name;

	/** city_id property */
	private int city_id;

	/** address property */
	private String address;

	/** rating property */
	private String rating;

	/** phone_number property */
	private String phone_number;

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
	 * Returns the hotel_name.
	 * @return String
	 */
	public String getHotel_name() {
		return hotel_name;
	}

	/** 
	 * Set the hotel_name.
	 * @param hotel_name The hotel_name to set
	 */
	public void setHotel_name(String hotel_name) {
		this.hotel_name = hotel_name;
	}

	/** 
	 * Returns the city_id.
	 * @return String
	 */


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
	 * Returns the rating.
	 * @return String
	 */
	public String getRating() {
		return rating;
	}

	/** 
	 * Set the rating.
	 * @param rating The rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/** 
	 * Returns the phone_number.
	 * @return String
	 */
	public String getPhone_number() {
		return phone_number;
	}

	/** 
	 * Set the phone_number.
	 * @param phone_number The phone_number to set
	 */
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
}