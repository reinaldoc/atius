package br.ufpa.ctic.atius.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.gov.frameworkdemoiselle.ldap.core.EntryQuery;
import br.gov.frameworkdemoiselle.ldap.template.LDAPCrud;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.util.StringUtils;
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

	public List<InetOrgPerson> findPerson(String search) {
		search = StringUtils.null2empty(search);
		List<InetOrgPerson> inetOrgPersons = new ArrayList<InetOrgPerson>();
		EntryQuery query = getEntryManager().createQuery("(&(objectClass=inetOrgPerson)(|(cn=*" + search + "*)(mail=*" + search + "*)))");
		query.setMaxResults(10);
		Collection<Map<String, String[]>> entries = query.getResultCollection();
		for (Map<String, String[]> entry : entries) {
			inetOrgPersons.add(entry2inetOrgPerson(entry));
		}
		return inetOrgPersons;
	}

	public InetOrgPerson load(String mail) {
		InetOrgPerson inetOrgPerson = new InetOrgPerson();
		if (!StringUtils.isBlank(mail)) {
			Map<String, String[]> entry = getEntryManager().createQuery("(&(objectClass=inetOrgPerson)(mail=" + mail + "))")
					.getSingleResult();
			inetOrgPerson = entry2inetOrgPerson(entry);
		}
		return inetOrgPerson;
	}

}