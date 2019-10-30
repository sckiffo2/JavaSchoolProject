package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.RouteDao;
import com.voronov.entities.Route;
import com.voronov.entities.Station;
import com.voronov.utils.HibernateSessionFactoryUtil;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@NoArgsConstructor
public class RouteDaoImpl implements RouteDao {

	@Override
	public Route findById(long id) {
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
		Route route= null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Route R where R.name = :name", Route.class);
			query.setParameter("name", name);
			route = (Route) query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return route;
	}

	@Override
	public Route findByNumber(String number) {
		Route route= null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Route R join fetch R.stationsOnRoute where R.number = :number", Route.class);
			query.setParameter("number", number);
			route = (Route) query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return route;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Route> findRoutesByStationId(long id) {
		List<Route> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("select RS.route from RouteStation RS where RS.station.id = :id");
			query.setParameter("id", id);
			result = query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public List<Route> findAll() {
		List<Route> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			result = session.createQuery("from Route", Route.class).getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void save(Route route) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(route);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Route route) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(route);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Route route) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.delete(route);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
