package com.voronov.controllers;

import com.voronov.service.serviceInterfaces.PassengerService;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.TripService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Setter
public class PassengerController {

	private static final Logger logger = LoggerFactory.getLogger(PassengerController.class);

	@Autowired
	private PassengerService passengerService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private TripService tripService;

	@GetMapping("/passenger")
	public String getSchedulePage(Model model) {
		model.addAttribute("routes", routeService.findAll());

		return "passenger";
	}

	@PostMapping("getPassengers")
	public String save(@RequestParam String number,
					   @RequestParam String date,
								   Model model) {
		model.addAttribute("routes", routeService.findAll());
		if (!number.isEmpty() && !date.isEmpty()) {
			long routeId = routeService.findByNumber(number).getId();
			LocalDate localDate = LocalDate.parse(date);
			long tripId = tripService.findByRouteIdAndDate(routeId, localDate).getId();
			model.addAttribute("passengers",passengerService.findPassengersOnTrip(tripId));
			logger.info("checked passengers of trip:" + tripId);
		} else {
			return "ErrorDefault";
		}
		return "passenger";
	}
}