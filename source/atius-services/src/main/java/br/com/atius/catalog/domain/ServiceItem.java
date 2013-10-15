package br.com.atius.catalog.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.atius.core.domain.Repository;
import br.com.atius.knowledge.domain.Faq;
import br.com.atius.knowledge.domain.Knowledge;

@Entity
@Table(name = "SERVICEITEM")
public class ServiceItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATIUS_ID", sequenceName = "SQ_ATIUS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ATIUS_ID")
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

	@ManyToMany
	@JoinTable(name = "FAQ_SERVICEITEM", joinColumns = { @JoinColumn(name = "SERVICE_ID") }, inverseJoinColumns = { @JoinColumn(name = "FAQ_ID") })
	private List<Faq> faqs = new ArrayList<Faq>();

	public ServiceItem() {

	}

	public ServiceItem(ServiceItem item) {
		this.name = item.name;
		this.description = item.description;
		this.group = item.group;
		this.subgroup = item.subgroup;
		this.knowledges = item.knowledges;
	}

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

	public List<Faq> getFaqs() {
		return faqs;
	}

	public void setFaqs(List<Faq> faqs) {
		this.faqs = faqs;
	}

	public Repository getImage() {
		if (group != null && group.getImage() != null)
			return group.getImage();
		else if (subgroup != null && subgroup.getGroup().getImage() != null)
			return subgroup.getGroup().getImage();
		return null;
	}

	public String getRole() {
		if (group != null)
			return group.getArea().getRole();
		else if (subgroup != null)
			return subgroup.getGroup().getArea().getRole();
		return null;
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
		ServiceItem other = (ServiceItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
