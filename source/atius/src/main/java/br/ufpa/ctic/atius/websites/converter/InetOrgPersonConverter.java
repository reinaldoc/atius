package br.ufpa.ctic.atius.websites.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufpa.ctic.atius.websites.domain.InetOrgPerson;

@FacesConverter("inetOrgPerson")
public class InetOrgPersonConverter implements Converter {

	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
		if (submittedValue.trim().isEmpty()) {
			return null;
		} else {
			InetOrgPerson inetOrgPerson = new InetOrgPerson();
			inetOrgPerson.setMail(submittedValue);
			return inetOrgPerson;
		}
	}

	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (value == null || value.equals("")) {
			return "";
		} else {
			return ((InetOrgPerson) value).getMail();
		}
	}
}
