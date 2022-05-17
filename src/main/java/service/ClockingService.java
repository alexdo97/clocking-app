package service;

import java.util.List;

import dao.ClockingEntryDAO;
import model.ClockingEntry;

public class ClockingService {

	private ClockingEntryDAO dao;

	public ClockingService() {
		this.dao = new ClockingEntryDAO();
	}

	public List<ClockingEntry> getAll() {
		return dao.getAll();
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
