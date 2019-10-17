package com.voronov.service;

import com.voronov.dao.DAOinterfaces.StationDao;
import com.voronov.entities.Station;

import java.util.List;

public interface StationService {
	void setStationDao(StationDao stationDao);

	Station findById(int id);

	Station findByName(String name);

	void save(Station station);

	void update(Station station);

	void delete(Integer id);

	List<Station> findAll();
}
