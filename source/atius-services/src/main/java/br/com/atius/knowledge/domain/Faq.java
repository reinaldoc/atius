package br.com.atius.knowledge.domain;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.atius.catalog.domain.ServiceItem;

@Entity
@Table(name = "FAQ")
public class Faq implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ATIUS_ID", sequenceName = "SQ_ATIUS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ATIUS_ID")
	private Integer id;

	@NotNull
	@Size(min = 3, max = 1024)
	private String question;

	@NotNull
	@Size(min = 3, max = 102400)
	private String answer;

	@ManyToMany
	@JoinTable(name = "FAQ_SERVICEITEM", joinColumns = { @JoinColumn(name = "FAQ_ID") }, inverseJoinColumns = { @JoinColumn(name = "SERVICE_ID") })
	private List<ServiceItem> services = new ArrayList<ServiceItem>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<ServiceItem> getServices() {
		return services;
	}

	public void setServices(List<ServiceItem> services) {
		this.services = services;
	}

}
