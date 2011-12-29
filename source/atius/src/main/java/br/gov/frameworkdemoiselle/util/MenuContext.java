package br.gov.frameworkdemoiselle.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class MenuContext extends AbstractMenuContext {

	private static final long serialVersionUID = 1L;

	@RequestScoped
	@Inject
	private Parameter<String> menuName;

	@RequestScoped
	@Inject
	private Parameter<String> itemName;

	public MenuContext() {
		select("MenuTop", "Email");
		select("MenuEmail", "Institucional");
		select("MenuSites", "Institucional");
	}

	@Override
	public String getSelectedStyleClass() {
		return "selected";
	}

	@Override
	protected boolean isSingleSelection() {
		return true;
	}

	@Override
	protected boolean isPermitedUnselect() {
		return false;
	}

	public String selectByParameters() {
		if (menuName.getValue() != null && itemName.getValue() != null)
			select(menuName.getValue(), itemName.getValue());
		return "index.jsf";
	}

}
