package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "last_name", nullable = false, length = 150)
	private String firstName;

	@Column(name = "first_name", nullable = false, length = 150)
	private String lastName;

	@OneToOne(cascade = CascadeType.ALL)
	@MapsId
	@JoinColumn(name = "id")
	private Identity identity;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClockingEntry> clockingEntries = new ArrayList<ClockingEntry>();

	public Employee() {

	}

	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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

	public List<ClockingEntry> getClockingEntries() {
		return clockingEntries;
	}

	public void setClockingEntries(List<ClockingEntry> clockingEntries) {
		this.clockingEntries = clockingEntries;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

}
