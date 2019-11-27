package com.voronov.controllers;


import com.voronov.service.serviceInterfaces.StationService;
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

@Controller()
@Setter
public class ScheduleController {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	@Autowired
	private StationService stationService;

	@GetMapping("/scheduleOfStation")
	public String getSchedulePage(Model model) {
		model.addAttribute("stations", stationService.findAll());
		logger.info("invoked /scheduleOfStation");
		return "scheduleOfStation";
	}

	@PostMapping("/scheduleOfStation")
	public String save(@RequestParam String name,
					   @RequestParam String stringDate, Model model) {
		LocalDate date;
		try {
			date = LocalDate.parse(stringDate);
		} catch (Exception e) {
			throw new IllegalArgumentException("Введена неверная дата");
		}

		model.addAttribute("station", name);
		model.addAttribute("date", stringDate);
		model.addAttribute("schedule", stationService.getScheduleOfStation(name, date));
		model.addAttribute("stations", stationService.findAll());
		logger.info("invoked /scheduleOfStation for station :" + name + " at " + date);
		return "scheduleOfStation";
	}
}