package com.voronov.controllers;

import com.voronov.service.serviceInterfaces.TicketService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
						   HttpSession session,
						   Model model) {

		if (!departureStation.equals("") && !arrivalStation.equals("") && !stringDate.equals("")) {
			session.setAttribute("departureStation", departureStation);
			session.setAttribute("arrivalStation", arrivalStation);
			model.addAttribute("stations", ticketService.findAllStations());
			LocalDate date = LocalDate.parse(stringDate);
			model.addAttribute("TicketScheduleDTO", ticketService.findTripsByStationsAndDate(departureStation, arrivalStation, date));
		} else {
			return "ErrorPage";
		}
		return "tripSearch";
	}

	@GetMapping("/freeplaces/{tripId}")
	public String getFreePlaces(@PathVariable int tripId,
								HttpSession session,
								Model model) {
		String departureStation = (String) session.getAttribute("departureStation");
		String arrivalStation = (String) session.getAttribute("arrivalStation");
		model.addAttribute("tripPlaces", ticketService.findFreePlaces(tripId,departureStation, arrivalStation));
		return "freePlaces";
	}

}
