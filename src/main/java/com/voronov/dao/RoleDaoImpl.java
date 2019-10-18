package com.voronov.dao;

import com.voronov.dao.DAOinterfaces.RoleDao;
import com.voronov.entities.Role;
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
public class RoleDaoImpl implements RoleDao {
	@Override
	public Role findById(int id) {
		Role role = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			role = session.get(Role.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public Role findByName(String name) {
		return null;
	}

	@Override
	public List<Role> findAll() {
		List<Role> result = null;
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			result = session.createQuery("from Role", Role.class).getResultList();
			result.sort(Comparator.comparingInt(Role::getId));
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void save(Role role) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(role);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Role role) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.update(role);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Role role) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.delete(role);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
