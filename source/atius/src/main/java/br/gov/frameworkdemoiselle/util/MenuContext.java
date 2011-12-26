package br.gov.frameworkdemoiselle.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class MenuContext implements Serializable {

	static enum EnumSelectMode {
		MULTI, SINGLE
	};

	private EnumSelectMode selectMode = EnumSelectMode.SINGLE;

	private boolean permitUnselect = false;

	private static final long serialVersionUID = 1L;

	private Map<String, Map<String, Map<String, String>>> menu = new HashMap<String, Map<String, Map<String, String>>>();

	private String defaultSelectedStyleClass = "selected";
	
	public MenuContext() {
		select("MenuTop", "Sites");
		select("MenuEmail", "Institucional");
		select("MenuSites", "Institucional");
	}
	
	public boolean isSelected(String menuName, String itemName) {
		return isSelectedStyle(menuName, itemName, defaultSelectedStyleClass);
	}

	public boolean isSelectedStyle(String menuName, String itemName, String styleClass) {
		try {
			if (menu.get(menuName).get(itemName).get("Style") == styleClass) {
				return true;
			}
		} catch (Exception e) {
			// Ignore
		}
		return false;
	}

	private void unselectAll(String menuName) {
		Map<String, Map<String, String>> items = new HashMap<String, Map<String, String>>();
		if (menu.containsKey(menuName)) {
			items = menu.get(menuName);
			Iterator<String> iterItems = items.keySet().iterator();
			while (iterItems.hasNext()) {
				selectStyle(menuName, iterItems.next(), "");
			}
		}
	}

	private void selectStyle(String menuName, String itemName, String styleClass) {
		Map<String, Map<String, String>> items = new HashMap<String, Map<String, String>>();
		if (menu.containsKey(menuName)) {
			items = menu.get(menuName);
		}

		Map<String, String> values = new HashMap<String, String>();
		if (items.containsKey(itemName)) {
			values = items.get(itemName);
		}

		if (permitUnselect && styleClass.equals(values.get("Style"))) {
			values.put("Style", "");
		} else {
			values.put("Style", styleClass);
		}
		items.put(itemName, values);
		menu.put(menuName, items);
	}

	public void select(String menuName, String itemName, String styleClass) {
		if (selectMode == EnumSelectMode.SINGLE) {
			unselectAll(menuName);
		}
		selectStyle(menuName, itemName, styleClass);
	}

	public void select(String menuName, String itemName) {
		if (selectMode == EnumSelectMode.SINGLE) {
			unselectAll(menuName);
		}
		select(menuName, itemName, defaultSelectedStyleClass);
	}

	public Map<String, Map<String, Map<String, String>>> getMenu() {
		return menu;
	}

	public EnumSelectMode getSelectMode() {
		return selectMode;
	}

	public void setSelectMode(EnumSelectMode selectMode) {
		this.selectMode = selectMode;
	}

	public boolean isPermitUnselect() {
		return permitUnselect;
	}

	public void setPermitUnselect(boolean permitUnselect) {
		this.permitUnselect = permitUnselect;
	}

	public String getDefaultSelectedStyleClass() {
		return defaultSelectedStyleClass;
	}

	public void setDefaultSelectedStyleClass(String defaultSelectedStyleClass) {
		this.defaultSelectedStyleClass = defaultSelectedStyleClass;
	}

}
