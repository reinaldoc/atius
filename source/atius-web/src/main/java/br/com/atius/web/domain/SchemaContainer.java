package br.com.atius.web.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class SchemaContainer extends Entry {

	@Id
	private String cn;

	public SchemaContainer() {
		super();
	}

	public SchemaContainer(boolean skipObjectClass) {
		super(skipObjectClass);
	}

	protected String[] objectClass() {
		return new String[] { "schemaContainer" };
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

}
