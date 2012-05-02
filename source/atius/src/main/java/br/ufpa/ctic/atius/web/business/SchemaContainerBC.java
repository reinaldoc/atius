package br.ufpa.ctic.atius.web.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.web.domain.SchemaContainer;
import br.ufpa.ctic.atius.web.persistence.SchemaContainerDAO;

@BusinessController
public class SchemaContainerBC extends DelegateCrud<SchemaContainer, String, SchemaContainerDAO> {

	private static final long serialVersionUID = 1L;

}