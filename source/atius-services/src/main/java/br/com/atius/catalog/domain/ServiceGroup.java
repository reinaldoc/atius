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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	private String name;

	@NotNull
	@NotEmpty(message = "{catalog.group.description}")
	private String description;

	@Lob
	private byte[] image;

	@OneToOne
	@JoinColumn(name = "area_id")
	private ServiceArea area;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "group_id")
	private List<ServiceSubgroup> subgroups = new ArrayList<ServiceSubgroup>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "group_id")
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

}
