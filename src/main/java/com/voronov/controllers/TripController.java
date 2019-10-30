package com.voronov.controllers;

import com.voronov.entities.Route;
import com.voronov.entities.Trip;
import com.voronov.service.serviceInterfaces.TicketService;
import com.voronov.service.serviceInterfaces.TripService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@Setter
public class TripController {

	@Autowired
	TripService tripService;

	@GetMapping("/trip")
	public String trips(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		List<Route> routes = tripService.findAllRoutes();
		model.addAttribute("routes", routes);
		return "trip";
	}

	@PostMapping("/trip/add")
	public String save(@RequestParam String routeNumber,
					   @RequestParam String date,
					   Model model) {
		LocalDate localDate = LocalDate.parse(date);
		tripService.createTrip(routeNumber, localDate);

		List<Route> routes = tripService.findAllRoutes();
		model.addAttribute("routes", routes);
		return "redirect:/trip";
	}
}