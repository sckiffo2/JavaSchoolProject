package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Station;

import java.util.List;

public interface StationDao {
	Station findById(int id);

	Station findByName(String name);

	List<Station> findAll();

	void save(Station station);

	void update(Station station);

	void delete(Station station);


}
