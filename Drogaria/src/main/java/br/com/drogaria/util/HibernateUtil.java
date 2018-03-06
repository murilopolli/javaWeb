package br.com.drogaria.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();
	
	public static SessionFactory getSessionFactory() {
		
		return sessionFactory;
	}
	
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration config = new Configuration().configure();
			ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			return config.buildSessionFactory(reg);
		} catch(Throwable e) {
			System.err.println("Error creating SessionFactory...");
			throw new ExceptionInInitializerError(e);
		}
	}
}
