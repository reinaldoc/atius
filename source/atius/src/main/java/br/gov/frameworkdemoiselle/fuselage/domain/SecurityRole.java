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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class SecurityRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	@SequenceGenerator(name = "system-uuid", sequenceName = "guid")
	private Long id;

	@Column
	@NotBlank(message = "Especifique melhor o nome do papél")
	@Size(min = 3, max = 255, message = "Especifique melhor o nome do papél")
	private String name;

	@Column
	@NotBlank(message = "Descreva melhor o papél")
	@Size(min = 20, max = 255, message = "Descreva melhor o papél")
	private String description;

	@Column
	@NotBlank(message = "Especifique melhor a descrição curta do papél")
	@Size(min = 10, max = 255, message = "Descreva melhor o papél")
	private String shortDescription;

	@Column
	@NotNull
	private Boolean restriction;

	@ManyToMany
	@JoinTable(name = "SECURITYROLE_RESOURCE", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "RESOURCE_ID") })
	private List<SecurityResource> resource;

	@ManyToMany
	@JoinTable(name = "SECURITYPROFILE_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PROFILE_ID") })
	private List<SecurityProfile> profiles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SecurityResource> getResource() {
		return resource;
	}

	public void setResource(List<SecurityResource> resource) {
		this.resource = resource;
	}

	public List<SecurityProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<SecurityProfile> profiles) {
		this.profiles = profiles;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Boolean getRestriction() {
		return restriction;
	}

	public void setRestriction(Boolean restriction) {
		this.restriction = restriction;
	}

}
