package com.voronov.service;

import com.voronov.dao.DAOinterfaces.TripStationDao;
import com.voronov.entities.TripStation;
import com.voronov.service.serviceInterfaces.TripStationService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@NoArgsConstructor
public class TripStationServiceImpl implements TripStationService {

	@Autowired
	TripStationDao tripStationDao;


	@Override
	public TripStation findById(long id) {
		return tripStationDao.findById(id);
	}

	@Override
	public void save(TripStation tripStation) {

		tripStationDao.save(tripStation);
	}

	@Override
	public void update(TripStation tripStation) {
		tripStationDao.update(tripStation);
	}

	@Override
	public void delete(int id) {
		TripStation deleteRouteStation = tripStationDao.findById(id);
		tripStationDao.delete(deleteRouteStation);
	}

	@Override
	public List<TripStation> findAll() {
		return tripStationDao.findAll();
	}
}
