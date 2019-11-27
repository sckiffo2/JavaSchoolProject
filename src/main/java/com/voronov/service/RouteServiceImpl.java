package com.voronov.service;

import com.voronov.dao.DAOinterfaces.RouteDao;
import com.voronov.entities.Route;
import com.voronov.exceptions.BusinessLogicException;
import com.voronov.service.serviceInterfaces.RouteService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
@NoArgsConstructor
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteDao routeDao;

	@Override
	public Route findById(long id) {
		return routeDao.findById(id);
	}

	@Override
	public Route findByName(String name) {
		return routeDao.findByName(name);
	}

	@Override
	public Route findByNumber(String number) {
		return routeDao.findByNumber(number);
	}

	@Override
	public List<Route> findRoutesByStationId(long id) {
		return routeDao.findRoutesByStationId(id);
	}

	@Override
	public boolean isExist(String number) {
		return routeDao.isExist(number);
	}

	@Override
	public void save(String number, String name, String pattern) {
		if (!isExist(number)) {
			routeDao.save(new Route(number, name, pattern));
		} else {
			throw new BusinessLogicException("Маршрут с таким номером уже существует");
		}

	}

	@Override
	public void update(Route route) {
		routeDao.update(route);
	}

	@Override
	public void delete(int id) {
		Route deleteRoute = routeDao.findById(id);
		routeDao.delete(deleteRoute);
	}

	@Override
	public List<Route> findAll() {
		return routeDao.findAll();
	}
}
