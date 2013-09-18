package br.com.atius.core.domain;

import br.gov.frameworkdemoiselle.ldap.annotation.Id;
import br.gov.frameworkdemoiselle.ldap.annotation.LDAPEntry;
import br.gov.frameworkdemoiselle.ldap.template.Entry;

@LDAPEntry
public class Group extends Entry {

	@Id
	private String cn;

	private String[] member;

	private String[] memberOf;

	private String description;

	@Override
	protected String[] objectClass() {
		return null;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String[] getMember() {
		return member;
	}

	public void setMember(String[] member) {
		this.member = member;
	}

	public String[] getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(String[] memberOf) {
		this.memberOf = memberOf;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cn == null) ? 0 : cn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (cn == null) {
			if (other.cn != null)
				return false;
		} else if (!cn.equals(other.cn))
			return false;
		return true;
	}
}
