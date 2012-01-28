package br.ufpa.ctic.atius.websites.business;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.primefaces.model.UploadedFile;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.websites.domain.InetOrgPerson;
import br.ufpa.ctic.atius.websites.domain.WebsiteDomain;
import br.ufpa.ctic.atius.websites.domain.WebsiteFiles;
import br.ufpa.ctic.atius.websites.persistence.WebsiteFilesDAO;

@BusinessController
public class WebsiteFilesBC extends DelegateCrud<WebsiteFiles, Long, WebsiteFilesDAO> {

	private static final long serialVersionUID = 1L;

	@Inject
	private InetOrgPersonBC inetOrgPersonBC;

	public void insert(WebsiteDomain websiteDomain, UploadedFile file) {
		InetOrgPerson inetOrgPerson = inetOrgPersonBC.load(websiteDomain.getOwnerId().getMail());
		WebsiteFiles websiteFiles = new WebsiteFiles();
		websiteFiles.setServerName(websiteDomain.getServerName());
		websiteFiles.setMail(websiteDomain.getOwnerId().getMail());
		websiteFiles.setOwner(inetOrgPerson.getCn());
		websiteFiles.setFile(file.getContents());
		websiteFiles.setContentType(file.getContentType());
		websiteFiles.setFilename(file.getFileName());
		websiteFiles.setDate(new Date());
		getDelegate().insert(websiteFiles);
	}

	public List<WebsiteFiles> findByServerName(String serverName) {
		WebsiteFiles websiteFiles = new WebsiteFiles();
		websiteFiles.setServerName(serverName);
		return getDelegate().findByExample(websiteFiles, true, 0);
	}

}