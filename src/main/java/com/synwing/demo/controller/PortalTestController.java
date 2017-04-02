package com.synwing.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.synwing.demo.model.Userinfo;
import com.synwing.demo.model.Users;
import com.synwing.demo.service.UserinfoService;
import com.synwing.demo.vo.ResultVo;
import com.synwing.demo.vo.UserLoginVo;
import com.synwing.redis.entity.User;
import com.synwing.redis.service.impl.UserService;

@Controller
@RequestMapping("/portalTest")
public class PortalTestController {
	
	@Autowired
	private com.synwing.demo.service.UsersService UsersService;
	
	@Autowired
	private UserinfoService UserinfoService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/sendAjaxReq",method=RequestMethod.POST)
	public @ResponseBody ResultVo sendAjaxReq(@RequestBody UserLoginVo parameter) throws Exception{ 
		String userName = parameter.getUser_login_name();
		String passWord = parameter.getUser_password();
		Users users = new Users();
		users.setId(1);
		users.setAge(10);
		users.setName("wangyong");
		//UsersService.saveUsers(users);
		
		Userinfo userinfo = new Userinfo();
		userinfo.setId(2);
		userinfo.setInfo("info");
		userinfo.setUid(1);
		//UserinfoService.saveUserInfo(userinfo);
		
		User user = new User();
		user.setId("user1");
		user.setName("java2000_wl");
		userService.add(user);
		
		User user2 = userService.get("user1");
		
		
		ResultVo resultVo = new ResultVo();
		resultVo.setUserLoginVo(parameter);
		return resultVo;
	}
	
	@RequestMapping(value="/test1",method=RequestMethod.POST)
	public @ResponseBody ResultVo test1(@RequestBody UserLoginVo userLoginVo) throws Exception{ 
		UserLoginVo userLoginVo2 = userLoginVo;
		System.out.println("登录用户名："+userLoginVo.getUser_login_name()); 
		System.out.println("密码："+userLoginVo.getUser_password()); 
		ResultVo resultVo = new ResultVo();
		return resultVo;
	}

}
