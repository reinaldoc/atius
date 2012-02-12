package br.ufpa.ctic.atius.websites.view.list;

import java.util.List;

import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;

import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.contrib.AbstractListPageBean;
import br.ufpa.ctic.atius.websites.business.WebsiteFilesBC;
import br.ufpa.ctic.atius.websites.domain.WebsiteDomain;
import br.ufpa.ctic.atius.websites.domain.WebsiteFiles;

@ViewController
public class WebsiteFilesListMB extends AbstractListPageBean<WebsiteFiles, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private WebsiteFilesBC bc;

	private WebsiteDomain websiteDomain;

	private WebsiteFiles bean;

	@Override
	protected List<WebsiteFiles> handleResultList(QueryConfig<WebsiteFiles> queryConfig) {
		if (websiteDomain == null)
			return null;
		queryConfig.setSorting("serverName");
		return bc.findByServerName(websiteDomain.getServerName());
	}

	public void selectWebsite(WebsiteDomain websiteDomain) {
		clearResultList();
		this.websiteDomain = websiteDomain;
	}

	public void upload(FileUploadEvent event) {
		clearResultList();
		bc.insert(websiteDomain, event.getFile());
	}

	public void selectWebsiteFile(WebsiteFiles websiteFiles) {
		this.bean = websiteFiles;
	}

	public WebsiteFiles getWebsiteFiles() {
		return bean;
	}

	public void delete() {
		bc.delete(bean.getId());
	}

}
