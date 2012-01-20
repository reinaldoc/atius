package br.ufpa.ctic.atius.persistence;

import java.util.List;
import java.util.Map;

import br.gov.frameworkdemoiselle.ldap.core.EntryQuery;
import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.util.Strings;
import br.ufpa.ctic.atius.domain.InetOrgPerson;

@PersistenceController
public class InetOrgPersonDAO extends LDAPCrud<InetOrgPerson, String> {

	private static final long serialVersionUID = 1L;

	private InetOrgPerson entry2inetOrgPerson(Map<String, String[]> entry) {
		InetOrgPerson inetOrgPerson = new InetOrgPerson();
		if (entry.size() == 0)
			return inetOrgPerson;
		inetOrgPerson.setDn(entry.get("dn")[0]);
		inetOrgPerson.setObjectClass(entry.get("objectClass"));
		inetOrgPerson.setCn(entry.get("cn")[0]);
		inetOrgPerson.setMail(entry.get("mail")[0]);
		return inetOrgPerson;
	}

	@SuppressWarnings("unchecked")
	public List<InetOrgPerson> findPerson(String search) {
		search = Strings.null2empty(search);
		EntryQuery query = getEntryManager().createQuery("(&(objectClass=inetOrgPerson)(|(cn=*" + search + "*)(mail=*" + search + "*)))");
		query.setMaxResults(10);
		return query.getResultList();
	}

	public InetOrgPerson load(String mail) {
		InetOrgPerson inetOrgPerson = new InetOrgPerson();
		if (!Strings.isBlank(mail)) {
			inetOrgPerson = (InetOrgPerson) getEntryManager().createQuery("(&(objectClass=inetOrgPerson)(mail=" + mail + "))").getSingleResult();
		}
		return inetOrgPerson;
	}

}