package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.TripDao;
import com.voronov.entities.TrainTrip;
import com.voronov.utils.HibernateSessionFactoryUtil;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@NoArgsConstructor
public class TripDaoImpl implements TripDao {

	@Override
	public TrainTrip findById(int id) {
		TrainTrip trainTrip = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			trainTrip = session.get(TrainTrip.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return trainTrip;
	}

	@Override
	public TrainTrip findByName(String name) {
		return null;
	}

	@Override
	public List<TrainTrip> findAll() {
		List<TrainTrip> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			result = session.createQuery("from TrainTrip", TrainTrip.class).getResultList();
			result.sort(Comparator.comparingInt(TrainTrip::getId));

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void save(TrainTrip trip) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(trip);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(TrainTrip trip) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(trip);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(TrainTrip trip) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.delete(trip);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
