package com.voronov.controllers;

import com.voronov.entities.Passenger;
import com.voronov.entities.Ticket;
import com.voronov.service.serviceInterfaces.TicketService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

	@Autowired
	TicketService ticketService;

	@GetMapping("/tripSearch")
	public String tripPage(Model model) {
		model.addAttribute("stations", ticketService.findAllStations());
		logger.info("/tripSearch page invoked");
		return "tripSearch";
	}

	@PostMapping("/tripSearch")
	public String findTrip(@RequestParam String departureStation,
						   @RequestParam String arrivalStation,
						   @RequestParam String stringDate,
						   HttpSession session, Model model) {
		session.setAttribute("departureStation", departureStation);
		session.setAttribute("arrivalStation", arrivalStation);
		model.addAttribute("stations", ticketService.findAllStations());
		model.addAttribute("TicketScheduleDTO", ticketService.findTripsByStationsAndDate(departureStation, arrivalStation, stringDate));
		logger.info("lets find trip for stations " + departureStation + arrivalStation + " at " + stringDate);
		return "tripSearch";
	}

	@GetMapping("/freeplaces/{tripId}")
	public String getFreePlaces(@PathVariable int tripId,
								HttpSession session, Model model) {
		String departureStation = (String) session.getAttribute("departureStation");
		String arrivalStation = (String) session.getAttribute("arrivalStation");
		session.setAttribute("tripId", tripId);
		model.addAttribute("tripPlaces", ticketService.findFreePlaces(tripId,departureStation, arrivalStation));
		logger.info("checking free places for trip id = " + tripId);
		return "freePlaces";
	}

	@GetMapping("/freeplaces/bookTicket/{wagon}/{place}")
	public String bookTicket(@PathVariable int wagon,
							 @PathVariable int place, HttpSession session, Model model) {
		String departureStation = (String) session.getAttribute("departureStation");
		String arrivalStation = (String) session.getAttribute("arrivalStation");
		int tripId = (int) session.getAttribute("tripId");
		ticketService.bookTicket(tripId, departureStation, arrivalStation, wagon, place);
		session.removeAttribute(departureStation);
		session.removeAttribute(arrivalStation);
		session.setAttribute("wagon", wagon);
		session.setAttribute("place", place);
		logger.info("booking ticket to trip:" + tripId + " place " + place + " in wagon " + wagon);
		return "regpassenger";
	}

	@PostMapping("/freeplaces/bookTicket/{tripId}/registerPassenger")
	public String registerPassenger(@RequestParam String firstName,
									@RequestParam String lastName,
									@RequestParam String birthDate,
									@RequestParam char gender, HttpSession session, Model model) {

		LocalDate date = LocalDate.parse(birthDate);
		Passenger passenger = new Passenger(firstName, lastName, date, gender);
		int tripId = (int) session.getAttribute("tripId");
		int wagon = (int) session.getAttribute("wagon");
		int place = (int) session.getAttribute("place");
		ticketService.registerPassengerToTrip(passenger, tripId, wagon, place);
		Ticket ticket = ticketService.findTicketByTripAndPlace(tripId, wagon, place);
		model.addAttribute("ticket", ticket);
		logger.info("registering passenger to trip " + tripId + " ticket " + ticket.getId() + firstName +
				" " + lastName + " " + birthDate);
		return "ticketSuccesRegister";
	}
}
