package br.com.atius.web.persistence;

import br.com.atius.web.domain.WebsiteFiles;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;

@PersistenceController
public class WebsiteFilesDAO extends JPACrud<WebsiteFiles, Long> {

	private static final long serialVersionUID = 1L;

}