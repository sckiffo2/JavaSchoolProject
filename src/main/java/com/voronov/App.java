package com.voronov;

import com.voronov.entities.Station;
import com.voronov.service.StationService;

import java.util.List;

public class App {
	public static void main(String[] args) {
		StationService stationService = new StationService();
		stationService.saveStation(new Station("Станция"));
	}
}
