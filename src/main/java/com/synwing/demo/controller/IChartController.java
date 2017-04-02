package com.synwing.demo.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/iChart")
public class IChartController {
	
	@RequestMapping({"/testList"})
	public String testList(Model model) {
		//model.addAttribute("users",users);
		return "ichart/testList";
	}
	//用html5 datetime-local选择时间
	@RequestMapping({"/testList01"})
	public String testList01(Model model) {
		//model.addAttribute("users",users);
		return "ichart/testList_01";
	}
	
	
	
}
