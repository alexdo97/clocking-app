package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import model.ClockingEntry;

public class ClockingEntryDAO extends GenericDAO<ClockingEntry, Long> {

	public List<ClockingEntry> getAllByEmployeeId(Long id) {
		List<ClockingEntry> clockingEntryList = new ArrayList<ClockingEntry>();
		try {
			entityManager.getTransaction().begin();
			String sql = "from ClockingEntry where employee_id = :id order by date DESC, time DESC";
			TypedQuery<ClockingEntry> query = entityManager.createQuery(sql, ClockingEntry.class);
			query.setParameter("id", id);
			clockingEntryList = query.getResultList();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return clockingEntryList;
	}

	public List<ClockingEntry> getAll() {
		List<ClockingEntry> clockingEntryList = new ArrayList<ClockingEntry>();
		try {
			entityManager.getTransaction().begin();
			String sql = "from ClockingEntry order by date DESC, time DESC";
			TypedQuery<ClockingEntry> query = entityManager.createQuery(sql, ClockingEntry.class);
			clockingEntryList = query.getResultList();
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return clockingEntryList;
	}

	public List<ClockingEntry> getLastActions(Long id) {
		List<ClockingEntry> lastActions = new ArrayList<ClockingEntry>();
		try {
			entityManager.getTransaction().begin();
			String sql = "from ClockingEntry where employee_id = :id order by date DESC, time DESC";
			TypedQuery<ClockingEntry> query = entityManager.createQuery(sql, ClockingEntry.class);
			query.setParameter("id", id);
			List<ClockingEntry> resultList = query.getResultList();
			if (resultList.size() >= 2) {
				lastActions.add(resultList.get(0));
				lastActions.add(resultList.get(1));
			} else if (resultList.size() == 1) {
				lastActions.add(resultList.get(0));
				lastActions.add(null);
			} else {
				lastActions.add(null);
				lastActions.add(null);
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return lastActions;

	}
}
