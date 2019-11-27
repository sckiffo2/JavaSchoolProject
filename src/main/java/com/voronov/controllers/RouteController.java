package com.voronov.controllers;

import com.voronov.entities.Route;
import com.voronov.service.serviceInterfaces.RouteService;
import com.voronov.service.serviceInterfaces.RouteStationService;
import com.voronov.service.serviceInterfaces.StationService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller()
@Setter
public class RouteController {

	private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

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
		logger.info("invoked /route routes found:" + routes.size());
		return modelAndView;
	}

	@PostMapping("/route/save")
	public String save(@RequestParam String number,
					   @RequestParam String name,
					   @RequestParam String pattern) {

		routeService.save(number, name, pattern);
		logger.info("added route with number:" + number);
		return "redirect:/route";
	}

	@GetMapping("/route/edit/{id}")
	public String updatePage(@PathVariable int id, Model model) {
		model.addAttribute("route", routeService.findById(id));
		logger.info("invoked /route/edit/" + id);
		return "routeEdit";
	}

	@PostMapping("route/edit/updateRoute")
	public String update(@RequestParam String id,
						 @RequestParam String number,
						 @RequestParam String name,
						 @RequestParam String pattern) {
		Route route = routeService.findById(Integer.parseInt(id));
		logger.info("updating route with number" + route.getNumber());
		route.setNumber(number);
		route.setName(name);
		route.setSchedulePattern(pattern);
		routeService.update(route);
		return "redirect:/route";
	}

	@RequestMapping(value = "/route/editStations/{id}", method = RequestMethod.GET)
	public String addStationsPage1(@PathVariable int id, ModelMap map) {

		map.addAttribute("id", id);
		map.addAttribute("stations", stationService.findAll());
		map.addAttribute("stationsOfRoute", routeStationService.findStationsOfRoute(id));

		return "editStations";
	}

	@GetMapping("route/editStations")
	public String addStationsPage(ModelMap map) {
		int id = 1;
		map.addAttribute("stations", stationService.findAll());
		map.addAttribute("stationsOfRoute", routeStationService.findStationsOfRoute(id));

		return "editStations";
	}

	@PostMapping("route/editStations/addStationToRoute")
	public String addStationToRoute(@RequestParam long id,
									@RequestParam String station,
									@RequestParam String arrival,
									@RequestParam String departure,
									@RequestParam String arrivalDay,
									@RequestParam String departureDay,
									RedirectAttributes redirectAttributes) {

		routeStationService.save(id, station, arrival, departure, arrivalDay, departureDay);
		logger.info("added station to route:" + station);
		return "redirect:/route/editStations/"+ id;
	}

	@GetMapping("/route/delete/{id}")
	public String delete(@PathVariable int id) {
		routeService.delete(id);
		logger.info("deleting route with id:" + id);
		return "redirect:/route/";
	}
}