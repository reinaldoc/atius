package br.com.atius.catalog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.atius.knowledge.domain.Knowledge;

@Entity
@Table(name = "SERVICEITEM")
public class ServiceItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 510)
	private String name;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 2048)
	private String description;

	@OneToOne
	@JoinColumn(name = "group_id")
	private ServiceGroup group;

	@OneToOne
	@JoinColumn(name = "subgroup_id")
	private ServiceSubgroup subgroup;

	@ManyToMany
	@JoinTable(name = "KNOWLEDGE_SERVICEITEM", joinColumns = { @JoinColumn(name = "SERVICE_ID") }, inverseJoinColumns = { @JoinColumn(name = "KNOWLEDGE_ID") })
	private List<Knowledge> knowledges = new ArrayList<Knowledge>();

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

	public ServiceSubgroup getSubgroup() {
		return subgroup;
	}

	public void setSubgroup(ServiceSubgroup subgroup) {
		this.subgroup = subgroup;
	}

	public List<Knowledge> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(List<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}

}
