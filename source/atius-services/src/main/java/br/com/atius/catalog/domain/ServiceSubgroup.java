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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SERVICESUBGROUP")
public class ServiceSubgroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATIUS_ID", sequenceName = "SQ_ATIUS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ATIUS_ID")
	private Integer id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 255)
	private String name;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 510)
	private String description;

	@OneToOne
	@JoinColumn(name = "group_id")
	private ServiceGroup group;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("name asc")
	@JoinColumn(name = "subgroup_id")
	@BatchSize(size = 10)
	private List<ServiceItem> items = new ArrayList<ServiceItem>();

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

	public ServiceGroup getGroup() {
		return group;
	}

	public void setGroup(ServiceGroup group) {
		this.group = group;
	}

	public List<ServiceItem> getItems() {
		return items;
	}

	public void setItems(List<ServiceItem> items) {
		this.items = items;
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
		ServiceSubgroup other = (ServiceSubgroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
