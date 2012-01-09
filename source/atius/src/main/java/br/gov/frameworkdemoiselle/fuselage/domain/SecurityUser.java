package br.gov.frameworkdemoiselle.fuselage.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class SecurityUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	@SequenceGenerator(name = "system-uuid", sequenceName = "guid")
	private Long id;

	@NotEmpty
	@Column
	private String login;

	@Column
	private String name;

	@Column
	private String password;

	@Column
	private String orgunit;

	@Column
	private String description;

	@ManyToMany
	@JoinTable(name = "SECURITYUSER_PROFILE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "PROFILE_ID") })
	private List<SecurityProfile> profiles;

	public SecurityUser() {
		
	}

	public SecurityUser(String login) {
		this.login = login;
	}

	public SecurityUser(String login, String name) {
		this.login = login;
		this.name = name;
	}

	public SecurityUser(String login, String name, String organizationUnit) {
		this.login = login;
		this.name = name;
		this.orgunit = organizationUnit;
	}

	public SecurityUser(String login, String name, String organizationUnit, String description) {
		this.login = login;
		this.name = name;
		this.orgunit = organizationUnit;
		this.description = description;
	}

	public SecurityUser(String login, String name, String organizationUnit, String description, String password) {
		this.login = login;
		this.name = name;
		this.orgunit = organizationUnit;
		this.description = description;
		this.password = password;
	}

	public SecurityUser(String login, String name, String organizationUnit, String description, String password, List<SecurityProfile> profiles) {
		this.login = login;
		this.name = name;
		this.orgunit = organizationUnit;
		this.description = description;
		this.password = password;
		this.profiles = profiles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<SecurityProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<SecurityProfile> profiles) {
		this.profiles = profiles;
	}

	public String getOrgunit() {
		return orgunit;
	}

	public void setOrgunit(String orgunit) {
		this.orgunit = orgunit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
