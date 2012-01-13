package br.gov.frameworkdemoiselle.fuselage.business;

import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityResource;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityRole;
import br.gov.frameworkdemoiselle.fuselage.persistence.RoleDAO;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

public class RoleBC extends DelegateCrud<SecurityRole, Long, RoleDAO> {
	private static final long serialVersionUID = 1L;

	@Inject
	private ResourceBC resourceBC;

	@Transactional
	@Startup
	public void startup() {
		if (findAll().isEmpty()) {
			// insert(new SecurityRole());
		}
	}

	public List<SecurityResource> getResources() {
		return resourceBC.findAll();
	}

}
