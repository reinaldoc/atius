package br.gov.frameworkdemoiselle.fuselage.configuration;

import java.io.Serializable;
import java.util.List;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource = "fuselage", prefix = "fuselage.view")
public class ViewConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Name("resource.namesuggestions")
	private List<String> namesuggestions;

	public List<String> getNamesuggestions() {
		return namesuggestions;
	}

	public void setNamesuggestions(List<String> namesuggestions) {
		this.namesuggestions = namesuggestions;
	}

}
