package br.gov.frameworkdemoiselle.fuselage.view.edit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.fuselage.business.ProfileBC;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityProfile;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityResource;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

@ViewController
public class ProfileEditMB extends AbstractEditPageBean<SecurityProfile, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProfileBC bc;

	@Override
	public String insert() {
		bc.insert(getBean());
		return null;
	}

	@Override
	public String update() {
		bc.update(getBean());
		return null;
	}

	@Override
	public String delete() {
		bc.delete(getBean().getId());
		return null;
	}

	@Override
	public SecurityProfile load(Long id) {
		return bc.load(id);
	}

	public List<Long> resourcePrio() {
		List<Long> priorities = new ArrayList<Long>();
		for (int i = 1; i < 101; i++)
			priorities.add(new Long(i));
		return priorities;
	}

	/**
	 * Get all SecurityResources for selectOneMenu to select welcome page
	 * 
	 * @return list of all SecurityResources
	 */
	public List<SecurityResource> getResourceList() {
		return bc.getResources();
	}

	/**
	 * Get all SecurityRoles for datatable
	 * 
	 * @return list of all SecurityRoles
	 */
	public List<SecurityRole> getRoleList() {
		return bc.getRoles();
	}

	/**
	 * Get SecurityRoles from current bean as array for datatable selection
	 * 
	 * @return array of bean SecurityRoles
	 */
	public SecurityRole[] getRoleArray() {
		if (getBean().getRoles() == null)
			return null;
		return getBean().getRoles().toArray(new SecurityRole[0]);
	}

	/**
	 * Set SecurityRoles on current bean from datatable selection array
	 * 
	 * @param roles
	 *            array of SecurityRoles to set current bean
	 */
	public void setRoleArray(SecurityRole[] roles) {
		getBean().setRoles(Arrays.asList(roles));
	}

}