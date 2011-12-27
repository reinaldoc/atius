package br.gov.frameworkdemoiselle.util;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class MenuContext extends AbstractMenuContext {

	private static final long serialVersionUID = 1L;

	public MenuContext() {
		select("MenuTop", "Sites");
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

}
