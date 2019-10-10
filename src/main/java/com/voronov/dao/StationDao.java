package com.voronov.dao;

import com.voronov.entities.Station;
import com.voronov.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StationDao {

	public Station findById(int id) {
		return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Station.class, id);
	}

	public void save(Station station) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(station);
		transaction.commit();
		session.close();
	}

	public void update(Station station) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(station);
		transaction.commit();
		session.close();
	}

	public void delete(Station station) {
		Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(station);
		transaction.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Station> findAll() {
		return (List<Station>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from com.voronov.entities.Station").list();
	}
}
