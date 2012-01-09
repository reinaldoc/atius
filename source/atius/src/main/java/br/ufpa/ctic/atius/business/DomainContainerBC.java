package br.ufpa.ctic.atius.business;

import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.ufpa.ctic.atius.domain.DomainContainer;
import br.ufpa.ctic.atius.persistence.DomainContainerDAO;

@BusinessController
public class DomainContainerBC extends DelegateCrud<DomainContainer, String, DomainContainerDAO> {

	private static final long serialVersionUID = 1L;
	
	public boolean setNextUidNumber(String dn, int nextUidNumber) {
		DomainContainer domainContainer = new DomainContainer();
		domainContainer.setDn(dn);
		domainContainer.setNextUidNumber(new Integer(nextUidNumber));
		getDelegate().update(domainContainer);
		Integer uidNumber = getDelegate().getReference(dn).getNextUidNumber();
		if (uidNumber != null && uidNumber.intValue() == nextUidNumber)
			return true;
		return false;
	}

}