package com.voronov.service;


import com.voronov.dao.StationDao;
import com.voronov.entities.Station;

import java.util.List;

public class StationService {

	private StationDao stationDao = new StationDao();

	public StationService() {
	}

	public Station findStation(int id) {
		return stationDao.findById(id);
	}

	public void saveStation(Station station) {
		stationDao.save(station);
	}

	public void updateStation(Station station) {
		stationDao.update(station);
	}

	public void deleteStation(Station station) {
		stationDao.delete(station);
	}

	public List<Station> findAllStations() {
		return stationDao.findAll();
	}
}
