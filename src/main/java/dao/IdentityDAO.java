package dao;

import java.util.List;

import model.Identity;

public class IdentityDAO extends GenericDAO<Identity, Long> {

	public List<Identity> findByUsernamePassword(String userName, String password) {

		this.entityManager.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Identity> result = entityManager
				.createQuery("FROM " + entityClass.getName() + " WHERE name = :userName AND password = :password")
				.setParameter("userName", userName).setParameter("password", password).getResultList();

		entityManager.getTransaction().commit();

		return result;
	}

}
