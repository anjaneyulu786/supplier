package com.supplier.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.supplier.constraints.PageView;


@Controller
public class DefaultController {
	
	@RequestMapping(value = { "/", "/index" })
	public String index(ModelMap modelMap) {
		return PageView.INDEX;		
	}
	
	@RequestMapping(value = { "/login" })
	public String login(ModelMap modelMap) {
		return PageView.LOGIN;
	}
	
}
