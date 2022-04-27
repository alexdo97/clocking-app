package service;

import java.util.List;

import javax.ejb.Stateless;

import dao.ClockingEntryDAO;
import model.ClockingEntry;

@Stateless
public class ClockingService {

	ClockingEntryDAO clockingEntryDAO;

	public ClockingService() {
		clockingEntryDAO = new ClockingEntryDAO();
	}

	public List<ClockingEntry> getAllEntries() {
		return clockingEntryDAO.findAll();
	}

	public ClockingEntry getById(Long id) {
		return clockingEntryDAO.getById(id);
	}
}
