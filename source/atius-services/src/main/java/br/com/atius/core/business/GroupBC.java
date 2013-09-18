package br.com.atius.core.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.atius.core.domain.Group;
import br.com.atius.core.persistence.GroupDAO;
import br.gov.frameworkdemoiselle.query.contrib.QueryConfig;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.contrib.DelegateCrud;

@BusinessController
public class GroupBC extends DelegateCrud<Group, String, GroupDAO> {

	private static final long serialVersionUID = 1L;

	private static final int MAX_RECURSIVE_PARENT_GROUPS_CALLS = 10;

	private Set<Group> getParentGroups(Group group, int count) {
		Set<Group> groups = new HashSet<Group>();
		if (count == MAX_RECURSIVE_PARENT_GROUPS_CALLS) {
			System.out.println("WARNING: MAX RECURSIVE PARENT GROUPS REACHED FOR " + group.getCn());
			return groups;
		}

		if (group.getMemberOf() != null)
			for (String parent : group.getMemberOf()) {
				Group groupParent = getDelegate().getReference(parent);
				groups.add(groupParent);
				groups.addAll(getParentGroups(groupParent, count + 1));

			}

		return groups;
	}

	public List<Group> getParentGroups(List<Group> groups) {
		Set<Group> result = new HashSet<Group>();
		result.addAll(groups);
		for (Group group : groups)
			result.addAll(getParentGroups(group, 0));
		List<Group> resultList = new ArrayList<Group>(result);
		Collections.sort(resultList, new GroupComparator());
		return resultList;
	}

	public List<Group> getGroupsByUserDn(String dn) {
		QueryConfig<Group> queryConfig = getQueryConfig();
		queryConfig.getFilter().put("member", dn);
		queryConfig.setMaxResults(30);
		return getParentGroups(findAll());
	}

}

class GroupComparator implements Comparator<Group> {

	public int compare(Group e1, Group e2) {
		return e1.getCn().compareTo(e2.getCn());
	}

}
