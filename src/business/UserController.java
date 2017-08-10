package br.com.saber.tcc.business;

import javax.ejb.Local;

import br.com.saber.tcc.entities.User;
import br.com.saber.tcc.filter.UserFilter;

@Local
public interface UserController extends IController<User, UserFilter>{

	void updatePassword(String token, String password);
	void resetPassword(String token, String password, String confirmPassword);
	boolean searchByCPF(String cpf);
	User searchByEmail(String email);
	User searchByToken(String token);
	void requestToken(String userEmail);
}
