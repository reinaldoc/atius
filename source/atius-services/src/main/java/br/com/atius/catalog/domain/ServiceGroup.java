package br.com.atius.catalog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SERVICEGROUP")
public class ServiceGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotEmpty(message = "{catalog.group.name}")
	@Size(min = 3, max = 255)
	private String name;

	@NotNull
	@NotEmpty(message = "{catalog.group.description}")
	@Size(min = 3, max = 512)
	private String description;

	@Lob
	private byte[] image;

	@OneToOne
	@JoinColumn(name = "area_id")
	private ServiceArea area;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("name asc")
	@JoinColumn(name = "group_id")
	@BatchSize(size = 3)
	private List<ServiceSubgroup> subgroups = new ArrayList<ServiceSubgroup>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("name asc")
	@JoinColumn(name = "group_id")
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public ServiceArea getArea() {
		return area;
	}

	public void setArea(ServiceArea area) {
		this.area = area;
	}

	public List<ServiceSubgroup> getSubgroups() {
		return subgroups;
	}

	public void setSubgroups(List<ServiceSubgroup> serviceSubgroups) {
		this.subgroups = serviceSubgroups;
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
		ServiceGroup other = (ServiceGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
