package br.com.atius.web.business;

import javax.inject.Inject;

import br.com.atius.web.domain.DomainContainer;
import br.com.atius.web.domain.WebsiteDomain;
import br.com.atius.web.domain.WebsiteProfile;
import br.com.atius.web.persistence.DomainContainerDAO;
import br.gov.frameworkdemoiselle.message.DefaultMessage;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.util.contrib.Faces;
import br.gov.frameworkdemoiselle.util.contrib.Strings;

@BusinessController
public class DomainContainerBC extends DelegateCrud<DomainContainer, String, DomainContainerDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteProfileBC websiteProfileBC;

	public DomainContainer getNextFreeUidNumber(String webserverName) {
		try {
			DomainContainer domainContainer = load(webserverName);
			domainContainer.setNextUidNumber(domainContainer.getNextUidNumber() + 1);
			update(domainContainer);
			domainContainer.setNextUidNumber(domainContainer.getNextUidNumber() - 1);
			return domainContainer;
		} catch (Exception e) {
			Faces.validationFailed();
			Faces.addMessage(new DefaultMessage("Não foi possível inicializar o webserver " + webserverName));
			return null;
		}
	}

	public DomainContainer getNextFreeUidNumber(WebsiteDomain websiteDomain) {
		WebsiteProfile websiteProfile = websiteProfileBC.load(websiteDomain.getWebsiteProfile());
		if (websiteProfile == null) {
			Faces.addMessage(new DefaultMessage("Não foi possível inicializar o perfil web " + websiteDomain.getWebsiteProfile()));
			Faces.validationFailed();
			return null;
		}
		if (Strings.isBlank(websiteProfile.getWebserverName())) {
			Faces.addMessage(new DefaultMessage("Não foi possível identificar o webserver do perfil web " + websiteDomain.getWebsiteProfile()));
			Faces.validationFailed();
			return null;
		}
		return getNextFreeUidNumber(websiteProfile.getWebserverName());
	}

}