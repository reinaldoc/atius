package br.gov.frameworkdemoiselle.fuselage.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.gov.frameworkdemoiselle.fuselage.domain.SecurityResource;

@FacesConverter("securityResource")
public class SecurityResourceConverter implements Converter {

	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		System.out.println("o======> " + value);
		if (value.trim().isEmpty()) {
			SecurityResource securityResource = new SecurityResource();
			securityResource.setId(new Long(0));
			securityResource.setName("xxx");
			securityResource.setValue("xxx");
			return securityResource;
		} else {
			SecurityResource securityResource = new SecurityResource();
			securityResource.setId(new Long(value));
			securityResource.setName("xxx");
			securityResource.setValue("xxx");
			return securityResource;
		}
	}

	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		System.out.println("s======> " + value);
		if (value == null || value.equals("")) {
			return "";
		} else {
			return ((SecurityResource) value).getId().toString();
		}
	}

}
