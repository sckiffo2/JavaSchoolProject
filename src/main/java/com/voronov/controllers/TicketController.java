package com.voronov.controllers;

import com.voronov.service.serviceInterfaces.TicketService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Setter
public class TicketController {

	@Autowired
	TicketService ticketService;

	@GetMapping("/tripSearch")
	public String tripPage(Model model) {
		model.addAttribute("stations", ticketService.findAllStations());
		return "tripSearch";
	}

	@PostMapping("getTrip")
	public String findTrip(@RequestParam String departureStation,
						   @RequestParam String arrivalStation,
						   @RequestParam String stringDate,
									   Model model) {

		if (!departureStation.equals("") && !arrivalStation.equals("") && !stringDate.equals("")) {
			model.addAttribute("stations", ticketService.findAllStations());
			LocalDate date = LocalDate.parse(stringDate);
			model.addAttribute("trips", ticketService.findTripsByStationsAndDate(departureStation, arrivalStation, date));
		} else {
			return "ErrorPage";
		}
		return "tripSearch";
	}
}
