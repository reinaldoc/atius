package br.com.atius.web.business;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import br.com.atius.web.common.WebConfig;
import br.com.atius.web.domain.WebsiteProfile;
import br.com.atius.web.persistence.WebsiteProfileDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

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
