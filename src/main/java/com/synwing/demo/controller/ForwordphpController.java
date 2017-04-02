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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/forwordphp")
public class ForwordphpController {
	
	
	
	@RequestMapping({"/getLineData"})
	public String getLineData(Model model) {
		//model.addAttribute("users",users);
		return "forwordphp/getLineData";
	}
	
	 @RequestMapping({"/getphpData"})  
     public String getphpData(Model model,RedirectAttributes redirectAttributes){  
	     //redirectAttributes.addFlashAttribute("firstName", user.getFirstName());  
	     //redirectAttributes.addFlashAttribute("lastName", user.getLastName())  
	     //return "redirect:success.html";  
		 return "redirect:http://127.0.0.1/linedata/get_line_data.php"; 
	 }  
	
	
}
