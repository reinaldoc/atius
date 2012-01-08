package br.ufpa.ctic.atius.business;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.domain.WebsiteProfile;
import br.ufpa.ctic.atius.persistence.WebsiteProfileDAO;

@BusinessController
public class WebsiteProfileBC extends DelegateCrud<WebsiteProfile, String, WebsiteProfileDAO> {

	private static final long serialVersionUID = 1L;

	public List<String> getNames() {
		List<String> websiteProfiles = new ArrayList<String>();
		for (WebsiteProfile websiteProfile : getDelegate().findAll())
			websiteProfiles.add(websiteProfile.getName());
		Collections.sort(websiteProfiles, Collator.getInstance(Locale.getDefault()));
		return websiteProfiles;
	}
}
