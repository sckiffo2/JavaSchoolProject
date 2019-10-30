package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.TripStationDao;
import com.voronov.entities.TripStation;
import com.voronov.utils.HibernateSessionFactoryUtil;
import lombok.NoArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@NoArgsConstructor
public class TripStationDaoImpl implements TripStationDao {

	@Override
	public TripStation findById(long id) {
		TripStation tripStation = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			tripStation = session.get(TripStation.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return tripStation;
	}

	@Override
	public List<TripStation> findAll() {
		List<TripStation> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			result = session.createQuery("from TripStation", TripStation.class).getResultList();
			result.sort(Comparator.comparingLong(TripStation::getId));

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void save(TripStation tripStation) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(tripStation);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(TripStation tripStation) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(tripStation);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(TripStation tripStation) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.delete(tripStation);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
