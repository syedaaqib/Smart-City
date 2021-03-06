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

import com.smart.struts.dao.HotelInfoDao;
import com.smart.struts.form.HotelInfoForm;
import com.smart.struts.form.HotelInfosForm;


/** 
 * MyEclipse Struts
 * Creation date: 09-09-2011
 * 
 * XDoclet definition:
 * @struts.action path="/hotelInfo" name="hotelInfoForm" input="/form/hotelInfo.jsp" scope="request" validate="true"
 * @struts.action-forward name="success" path="/hotel.jsp"
 * @struts.action-forward name="failure" path="/hotel.jsp"
 */
public class HotelInfoAction extends Action {
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
		HotelInfosForm ab = (HotelInfosForm) form;// TODO Auto-generated method stub
		HotelInfoDao sd=new HotelInfoDao();
	    boolean flag=sd.hotelinfo_insert(ab);
		if(flag)
			return mapping.findForward("success");
		else
			return mapping.findForward("failure");
		}
	}
