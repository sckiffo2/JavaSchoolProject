package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.PassengerDao;
import com.voronov.entities.Passenger;
import com.voronov.entities.Ticket;
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
public class PassengerDaoImpl implements PassengerDao {

	@Override
	public Passenger findById(int id) {
		Passenger passenger = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			passenger = session.get(Passenger.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return passenger;
	}

	@Override
	public Passenger findByName(String name) {
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Passenger> findPassengersOnTrip(int id) {
		List<Passenger> result = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("select P from Passenger P inner join P.tickets T where T.Trip.id = :id", Passenger.class);
			query.setParameter("id", id);

			result = (List)query.getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Passenger> findAll() {
		List<Passenger> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			result = session.createQuery("from Passenger", Passenger.class).getResultList();
			result.sort(Comparator.comparingInt(Passenger::getId));

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void save(Passenger passenger) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(passenger);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Passenger passenger) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(passenger);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Passenger passenger) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.delete(passenger);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
