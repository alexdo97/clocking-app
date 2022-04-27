package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clocking_entry")
public class ClockingEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clocking_id", unique = true, nullable = false)
	private Long id;

	@Column(name = "last_name", nullable = false, length = 150)
	private String firstName;

	@Column(name = "first_name", nullable = false, length = 150)
	private String lastName;

	@Column(name = "punch_type", nullable = false, length = 150)
	private String punchType;

	@Column(name = "date", nullable = false, length = 150)
	private String date;

	@Column(name = "time", nullable = false, length = 150)
	private String time;

	public ClockingEntry() {

	}

	public ClockingEntry(String lastName, String firstName, String punchType, String date, String time) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.punchType = punchType;
		this.date = date;
		this.time = time;
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

	public String getPunchType() {
		return punchType;
	}

	public void setPunchType(String punchType) {
		this.punchType = punchType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
