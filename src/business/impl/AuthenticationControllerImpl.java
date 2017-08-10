package br.com.saber.tcc.business.impl;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.saber.tcc.business.AuthenticationController;
import br.com.saber.tcc.entities.User;
import br.com.saber.tcc.exceptions.BusinessException;
import br.com.saber.tcc.exceptions.InvalidLoginOrPasswordException;
import br.com.saber.tcc.filter.UserFilter;
import br.com.saber.tcc.persistence.UserDao;
import br.com.saber.tcc.persistence.jpa.UserDaoImpl;

@Stateless
public class AuthenticationControllerImpl implements AuthenticationController {
    
    @EJB
    private UserDao dao;
    
    public AuthenticationControllerImpl() {
    	dao = new UserDaoImpl();
    }
    
    public void login(User user) throws BusinessException {
    	User userFromDB = getUserByEmail(user);
        
        if (!Arrays.equals(user.getPassword(), userFromDB.getPassword())) {
        	throw new InvalidLoginOrPasswordException();
        }
    }

    public User getUserByEmail(User user) throws InvalidLoginOrPasswordException {
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
        	System.out.println("Usuario, email ou senha null");
            throw new InvalidLoginOrPasswordException();
        }
                
        String userValidate = user.getEmail().substring(0, user.getEmail().indexOf('@') + 1);
        String domValidate  = user.getEmail().substring(user.getEmail().indexOf('@') + 1, user.getEmail().length());   
            
        char last = domValidate.charAt(domValidate.length() - 1);
        
        if( (userValidate.length() <= 1) || 
        	(domValidate.length() <= 3   || domValidate.indexOf('.') == -1) ||
        	(user.getEmail().indexOf('@') < 0) ||
        	(userValidate.indexOf(' ') != -1 || (domValidate.indexOf(' ') != -1 && last !=' '))
        	)
        {
        	
        	throw new InvalidLoginOrPasswordException(); 
        }
        
        UserFilter filter = new UserFilter();
        filter.setEmail(user.getEmail());
        List<User> lista = dao.list(filter);
        if (lista.isEmpty()) {
            throw new InvalidLoginOrPasswordException();
        }
        
        return lista.get(0);
    }
}
