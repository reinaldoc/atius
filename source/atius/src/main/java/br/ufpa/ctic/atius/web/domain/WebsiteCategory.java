package br.ufpa.ctic.atius.web.domain;

import javax.validation.constraints.Size;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class WebsiteCategory extends Entry {

	@Id
	@Name("cn")
	@Size(min = 3, max = 128, message = "Identifique melhor o nome da categoria.")
	private String name;

	private String order;

	protected String[] objectClass() {
		return new String[] { "websiteCategory" };
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
