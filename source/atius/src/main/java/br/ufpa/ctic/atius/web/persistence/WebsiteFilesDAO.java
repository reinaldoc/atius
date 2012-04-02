package br.ufpa.ctic.atius.web.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.contrib.JPACrud;
import br.ufpa.ctic.atius.web.domain.WebsiteFiles;

@PersistenceController
public class WebsiteFilesDAO extends JPACrud<WebsiteFiles, Long> {

	private static final long serialVersionUID = 1L;

}