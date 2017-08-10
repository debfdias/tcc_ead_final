package br.com.saber.tcc.persistence;

import javax.ejb.Local;

import br.com.saber.tcc.entities.User;
import br.com.saber.tcc.filter.UserFilter;

@Local
public interface UserDao extends IDao<User, UserFilter>{

}
