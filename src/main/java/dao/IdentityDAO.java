package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import model.Identity;

public class IdentityDAO extends GenericDAO<Identity, Long> {

	public Identity findByUsernamePassword(String username, String password) {
		Identity result = null;
		try {
			this.entityManager.getTransaction().begin();
			String hql = "SELECT ide FROM Identity ide WHERE ide.username =:username AND ide.password =:password";
			TypedQuery<Identity> query = entityManager.createQuery(hql, Identity.class);
			query.setParameter("username", username);
			query.setParameter("password", password);
			List<Identity> resultList = query.getResultList();
			entityManager.getTransaction().commit();
			if (resultList.isEmpty() || resultList.size() > 1) {
				return null;
			} else {
				result = resultList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return result;

	}

	public Identity findByUsername(String username) {
		Identity result = null;
		try {
			this.entityManager.getTransaction().begin();
			String hql = "SELECT ide FROM Identity ide WHERE ide.username =:username";
			TypedQuery<Identity> query = entityManager.createQuery(hql, Identity.class);
			query.setParameter("username", username);
			List<Identity> resultList = query.getResultList();
			entityManager.getTransaction().commit();
			if (resultList.isEmpty() || resultList.size() > 1) {
				return null;
			} else {
				result = resultList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return result;
	}

	public Identity findByEmail(String email) {
		Identity result = null;
		try {
			this.entityManager.getTransaction().begin();
			String hql = "SELECT ide FROM Identity ide WHERE ide.email =:email";
			TypedQuery<Identity> query = entityManager.createQuery(hql, Identity.class);
			query.setParameter("email", email);
			List<Identity> resultList = query.getResultList();
			entityManager.getTransaction().commit();
			if (resultList.isEmpty() || resultList.size() > 1) {
				return null;
			} else {
				result = resultList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return result;
	}

}
