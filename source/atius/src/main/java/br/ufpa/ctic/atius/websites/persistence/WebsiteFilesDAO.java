package br.ufpa.ctic.atius.websites.persistence;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.ufpa.ctic.atius.websites.domain.WebsiteFiles;

@PersistenceController
public class WebsiteFilesDAO extends JPACrud<WebsiteFiles, Long> {

	private static final long serialVersionUID = 1L;

}