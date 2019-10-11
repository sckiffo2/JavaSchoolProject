package com.voronov.controllers;

import com.voronov.entities.Station;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HelloController {

	@GetMapping("/")
	public String main(Model model) {
		return "index";
	}

	@GetMapping("/showViewPage")
	public String passParametersWithModelMap(ModelMap map) {
		map.addAttribute("welcomeMessage", "welcome");
		map.addAttribute("message", "Baeldung");
		return "viewPage";
	}
}
