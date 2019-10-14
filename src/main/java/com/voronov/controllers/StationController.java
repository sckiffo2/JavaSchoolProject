package com.voronov.controllers;

import com.voronov.entities.Station;
import com.voronov.service.StationService;
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
public class StationController {

	private StationService stationService;

	@Autowired
	@Qualifier("stationServiceImpl")
	public void setPersonService(StationService stationService){
		this.stationService = stationService;
	}

	@GetMapping("/station")
	public ModelAndView stations() {
		ModelAndView modelAndView = new ModelAndView();
		List<Station> stations = stationService.findAll();
		modelAndView.addObject("stations", stations);
		modelAndView.setViewName("station");
		return modelAndView;
	}

	@PostMapping("/station/save")
	public String save(@RequestParam String name) {
		Station station = new Station(name);

		stationService.save(station);
		return "redirect:/station";
	}

	@GetMapping("/station/edit/{id}")
	public String updatePage(@PathVariable int id, Model model) {
		model.addAttribute(stationService.findById(id));
		return "stationEdit";
	}

	@PostMapping("station/edit/updateStation")
	public String update(@RequestParam String id,
						 @RequestParam String name) {
		Station station = stationService.findById(Integer.parseInt(id));
		station.setName(name);
		stationService.update(station);
		return "redirect:/station";
	}

	@GetMapping("/station/delete/{id}")
	public String delete(@PathVariable int id) {
		stationService.delete(id);
		return "redirect:/station";
	}
}
