package br.com.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.drogaria.util.HibernateUtil;

public class GenericDAO<T> {

	private Class<T> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void salvar(T t) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.save(t);
			trans.commit();
		} catch (RuntimeException e) {
			if (trans != null)
				trans.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> listar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria select = session.createCriteria(this.classe);
			List<T> list = select.list();
			return list;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listar(String campo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria select = session.createCriteria(this.classe);
			select.addOrder(Order.asc(campo));
			List<T> list = select.list();
			return list;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T buscar(Long c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria select = session.createCriteria(this.classe);
			select.add(Restrictions.idEq(c));
			T t = (T) select.uniqueResult();
			return t;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	public void excluir(T t) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.delete(t);
			trans.commit();
		} catch (RuntimeException e) {
			if (trans != null)
				trans.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	public void alterar(T t) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.update(t);
			trans.commit();
		} catch (RuntimeException e) {
			if (trans != null)
				trans.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T merge(T t) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			T retorno = (T)session.merge(t);
			trans.commit();
			return retorno;
		} catch (RuntimeException e) {
			if (trans != null)
				trans.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
