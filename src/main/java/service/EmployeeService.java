package service;

import java.util.List;

import dao.EmployeeDAO;
import model.Employee;

public class EmployeeService {

	private EmployeeDAO dao;

	public EmployeeService() {
		this.dao = new EmployeeDAO();
	}

	public Employee getById(Long id) {
		return dao.getById(id);
	}

	public List<Employee> getAll() {
		return dao.getAll();
	}

	public void save(Employee employee) {
		dao.save(employee);
	}

}
