package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clocking_entry")
public class ClockingEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "punch_type", nullable = false, length = 150)
	private String punchType;

	@Column(name = "date", nullable = false, length = 150)
	private String date;

	@Column(name = "time", nullable = false, length = 150)
	private String time;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public ClockingEntry() {

	}

	public ClockingEntry(String punchType, String date, String time) {

		this.punchType = punchType;
		this.date = date;
		this.time = time;
	}

	// GETTERS AND SETTERS

	public Long getId() {
		return id;
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
