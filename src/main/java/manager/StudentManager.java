package manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Student;

public class StudentManager {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clocking-app");
		EntityManager entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();

		Student newStudent = new Student();
		newStudent.setFirstName("Alex");
		newStudent.setLastName("Dobrin");

		entityManager.persist(newStudent);

		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
}
