package br.ufpa.ctic.atius.web.common;

import java.io.Serializable;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(resource = "atius", prefix = "web")
public class WebConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Name("basedn")
	private String websiteContainerDN;

	@Name("category.basedn")
	private String categoryContainerDN;

	public String getWebsiteContainerDN() {
		return websiteContainerDN;
	}

	public String getCategoryContainerDN() {
		return categoryContainerDN;
	}

}
