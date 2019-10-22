package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.UserDao;
import com.voronov.entities.User;
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
	public User findByName(String name) {
		return null;
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
