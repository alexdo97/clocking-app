package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;

import util.FactoryUtils;

public class GenericDAO<T, PK extends Serializable> {

	protected EntityManager entityManager;

	protected Class<T> entityClass;

	EntityManagerFactory factory;

	@SuppressWarnings("unchecked")
	public GenericDAO() {

		entityManager = FactoryUtils.getEntityManager();
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();

		this.entityClass = ((Class<T>) genericSuperclass.getActualTypeArguments()[0]);
	}

	public void save(final T entity) {
		
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T getById(final PK id) {
		T t = null;
		try {
			entityManager.getTransaction().begin();
			t = entityManager.find(entityClass, id);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}

	public void delete(final T entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		entityManager.getTransaction().begin();
		List<T> entityReturn = entityManager.createQuery("FROM " + entityClass.getName()).getResultList();
		entityManager.getTransaction().commit();
		return entityReturn;
	}

	public Session getSession() {
		return (Session) entityManager.getDelegate();
	}

}
