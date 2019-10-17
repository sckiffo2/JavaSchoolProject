package com.voronov.service;


import com.voronov.dao.DAOinterfaces.StationDao;
import com.voronov.entities.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {


	private StationDao stationDao;

	public StationServiceImpl() {
	}

	@Autowired
	@Override
	public void setStationDao(StationDao stationDao) {
		this.stationDao = stationDao;
	}

	@Override
	public Station findById(int id) {
		return stationDao.findById(id);
	}

	@Override
	public Station findByName(String name) {
		return stationDao.findByName(name);
	}

	@Override
	public void save(Station station) {
		stationDao.save(station);
	}

	@Override
	public void update(Station station) {
		stationDao.update(station);
	}

	@Override
	public void delete(Integer id) {
		stationDao.delete(id);
	}

	@Override
	public List<Station> findAll() {
		return stationDao.findAll();
	}
}
