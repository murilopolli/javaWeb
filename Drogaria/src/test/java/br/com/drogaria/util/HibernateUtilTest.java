package br.com.drogaria.util;

import org.hibernate.Session;
import org.junit.Test;

public class HibernateUtilTest {
	
	@Test
	public void connect() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.close();
	}

}
