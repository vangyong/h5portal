package com.synwing.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.synwing.demo.service.UserinfoService;
import com.synwing.demo.service.UsersService;


@Controller
@RequestMapping("/websockets")
public class WebsocketsController {
	
	@Autowired
	@Qualifier("UsersService")
	private UsersService usersService;

	@Autowired
	@Qualifier("UserinfoService")
	private UserinfoService userinfoService;
	
	@RequestMapping({"/page"})
	public String page(Model model) {
		//model.addAttribute("users",users);
		int id = 1000;
		System.out.println(usersService.findUserAgeById(id));
		System.out.println(userinfoService.findUserInfoById(id));
		
		return "websockets/page";
	}
	
}
