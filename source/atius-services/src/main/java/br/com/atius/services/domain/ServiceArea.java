package br.com.atius.services.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SERVICEAREA")
public class ServiceArea implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@NotNull
	@NotEmpty
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "area_id")
	private List<ServiceGroup> serviceGroups = new ArrayList<ServiceGroup>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ServiceGroup> getServiceGroups() {
		return serviceGroups;
	}

	public void setServiceGroups(List<ServiceGroup> serviceGroups) {
		this.serviceGroups = serviceGroups;
	}

}
