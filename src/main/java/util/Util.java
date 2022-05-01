package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Util {

	private static EntityManagerFactory factory;

	static {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("clocking-app");
		}
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
