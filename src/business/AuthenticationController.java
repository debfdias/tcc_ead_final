package br.com.saber.tcc.business;

import javax.ejb.Local;

import br.com.saber.tcc.entities.User;
import br.com.saber.tcc.exceptions.BusinessException;
import br.com.saber.tcc.exceptions.InvalidLoginOrPasswordException;

@Local
public interface AuthenticationController {

	public static String AUTENTICATED_USER = "autenticatedUser";
	
	void login(User user) throws BusinessException;
	User getUserByEmail(User user) throws InvalidLoginOrPasswordException;
}
