package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("twoController/")
public class TwoController {
	@RequestMapping("/two.html")
	public String twoUI(){
		return "test/two";
	}
}
