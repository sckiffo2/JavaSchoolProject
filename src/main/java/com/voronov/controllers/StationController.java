package com.voronov.controllers;

import com.voronov.entities.Station;
import com.voronov.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class StationController {

	private StationService stationService;

	@Autowired
	@Qualifier(value="stationService")
	public void setPersonService(StationService stationService){
		this.stationService = stationService;
	}

	@GetMapping("/station")
	public String stations(Model model) {
		return "station";
	}

	@PostMapping("/station")
	public String add(@RequestParam String name, Model model) {
		Station station = new Station(name);
		stationService.saveStation(station);
		return "station";
	}
}
