package com.voronov.service;

import com.voronov.dao.DAOinterfaces.StationDao;
import com.voronov.entities.Station;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class StationServiceImpl implements StationService {

	@Autowired
	private StationDao stationDao;

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
	public void delete(int id) {
		Station deleteStation = stationDao.findById(id);
		stationDao.delete(deleteStation);
	}

	@Override
	public List<Station> findAll() {
		return stationDao.findAll();
	}

	public void setStationDao(StationDao stationDao) {
		this.stationDao = stationDao;
	}
}