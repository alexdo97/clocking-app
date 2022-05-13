package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import enums.Profile;

@Entity
@Table(name = "identity")
public class Identity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "username", unique = true, nullable = false, length = 150)
	private String username;

	@Column(name = "password", nullable = false, length = 150)
	private String password;

	@Column(name = "email", unique = true, nullable = false, length = 150)
	private String email;

	@Column(name = "profile", nullable = false)
	private Integer profile;

	@OneToOne(mappedBy = "identity", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Employee employee;

	@Transient
	private Profile profileEnum;

	public Identity() {
		profile = Profile.User.getCode();
	}

	public Integer getProfile() {
		return profile;
	}

	public void setProfile(Integer profile) {
		this.profile = profile;
	}

	public Profile getProfileEnum() {
		return profileEnum;
	}

	public void setProfileEnum(Profile profileEnum) {
		this.profileEnum = profileEnum;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
