package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.StationDao;
import com.voronov.entities.Station;
import com.voronov.utils.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;

@Service
public class StationDaoImpl implements StationDao {

	@Override
	public Station findById(int id) {
		Station station = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			station = session.get(Station.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return station;
	}

	@Override
	public Station findByName(String name) {
		Station station = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.createQuery("from Station S where S.name = :name", Station.class);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return station;
	}

	@Override
	public void save(Station station) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(station);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Station station) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(station);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer id) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();

			Station stationDelete = findById(id);
			//todo soft delete
			session.delete(stationDelete);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Station> findAll() {
		List<Station> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			result = session.createQuery("from com.voronov.entities.Station", Station.class).getResultList();
			result.sort(Comparator.comparingInt(Station::getId));

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
