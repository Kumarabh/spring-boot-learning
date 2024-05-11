package com.boot.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	// @RequestMapping on handler fn will return a view of type index.jsp
	// @ResponseBody on handler fn will return a string body
	
	@RequestMapping("/")
	public String testHandler() {
		System.out.println("Welcome to test");
		return "home"; // return home.jsp
	}
}
