package br.gov.frameworkdemoiselle.fuselage.business;

import java.util.List;

import br.gov.frameworkdemoiselle.annotation.Startup;
import br.gov.frameworkdemoiselle.fuselage.domain.SecurityUser;
import br.gov.frameworkdemoiselle.fuselage.persistence.UserDAO;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

public class UserBC extends DelegateCrud<SecurityUser, Long, UserDAO> {
	private static final long serialVersionUID = 1L;

	@Transactional
	@Startup
	public void startup() {
		if (findAll().isEmpty()) {
			insert(new SecurityUser("faa-admin", "Usuário Administrador", "adminpass"));
		}
	}

	public void insert(String login, String name, String orgunit, String description) {
		if (login != null && !login.isEmpty()) {
			SecurityUser bean = loadByLogin(login);
			if (bean.getId() == null) {
				insert(new SecurityUser(login, name, orgunit, description));
			} else {
				bean.setName(name);
				bean.setOrgunit(orgunit);
				bean.setDescription(description);
				update(bean);
			}
		}
	}

	public SecurityUser loadByLogin(String login) {
		List<SecurityUser> userList = findByLogin(login);
		if (userList.size() != 1) {
			return new SecurityUser();
		} else {
			return userList.get(0);
		}
	}

	public List<SecurityUser> findByLogin(String login) {
		SecurityUser userLoad = new SecurityUser();
		userLoad.setLogin(login);
		return getDelegate().findByExample(userLoad);
	}

}
