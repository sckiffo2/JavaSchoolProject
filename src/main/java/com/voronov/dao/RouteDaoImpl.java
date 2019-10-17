package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.RouteDao;
import com.voronov.entities.Route;
import com.voronov.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteDaoImpl implements RouteDao {
	@Override
	public Route findById(int id) {
		Route route = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			route = session.get(Route.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return route;
	}

	@Override
	public Route findByName(String name) {
		return null;
	}

	@Override
	public List<Route> findAll() {
		return null;
	}

	@Override
	public void save(Route route) {

	}

	@Override
	public void update(Route route) {

	}

	@Override
	public void delete(Integer id) {

	}
}
