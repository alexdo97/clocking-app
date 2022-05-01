package service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import dao.EmployeeDAO;
import model.Employee;

@Stateless
public class EmployeeService {

	private EmployeeDAO dao;

	public EmployeeService() {
		this.dao = new EmployeeDAO();
	}

	public Employee getById(Long id) {
		return dao.getById(id);
	}

}
