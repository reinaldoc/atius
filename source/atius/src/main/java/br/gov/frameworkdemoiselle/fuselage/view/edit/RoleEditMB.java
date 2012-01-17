package br.gov.frameworkdemoiselle.fuselage.view.edit;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.fuselage.business.RoleBC;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityResource;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityRole;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;

@ViewController
public class RoleEditMB extends AbstractEditPageBean<SecurityRole, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private RoleBC bc;

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
	public SecurityRole load(Long id) {
		return bc.load(id);
	}

	/**
	 * Get all SecurityResources for datatable
	 * 
	 * @return list of all SecurityResources
	 */
	public List<SecurityResource> getResourceList() {
		return bc.getResources();
	}

	/**
	 * Get SecurityResources from current bean as array for datatable selection
	 * 
	 * @return array of bean SecurityResources
	 */
	public SecurityResource[] getResourceArray() {
		if (getBean().getResources() == null)
			return null;
		return getBean().getResources().toArray(new SecurityResource[0]);
	}

	/**
	 * Set SecurityResources on current bean from datatable selection array
	 * 
	 * @param resources
	 *            array of SecurityResources to set current bean
	 */
	public void setResourceArray(SecurityResource[] resources) {
		getBean().setResources(Arrays.asList(resources));
	}

}