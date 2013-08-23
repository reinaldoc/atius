package br.com.atius.core.business;

import br.com.atius.core.domain.Group;
import br.com.atius.core.persistence.GroupDAO;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;

@BusinessController
public class GroupBC extends DelegateCrud<Group, String, GroupDAO> {

	private static final long serialVersionUID = 1L;

}
