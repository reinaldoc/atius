package br.com.atius.catalog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SERVICEAREA")
public class ServiceArea implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATIUS_ID", sequenceName = "SQ_ATIUS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ATIUS_ID")
	private Integer id;

	private String role;

	@NotNull
	@NotEmpty(message = "{catalog.area.name}")
	@Size(min = 3, max = 255)
	private String name;

	@NotNull
	@NotEmpty(message = "{catalog.area.description}")
	@Size(min = 3, max = 510)
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("name asc")
	@JoinColumn(name = "area_id")
	@BatchSize(size = 10)
	private List<ServiceGroup> groups = new ArrayList<ServiceGroup>();

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

	public List<ServiceGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<ServiceGroup> groups) {
		this.groups = groups;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceArea other = (ServiceArea) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
