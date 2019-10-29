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

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@NoArgsConstructor
public class PassengerDaoImpl implements PassengerDao {

	@Override
	public Passenger findById(long id) {
		Passenger passenger = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			passenger = session.get(Passenger.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return passenger;
	}

	@Override
	public Passenger findByPassengerData(String firstName, String lastName, LocalDate birthDate) {
		Passenger result = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from Passenger P where P.firstName = :firstName and P.lastName = :lastName and P.birthDate = :birthDate", Passenger.class);
			query.setParameter("firstName", firstName);
			query.setParameter("lastName", lastName);
			query.setParameter("birthDate", birthDate);

			List list = query.getResultList();
			if (!list.isEmpty()) {
				result = (Passenger) list.get(0);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Passenger> findPassengersOnTrip(long id) {
		List<Passenger> result = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("select P from Passenger P inner join P.tickets T where T.trip.id = :id", Passenger.class);
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
