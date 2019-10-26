package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.Trip;
import com.voronov.utils.HibernateSessionFactoryUtil;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@NoArgsConstructor
public class TripDaoImpl implements TripDao {

	@Override
	public Trip findById(long id) {
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
	public Trip findByRouteIdAndDate(long id, LocalDate date) {
		Trip result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("from Trip T where T.route.id = :id and T.startDate = :date", Trip.class);
			query.setParameter("id", id);
			query.setParameter("date", date);
			result = (Trip)query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Trip> findTripsByStationId(long id) {
		List<Trip> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("select t from Trip t join fetch t.route r join fetch r.stationsOnRoute rs where rs.station.id = : id", Trip.class);
			query.setParameter("id", id);
			result = query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Trip> findTripsByStationAndDate(long firstStationId, LocalDate date) {
		List<Trip> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("select t from Trip t join fetch t.stationsOnTrip ST join fetch t.stations s where ST.station.id = :id and ST.arrivalTime.toLocalDate() = :date", Trip.class);
			query.setParameter("id", firstStationId);
			query.setParameter("date", date.atStartOfDay()); //todo compare datetime at same date ?
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
