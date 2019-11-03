package com.voronov.controllers;


import com.voronov.service.serviceInterfaces.StationService;
import lombok.Setter;
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
	@Autowired
	private StationService stationService;

	@GetMapping("/scheduleOfStation")
	public String getSchedulePage(Model model) {
		model.addAttribute("stations", stationService.findAll());
		return "scheduleOfStation";
	}

	@PostMapping("/scheduleOfStation")
	public String save(@RequestParam String name,
					   @RequestParam String stringDate, Model model) {
		LocalDate date = null;
		try {
			date = LocalDate.parse(stringDate);
		} catch (Exception e) {
			throw new IllegalArgumentException("Введена неверная дата");
		}
		long id = stationService.findByName(name).getId();

		model.addAttribute("station", name);
		model.addAttribute("date", stringDate);
		model.addAttribute("schedule", stationService.getScheduleOfStation(id, date));
		model.addAttribute("stations", stationService.findAll());
		return "scheduleOfStation";
	}
}