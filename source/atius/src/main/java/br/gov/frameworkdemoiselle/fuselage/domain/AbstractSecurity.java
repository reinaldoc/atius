package br.gov.frameworkdemoiselle.fuselage.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class AbstractSecurity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
	@SequenceGenerator(name = "system-uuid", sequenceName = "guid")
	private Long id;

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (this.id == null || obj == null || obj.getClass() != getClass())
			return false;
		if (id.equals(((AbstractSecurity) obj).id))
			return true;
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
