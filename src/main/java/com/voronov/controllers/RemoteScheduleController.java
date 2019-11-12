package com.voronov.controllers;

import com.voronov.dto.StationScheduleDTO;
import com.voronov.service.serviceInterfaces.StationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController()
@Setter
public class RemoteScheduleController {
	@Autowired
	private StationService stationService;

	@RequestMapping(value = "/getSchedule", method = RequestMethod.GET)
	public List<StationScheduleDTO> getInitialSchedule(@RequestParam String name,
													   @RequestParam String stringDate,
													   Model model) {
//		String name = "Москва";
//		LocalDate date = LocalDate.parse("2019-11-23");

		LocalDate date = null;
		try {
			date = LocalDate.parse(stringDate);
		} catch (Exception e) {

		}
		return stationService.getScheduleOfStation(name, date);
	}
}