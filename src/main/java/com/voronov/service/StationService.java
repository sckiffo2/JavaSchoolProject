package com.voronov.service;


import com.voronov.dao.StationDao;
import com.voronov.entities.Station;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

	private StationDao stationDao = new StationDao();

	public StationService() {
	}

	public void setStationDao(StationDao stationDao) {
		this.stationDao = stationDao;
	}

	public Station findById(int id) {
		return stationDao.findById(id);
	}

	public Station findByName(String name) {
		return stationDao.findByName(name);
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
