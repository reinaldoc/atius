package br.ufpa.ctic.atius.websites.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.websites.domain.WebsiteFiles;
import br.ufpa.ctic.atius.websites.persistence.WebsiteFilesDAO;

@BusinessController
public class WebsiteFilesBC extends DelegateCrud<WebsiteFiles, Long, WebsiteFilesDAO> {

	private static final long serialVersionUID = 1L;

}