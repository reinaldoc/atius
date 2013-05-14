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
@Table(name = "SERVICEGROUP")
public class ServiceGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@NotNull
	@NotEmpty
	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "group_id")
	private List<ServiceItem> serviceItems = new ArrayList<ServiceItem>();

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

	public List<ServiceItem> getServiceItems() {
		return serviceItems;
	}

	public void setServiceItems(List<ServiceItem> serviceItems) {
		this.serviceItems = serviceItems;
	}

}
