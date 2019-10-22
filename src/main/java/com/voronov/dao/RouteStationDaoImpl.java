package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.RouteStationDao;
import com.voronov.entities.RouteStation;
import com.voronov.utils.HibernateSessionFactoryUtil;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@NoArgsConstructor
public class RouteStationDaoImpl implements RouteStationDao {
	@Override
	public RouteStation findById(long id) {
		RouteStation routeStation = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			routeStation = session.get(RouteStation.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return routeStation;
	}

	@Override
	public List<RouteStation> findAll() {
		List<RouteStation> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			result = session.createQuery("from RouteStation", RouteStation.class).getResultList();
			result.sort(Comparator.comparingLong(RouteStation::getId));

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void save(RouteStation routeStation) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(routeStation);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(RouteStation routeStation) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(routeStation);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(RouteStation routeStation) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.delete(routeStation);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<RouteStation> findStationsOfRoute(long id) {
		List<RouteStation> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("from RouteStation R where route.id = :id", RouteStation.class);
			query.setParameter("id", id);
			result = query.getResultList();
			result.sort(Comparator.comparingInt(RouteStation::getIndexInRoute));

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null) {
			result = new ArrayList<>();
		}
		return result;
	}
}
