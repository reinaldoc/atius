package br.gov.frameworkdemoiselle.fuselage.business;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityProfile;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityResource;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityRole;
import br.gov.frameworkdemoiselle.fuselage.persistence.ProfileDAO;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

public class ProfileBC extends DelegateCrud<SecurityProfile, Long, ProfileDAO> {
	private static final long serialVersionUID = 1L;

	@Inject
	private RoleBC roleBC;

	@Inject
	private ResourceBC resourceBC;

	@Transactional
	@Startup
	public void startup() {
		if (findAll().isEmpty()) {
			// insert(new SecurityProfile());
		}
	}

	public List<Long> getUsedPrioritiesExceptMyself(SecurityProfile securityProfile) {
		List<Long> usedPriorities = new ArrayList<Long>();
		for (Long usedPriority : getDelegate().getUsedPrioritiesExceptMyself(securityProfile))
			usedPriorities.add(usedPriority);
		return usedPriorities;
	}

	public List<SecurityRole> getRoles() {
		return roleBC.findAll();
	}

	public List<SecurityResource> getResources() {
		return resourceBC.findAll();
	}

}
