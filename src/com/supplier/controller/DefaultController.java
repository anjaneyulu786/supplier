package com.supplier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.supplier.constraints.PageView;
import com.supplier.dao.SupplierDAO;
import com.supplier.model.Supplier;

@Controller
public class DefaultController {

	@Autowired
	SupplierDAO supplierDAO;

	@RequestMapping(value = { "/", "/index" })
	public String index(ModelMap modelMap) {
		return PageView.INDEX;
	}

	@RequestMapping(value = { "/login" })
	public String login(ModelMap modelMap) {
		return PageView.LOGIN;
	}

	@RequestMapping(value = { "/signUp" })
	public String signUp(ModelMap modelMap) {
		return PageView.SIGNUP;
	}

	@RequestMapping(value = { "/items" })
	public String items(ModelMap modelMap) {
		return PageView.MENU;
	}

	@RequestMapping(value = { "/orders" })
	public String orders(ModelMap modelMap) {
		return PageView.ORDERS;
	}

	@RequestMapping(value = "/supplierSignUp")
	public String supplierSignUp(ModelMap modelMap, Supplier supplier) {
		if (supplier.getUserName() != null && supplier.getPassword() != null && supplier.getLocation() != null
				&& supplier.getMobileNumber() != null) {
			boolean isCreated = supplierDAO.supplierSignUp(supplier);
			if (isCreated) {
				modelMap.put("isCreated", isCreated);
			}
		}
		return "";
	}

	@RequestMapping(value = "/supplierLogin")
	public String supplierLogin(ModelMap modelMap, Supplier supplier) {

		if (supplier.getUserName() != null && supplier.getPassword() != null) {
			Supplier supplierDetails = supplierDAO.getSupplierDetails(supplier);
			if (supplierDetails != null) {
				modelMap.put("supplierDetails", supplierDetails);
			}
		}
		return "";
	}

}
