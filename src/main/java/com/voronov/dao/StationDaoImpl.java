package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.StationDao;
import com.voronov.entities.Station;
import com.voronov.utils.HibernateSessionFactoryUtil;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@NoArgsConstructor
public class StationDaoImpl implements StationDao {

	@Override
	public Station findById(long id) {
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
		List<Station> list = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Station S where S.name = :name", Station.class);
			query.setParameter("name", name);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list != null && list.size() == 1) {
			station = list.get(0);
			return station;
		}
		return null;
	}

	@Override
	public void save(Station station) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(station);
			session.getTransaction().commit();
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
	public void delete(Station station) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.delete(station);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Station> findAll() {
		List<Station> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			result = session.createQuery("from Station", Station.class).getResultList();
			result.sort(Comparator.comparingLong(Station::getId));

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
