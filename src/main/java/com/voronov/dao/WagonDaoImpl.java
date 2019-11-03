package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.WagonDao;
import com.voronov.entities.Wagon;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class WagonDaoImpl implements WagonDao {

	@Override
	public Wagon findById(long id) {
		return null;
	}

	@Override
	public Wagon findByName(String name) {
		return null;
	}

	@Override
	public List<Wagon> findAll() {
		return null;
	}

	@Override
	public void save(Wagon wagon) {

	}

	@Override
	public void update(Wagon wagon) {

	}

	@Override
	public void delete(Wagon wagon) {

	}
}
