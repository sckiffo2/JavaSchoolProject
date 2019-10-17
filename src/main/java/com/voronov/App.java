package com.voronov;

import com.voronov.dao.StationDaoImpl;
import com.voronov.entities.Station;
import com.voronov.service.StationService;
import com.voronov.service.StationServiceImpl;

public class App {
	public static void main(String[] args) {
		StationService stationService = new StationServiceImpl();
		stationService.setStationDao(new StationDaoImpl());
		Station station = stationService.findByName("99");
		System.out.println(station);
	}
}
