/**
 * 
 */
package br.com.saber.tcc.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.saber.tcc.business.UserController;
import br.com.saber.tcc.entities.User;
import br.com.saber.tcc.exceptions.BusinessException;
import br.com.saber.tcc.exceptions.ExistentRecordException;
import br.com.saber.tcc.exceptions.PasswordNotMatchException;
import br.com.saber.tcc.filter.UserFilter;
import br.com.saber.tcc.persistence.IDao;
import br.com.saber.tcc.persistence.UserDao;
import br.com.saber.tcc.presentation.JsfUtil;
import br.com.saber.tcc.util.EncryptionHelper;
import br.com.saber.tcc.util.TokenGenerator;

/**
 * @author Victor Lira
 *
 */
@Stateless
public class UserControllerImpl extends AbstractRegisterController<User, UserFilter> implements UserController {
	private String urlResetPassword;
	
	@EJB
    private UserDao dao;
    
    @Override
    protected IDao<User, UserFilter> getDao() {
        return dao;
    }

    @Override
    protected void validateEntity(User user) throws BusinessException, ExistentRecordException {
        boolean isUnique = true;
        Integer id = user.getId();
        UserFilter filter = new UserFilter();
        filter.setEmail(user.getEmail());

        try {
			List<User> users = dao.list(filter);
			
			for (User usuariosSalvos : users) {
			    int savedIdentifier = usuariosSalvos.getId();
			    
			    if (id == null || savedIdentifier != id) {
			        isUnique = false;
			        break;
			    }
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        
        if (isUnique == false) {
            throw new ExistentRecordException(JsfUtil.getBundleMessage("USER_EXISTING") + user.getEmail());
        }
        
        if (urlResetPassword == null || !(urlResetPassword.contains("loginProblems.xhtml") || urlResetPassword.contains("resetPassword.xhtml")
        		|| urlResetPassword.contains("firstAccess.xhtml"))) {
        	searchByCPF(user.getCpf());
        }
        
    }

    @Override
    protected void prepareInsert(User user) {
        String token = TokenGenerator.generate();
        user.setToken(token);
    }
    
    @Override
    public User searchByToken(String token) { 
        UserFilter filter = new UserFilter();
        filter.setToken(token);
        
        List<User> list = list(filter);
        if (list.isEmpty()) {
            throw new ExistentRecordException(JsfUtil.getBundleMessage("USER_INVALID"));
        }
        
        return list.get(0);
    }
    
    @Override
    public boolean searchByCPF(String cpf){
    	UserFilter filter = new UserFilter();
    	filter.setCpf(cpf);
    	
    	List<User> list = list(filter);    	
    	if (!list.isEmpty()) {
            throw new ExistentRecordException(JsfUtil.getBundleMessage("USER_CPF_EXISTING") + cpf);
        }
    	
    	return true;
    }
    
    public User searchByEmail(String email) { 
    	UserFilter filter = new UserFilter();
        filter.setEmail(email);
        
        List<User> list = list(filter);
        if (list.isEmpty()) {
            throw new ExistentRecordException(JsfUtil.getBundleMessage("USER_INVALID"));
        }
        
        return list.get(0);
    }

    @Override
    public void updatePassword(String token, String password) {
        User user = searchByToken(token);
        user.setPassword(EncryptionHelper.encrypt(password));
        user.setToken(null);
        user.setHasChangedDefaultPassword(true);
        
        this.urlResetPassword = getCurrentUserURL();
        
        saveOrUpdate(user);
        
        this.urlResetPassword = null;
    }
    
    @Override
	public void resetPassword(String token, String password, String confirmPassword) {
    	if (!password.equals(confirmPassword)) {
    		throw new PasswordNotMatchException();
    	}
    	updatePassword(token, confirmPassword);
	}

	@Override
	public void requestToken(String userEmail) {
		String token = TokenGenerator.generate();
		
		User user = searchByEmail(userEmail);
        user.setToken(token);
        
        this.urlResetPassword = getCurrentUserURL();
        
        saveOrUpdate(user);
        
        this.urlResetPassword = null;
	}
	
	private String getCurrentUserURL(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		
		return request.getRequestURI();
	}
}

