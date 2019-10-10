package com.voronov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

	@GetMapping("/")
	public String main(Model model) {
		return "index";
	}

	@GetMapping("/station")
	public String hello(Model model) {
		return "station";
	}

	@GetMapping("/showViewPage")
	public String passParametersWithModelMap(ModelMap map) {
		map.addAttribute("welcomeMessage", "welcome");
		map.addAttribute("message", "Baeldung");
		return "viewPage";
	}
}
