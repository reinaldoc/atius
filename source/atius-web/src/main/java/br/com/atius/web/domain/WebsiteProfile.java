package br.com.atius.web.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

public class WebsiteProfile extends Entry {

	@Id
	@Name("cn")
	@Size(min = 3, max = 128, message = "Identifique melhor o nome do perfil.")
	private String name;

	@NotEmpty(message = "Selecione o servidor web.")
	private String webserverName;

	@NotEmpty(message = "Selecione o servidor de banco de dados.")
	private String schemaserverName;

	protected String[] objectClass() {
		return new String[] { "websiteProfile" };
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebserverName() {
		return webserverName;
	}

	public void setWebserverName(String webserverName) {
		this.webserverName = webserverName;
	}

	public String getSchemaserverName() {
		return schemaserverName;
	}

	public void setSchemaserverName(String schemaserverName) {
		this.schemaserverName = schemaserverName;
	}

}
