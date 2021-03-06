package com.voronov.utils;

import com.voronov.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateSessionFactoryUtil {
	private static SessionFactory sessionFactory;

	private HibernateSessionFactoryUtil() { }

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration().configure();
				configuration.addAnnotatedClass(Station.class);
				configuration.addAnnotatedClass(Route.class);
				configuration.addAnnotatedClass(RouteStation.class);
				configuration.addAnnotatedClass(Passenger.class);
				configuration.addAnnotatedClass(Role.class);
				configuration.addAnnotatedClass(Ticket.class);
				configuration.addAnnotatedClass(Trip.class);
				configuration.addAnnotatedClass(TripStation.class);
				configuration.addAnnotatedClass(User.class);


				StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
				sessionFactory = configuration.buildSessionFactory(builder.build());

			} catch (Exception e) {
				System.err.println("Initial SessionFactory creation failed." + e);
				throw new ExceptionInInitializerError(e);
			}
		}
		return sessionFactory;
	}
}
