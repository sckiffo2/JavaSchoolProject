package com.voronov.controllers;

import com.voronov.dto.RemoteScheduleDto;
import com.voronov.entities.Station;
import com.voronov.service.serviceInterfaces.RemoteScheduleService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController()
@Setter
public class RemoteScheduleController {

	private static final Logger logger = LoggerFactory.getLogger(RemoteScheduleController.class);

	@Autowired
	private RemoteScheduleService remoteScheduleService;

	@RequestMapping(value = "/getSchedule", produces = APPLICATION_JSON_VALUE ,method = RequestMethod.GET)
	public List<RemoteScheduleDto> getInitialSchedule(@RequestParam Long id) {
		logger.info("remoteSchedule invoked getInitialSchedule of station with id:" + id);
		return remoteScheduleService.getSchedule(id);
	}

	@RequestMapping(value = "/getStation", produces = APPLICATION_JSON_VALUE ,method = RequestMethod.GET)
	public Station getStation(@RequestParam Long id) {
		logger.info("remoteSchedule invoked getStation with id:" + id);
		return remoteScheduleService.getStation(id);
	}

	@RequestMapping(value = "/getStations", produces = APPLICATION_JSON_VALUE ,method = RequestMethod.GET)
	public List<Station> getStations() {
		logger.info("remoteSchedule invoked getStations");
		return remoteScheduleService.getAllStations();
	}
}