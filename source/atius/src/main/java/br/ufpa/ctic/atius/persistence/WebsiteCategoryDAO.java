package br.ufpa.ctic.atius.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.LDAPCrud;
import br.ufpa.ctic.atius.domain.WebsiteCategory;

@PersistenceController
public class WebsiteCategoryDAO extends LDAPCrud<WebsiteCategory, String> {

	private static final long serialVersionUID = 1L;

	@Override
	public List<WebsiteCategory> findAll() {
		List<WebsiteCategory> websiteCategories = new ArrayList<WebsiteCategory>();
		Collection<Map<String, String[]>> entries = getEntryManager().createQuery("objectClass=websiteCategory").getResultCollection();
		for (Map<String, String[]> entry : entries) {
			WebsiteCategory websiteCategory = new WebsiteCategory();
			websiteCategory.setName(entry.get("cn")[0]);
			websiteCategory.setOrder(entry.get("order")[0]);
			websiteCategories.add(websiteCategory);
		}
		return websiteCategories;
	}
}
