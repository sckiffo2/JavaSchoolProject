package com.voronov.dao.DAOinterfaces;

import com.voronov.entities.Wagon;

import java.util.List;

public interface WagonDao {
	Wagon findById(int id);

	Wagon findByName(String name);

	@SuppressWarnings("unchecked")
	List<Wagon> findAll();

	void save(Wagon wagon);

	void update(Wagon wagon);

	void delete(Wagon wagon);
}
