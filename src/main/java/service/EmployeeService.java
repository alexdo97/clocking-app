package service;

import java.util.List;

import javax.ejb.Stateless;

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

	public List<Employee> getAll() {
		return dao.findAll();
	}

	public void save(Employee employee) {
		dao.save(employee);
	}

//	public Employee getBy
}
