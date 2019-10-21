package com.voronov.service;


import com.voronov.dao.DAOinterfaces.RouteStationDao;
import com.voronov.entities.RouteStation;
import com.voronov.service.serviceInterfaces.RouteStationService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@NoArgsConstructor
public class RouteStationServiceImpl implements RouteStationService {

	@Autowired
	private RouteStationDao routeStationDao;

	@Override
	public RouteStation findById(int id) {
		return routeStationDao.findById(id);
	}

	@Override
	public void save(RouteStation routeStation) {
		routeStationDao.save(routeStation);
	}

	@Override
	public void update(RouteStation routeStation) {
		routeStationDao.update(routeStation);
	}

	@Override
	public void delete(int id) {
		RouteStation deleteRouteStation = routeStationDao.findById(id);
		routeStationDao.delete(deleteRouteStation);
	}

	@Override
	public List<RouteStation> findAll() {
		return routeStationDao.findAll();
	}

	@Override
	public List<RouteStation> findStationsOfRoute(int id) {
		return routeStationDao.findStationsOfRoute(id);
	}
}
