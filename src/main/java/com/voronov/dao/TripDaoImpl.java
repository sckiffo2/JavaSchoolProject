package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.Trip;
import com.voronov.utils.HibernateSessionFactoryUtil;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@NoArgsConstructor
public class TripDaoImpl implements TripDao {

	@Override
	public Trip findById(int id) {
		Trip trip = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			trip = session.get(Trip.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return trip;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Trip> findByRouteId(int id) {
		List<Trip> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("from Trip T where T.route.id = :id", Trip.class);
			query.setParameter("id", id);
			result = query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Trip> findAll() {
		List<Trip> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			result = session.createQuery("from Trip", Trip.class).getResultList();
			result.sort(Comparator.comparingInt(Trip::getId));

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void save(Trip trip) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(trip);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Trip trip) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(trip);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Trip trip) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.delete(trip);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
