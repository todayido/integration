package com.user.controller;

import com.sun.javafx.sg.PGShape;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.user.service.UserService;
import com.user.vo.User;

import java.util.List;
import java.util.concurrent.RunnableFuture;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value = "info")
	public ModelAndView getUserByUsername(@RequestParam String username){
		
		ModelAndView mav = new ModelAndView("user/userInfo");
		User user = userService.getUserByUsername(username);
		mav.addObject("user", user);
		
		return mav;
	}

	@RequestMapping(value = "list")
	//@RequiresRoles("admin")
	public ModelAndView getUserList(){

		ModelAndView mav = new ModelAndView("user/list");
		List userList = userService.getUserList();
		mav.addObject(userList);

		return mav;
	}

	@RequestMapping(value = "add")
	public String add(User user){
//		new Thread(){
//				public void run(){
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}.start();
        userService.add(user);
		return "user/add";
	}

	@RequestMapping(value = "delete")
	public String delete(){
		return "user/delete";
	}

	@RequestMapping(value = "update")
	public String update(){
		return "user/update";
	}

	@RequestMapping(value = "get")
	public String get(){
		return "user/get";
	}
}
