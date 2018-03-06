package br.com.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.drogaria.domain.Cidade;
import br.com.drogaria.util.HibernateUtil;

public class CidadeDAO extends GenericDAO<Cidade>{
	
	@SuppressWarnings("unchecked")
	public List<Cidade> listarPorUF(Long codigoEstado){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria select = session.createCriteria(Cidade.class);
			select.add(Restrictions.eq("estado.codigo", codigoEstado));
			select.addOrder(Order.asc("nome"));
			return select.list();
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
}