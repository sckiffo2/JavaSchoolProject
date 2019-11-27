package com.voronov.controllers;

import com.voronov.entities.Station;
import com.voronov.service.serviceInterfaces.StationService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller()
@Setter
public class StationController {

	private static final Logger logger = LoggerFactory.getLogger(StationController.class);

	@Autowired
	@Qualifier("stationServiceImpl")
	private StationService stationService;

	@GetMapping("/station")
	public ModelAndView stations() {
		ModelAndView modelAndView = new ModelAndView();
		List<Station> stations = stationService.findAll();
		modelAndView.addObject("stations", stations);
		modelAndView.setViewName("station");
		logger.info("invoked /station page");
		return modelAndView;
	}

	@PostMapping("/station/save")
	public String save(@RequestParam String name) {
		stationService.save(name);
		logger.info("saving station :" + name);
		return "redirect:/station";
	}

	@GetMapping("/station/edit/{id}")
	public String updatePage(@PathVariable int id, Model model) {
		model.addAttribute(stationService.findById(id));
		logger.info("update station page invoked for:" + id);
		return "stationEdit";
	}

	@PostMapping("station/edit/updateStation")
	public String updateStation(@RequestParam String id,
						 @RequestParam String name) {
		Station station = stationService.findById(Integer.parseInt(id));
		station.setName(name);
		stationService.update(station);
		logger.info("updating station:" + id + " " + name);
		return "redirect:/station";
	}
}
