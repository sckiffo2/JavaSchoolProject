package com.voronov.controllers;

import com.voronov.entities.Route;
import com.voronov.service.serviceInterfaces.TripService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("routes", tripService.findAllRoutes());
		model.addAttribute("trips", tripService.findAllActualTrips());
		return "trip";
	}

	@PostMapping("/trip/add")
	public String save(@RequestParam String routeNumber,
					   @RequestParam String date,
					   Model model) {
		LocalDate localDate;
		try {
			localDate = LocalDate.parse(date);
		} catch (Exception e) {
			throw new IllegalArgumentException("Введена неверная дата");
		}

		tripService.createTrip(routeNumber, localDate);

		List<Route> routes = tripService.findAllRoutes();
		model.addAttribute("routes", routes);
		return "redirect:/trip";
	}

	@GetMapping("/trip/edit/{id}")
	public String editTrip(@PathVariable long id, Model model) {
		model.addAttribute(tripService.findById(id));
		return "tripEdit";
	}

	@PostMapping("/trip/edit/tripUpdate")
	public String save(@RequestParam long id,
					   @RequestParam boolean canceled,
					   @RequestParam int delay,
					   Model model) {
		tripService.update(id, canceled, delay);
		return "redirect:/trip";
	}
}