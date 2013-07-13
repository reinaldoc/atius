package br.com.atius.catalog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SERVICEAREA")
public class ServiceArea implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotEmpty(message="{catalog.area.name}")
	private String name;

	@NotNull
	@NotEmpty(message="{catalog.area.description}")
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "area_id")
	@BatchSize(size = 10)
	private List<ServiceGroup> serviceGroups = new ArrayList<ServiceGroup>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public List<ServiceGroup> getServiceGroups() {
		return serviceGroups;
	}

	public void setServiceGroups(List<ServiceGroup> serviceGroups) {
		this.serviceGroups = serviceGroups;
	}

}
