package br.com.atius.web.business;

import br.com.atius.web.domain.SchemaContainer;
import br.com.atius.web.persistence.SchemaContainerDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;

@BusinessController
public class SchemaContainerBC extends DelegateCrud<SchemaContainer, String, SchemaContainerDAO> {

	private static final long serialVersionUID = 1L;

}