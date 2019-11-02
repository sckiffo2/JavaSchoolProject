package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.TicketDao;
import com.voronov.entities.Ticket;
import com.voronov.entities.Trip;
import com.voronov.utils.HibernateSessionFactoryUtil;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@NoArgsConstructor
public class TicketDaoImpl implements TicketDao {

	@Override
	public Ticket findById(long id) {
		Ticket ticket = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			ticket = session.get(Ticket.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	@Override
	public List<Ticket> findAll() {
		List<Ticket> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			result = session.createQuery("from Ticket", Ticket.class).getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Ticket> findByTripId(long id) {
		List<Ticket> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("from Ticket T where T.trip.id = :id", Ticket.class);
			query.setParameter("id", id);
			result = query.getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Ticket findTicketByTripAndPlace(long tripId, int wagon, int place) {
		Ticket result = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("from Ticket T where T.trip.id = :tripId and wagonNumber = :wagon and place = :place", Ticket.class);
			query.setParameter("tripId", tripId);
			query.setParameter("wagon", wagon);
			query.setParameter("place", place);
			result = (Ticket) query.getSingleResult();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void deleteLongBookedTickets() {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("delete Ticket T where T.bookedTill is not null and T.bookedTill < :timeNow");
			query.setParameter("timeNow", LocalDateTime.now());
			int result = query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void save(Ticket ticket) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(ticket);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Ticket ticket) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(ticket);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Ticket ticket) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.delete(ticket);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isExists(Trip trip, int wagon, int place) {
		boolean result = false;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("select count(*) from Ticket T where T.trip = :trip and wagonNumber = :wagon and place = :place");
			query.setParameter("trip", trip);
			query.setParameter("wagon", wagon);
			query.setParameter("place", place);
			result = (long)query.getSingleResult() == 0;

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
