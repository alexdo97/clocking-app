package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import model.ClockingEntry;
import model.Employee;
import service.ClockingService;
import service.EmployeeService;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class ClockingHistoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ClockingEntry> clockingEntryList = new ArrayList<ClockingEntry>();
	private Employee identityEmployee;

	@EJB
	private EmployeeService employeeService;
	@EJB
	private ClockingService clockingService;

	@PostConstruct
	public void init() {
		employeeService = new EmployeeService();
		clockingService = new ClockingService();
		identityEmployee = employeeService.getById(1L);
		clockingEntryList = clockingService.getAllByEmployeeId(identityEmployee.getId());
		System.out.println(ClockingHistoryBean.class.getName() + " init");
	}

	// GETTERS AND SETTERS

	public List<ClockingEntry> getClockingEntryList() {
		return clockingEntryList;
	}

	public void setClockingEntryList(List<ClockingEntry> clockingEntryList) {
		this.clockingEntryList = clockingEntryList;
	}

}
