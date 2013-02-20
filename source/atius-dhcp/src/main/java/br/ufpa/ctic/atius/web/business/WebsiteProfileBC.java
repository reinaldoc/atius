package br.ufpa.ctic.atius.web.business;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.web.common.WebConfig;
import br.ufpa.ctic.atius.web.domain.WebsiteProfile;
import br.ufpa.ctic.atius.web.persistence.WebsiteProfileDAO;

@BusinessController
public class WebsiteProfileBC extends DelegateCrud<WebsiteProfile, String, WebsiteProfileDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebConfig webConfig;

	public void insert(WebsiteProfile websiteProfile) {
		websiteProfile.setParentDN(webConfig.getProfileContainerDN());
		getDelegate().insert(websiteProfile);
	}

	public List<String> getNames() {
		List<String> websiteProfiles = new ArrayList<String>();
		for (WebsiteProfile websiteProfile : getDelegate().findAll())
			websiteProfiles.add(websiteProfile.getName());
		Collections.sort(websiteProfiles, Collator.getInstance(Locale.getDefault()));
		return websiteProfiles;
	}
}
