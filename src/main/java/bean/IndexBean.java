package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Student;

@Named
@ViewScoped
public class IndexBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String test = "TEST";

	@PersistenceContext(unitName = "CLOCKINGDB")
	EntityManager em;

	@PostConstruct
	public void init() {
		System.out.println("IndexBean init");
		Student newStudent = new Student();
		newStudent.setFirstName("Alex333");
		newStudent.setLastName("Dobrin");
		em.persist(newStudent);
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
}
