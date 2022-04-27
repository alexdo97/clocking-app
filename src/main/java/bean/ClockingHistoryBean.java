package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import DTO.ClockingAction;
import model.ClockingEntry;
import model.Student;

@ManagedBean
@ViewScoped
public class ClockingHistoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ClockingEntry> clockingEntryList = new ArrayList<ClockingEntry>();

	@PostConstruct
	public void init() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("clocking-app");
		EntityManager entityManager = factory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			ClockingEntry clockingEntry1 = new ClockingEntry("Dobrin", "Alex", "Punch In", "2022-04-27", "11:37:30");
			entityManager.persist(clockingEntry1);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
		try {
			entityManager.getTransaction().begin();
			ClockingEntry clockingEntry2 = new ClockingEntry("Dobrin", "Alex", "Punch Out", "2022-04-27", "11:37:40");
			entityManager.persist(clockingEntry2);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}

		try {
			entityManager.getTransaction().begin();
			String sql = "select ce from ClockingEntry ce";
			TypedQuery<ClockingEntry> query = entityManager.createQuery(sql, ClockingEntry.class);
			clockingEntryList = query.getResultList();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
//		entityManager.getTransaction().begin();
//		String sql = "select ce from ClockingEntry ce";
//		TypedQuery<ClockingEntry> query = entityManager.createQuery(sql, ClockingEntry.class);
//		clockingEntryList = query.getResultList();
//		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();

		System.out.println("ClockingHistoryBean init");

	}

	// GETTERS AND SETTERS

	public List<ClockingEntry> getClockingEntryList() {
		return clockingEntryList;
	}

	public void setClockingEntryList(List<ClockingEntry> clockingEntryList) {
		this.clockingEntryList = clockingEntryList;
	}

}
