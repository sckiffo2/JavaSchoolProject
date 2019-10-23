package com.voronov.controllers;

import com.voronov.entities.Route;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.RouteStationService;
import com.voronov.service.serviceInterfaces.StationService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller()
@Setter
public class RouteController {
	@Autowired
	@Qualifier("routeServiceImpl")
	private RouteService routeService;
	@Autowired
	@Qualifier("routeStationServiceImpl")
	private RouteStationService routeStationService;
	@Autowired
	@Qualifier("stationServiceImpl")
	private StationService stationService;

	@GetMapping("/route")
	public ModelAndView routes() {
		ModelAndView modelAndView = new ModelAndView();
		List<Route> routes = routeService.findAll();
		modelAndView.addObject("routes", routes);
		modelAndView.setViewName("route");
		return modelAndView;
	}

	@PostMapping("/route/save")
	public String save(@RequestParam String number,
					   @RequestParam String name,
					   @RequestParam String pattern) {
		Route route = new Route(number, name, pattern);

		routeService.save(route);
		return "redirect:/route";
	}

	@GetMapping("/route/edit/{id}")
	public String updatePage(@PathVariable int id, Model model) {
		model.addAttribute("route", routeService.findById(id));
		return "routeEdit";
	}

	@PostMapping("route/edit/updateRoute")
	public String update(@RequestParam String id,
						 @RequestParam String number,
						 @RequestParam String name) {
		Route route = routeService.findById(Integer.parseInt(id));
		route.setNumber(number);
		route.setName(name);
		routeService.update(route);
		return "redirect:/route";
	}

	@GetMapping("route/editStations/{id}")
	public String addStationsPage(@PathVariable int id, ModelMap map) {

		map.addAttribute("stations", stationService.findAll());
		map.addAttribute("stationsOfRoute", routeStationService.findStationsOfRoute(id));

		return "editStations";
	}

	@PostMapping("editStations")
	public String addStationToRoute(RedirectAttributes redirectAttributes) {
		System.out.println("--===tadam==---");
		return "redirect:/editStations";
	}

	@GetMapping("/route/delete/{id}")
	public String delete(@PathVariable int id) {
		routeService.delete(id);
		return "redirect:/route/";
	}
}