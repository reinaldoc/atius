package br.ufpa.ctic.atius.view;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import br.ufpa.ctic.atius.domain.WebsiteCategory;

@Named
public class WebsiteCategoryListMB {

	public List<WebsiteCategory> getWebsiteCategory() {
		List<WebsiteCategory> list = new ArrayList<WebsiteCategory>();
		list.add(new WebsiteCategory("Institucional", 0));
		list.add(new WebsiteCategory("Institutos", 1));
		list.add(new WebsiteCategory("Faculdades", 2));
		return list;
	}

}
