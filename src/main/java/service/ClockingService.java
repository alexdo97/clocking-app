package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dao.ClockingEntryDAO;
import model.ClockingEntry;
import util.Util;

@Stateless
public class ClockingService {

	private ClockingEntryDAO dao;
	private EntityManager em;

	public ClockingService() {
		this.dao = new ClockingEntryDAO();
		em = Util.getEntityManager();
	}

	public List<ClockingEntry> getAll() {
		return dao.findAll();
	}

	public List<ClockingEntry> getAllByEmployeeId(Long id) {
		List<ClockingEntry> clockingEntryList = new ArrayList<ClockingEntry>();
		try {
			em.getTransaction().begin();
			String sql = "from ClockingEntry where employee_id = :id";
			TypedQuery<ClockingEntry> query = em.createQuery(sql, ClockingEntry.class);
			query.setParameter("id", id);
			clockingEntryList = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		return clockingEntryList;
	}

	public ClockingEntry getById(Long id) {
		return dao.getById(id);
	}

	public void save(ClockingEntry clockingEntry) {
		dao.save(clockingEntry);
	}
}
