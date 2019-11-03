package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.UserDao;
import com.voronov.entities.User;
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
public class UserDaoImpl implements UserDao {

	@Override
	public User findById(long id) {
		User user = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			user = session.get(User.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User findByName(String username) {
		User user = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Query query = session.createQuery("from User U where U.username = :username", User.class);
			query.setParameter("username", username);
			user = (User) query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean isExists(String username, String mail) {
		boolean result = false;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Query query = session.createQuery("select count(*) from User U where U.username = :username and U.mail = :mail");
			query.setParameter("username", username);
			query.setParameter("mail", mail);
			result = !((long)query.getSingleResult() == 0);

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<User> findAll() {
		List<User> result = null;

		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			result = session.createQuery("from User", User.class).getResultList();

			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void save(User user) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User user) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(User user) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
