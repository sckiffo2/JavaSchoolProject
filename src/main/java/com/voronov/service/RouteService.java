package com.voronov.service;

import com.voronov.dao.DAOinterfaces.RouteDao;
import com.voronov.entities.Route;

import java.util.List;

public interface RouteService {

	Route findById(int id);

	Route findByName(String name);

	void save(Route passenger);

	void update(Route passenger);

	void delete(int id);

	List<Route> findAll();
}

