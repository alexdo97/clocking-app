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
		return dao.getAllByEmployeeId(id);
	}

	public ClockingEntry getById(Long id) {
		return dao.getById(id);
	}

	public void save(ClockingEntry clockingEntry) {
		dao.save(clockingEntry);
	}

	public List<ClockingEntry> getLastActions(Long id) {
		return dao.getLastActions(id);
	}
}
