package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "last_name", nullable = false, length = 150)
	private String firstName;

	@Column(name = "first_name", nullable = false, length = 150)
	private String lastName;

	@Column(name = "role", nullable = false, length = 150)
	private String role;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClockingEntry> clockingEntries = new ArrayList<ClockingEntry>();

	public Employee() {

	}

	public Employee(String firstName, String lastName, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	// GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ClockingEntry> getClockingEntries() {
		return clockingEntries;
	}

	public void setClockingEntries(List<ClockingEntry> clockingEntries) {
		this.clockingEntries = clockingEntries;
	}

}
