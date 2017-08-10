package br.com.saber.tcc.business.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.saber.tcc.business.ProfileController;
import br.com.saber.tcc.entities.Profile;
import br.com.saber.tcc.exceptions.BusinessException;
import br.com.saber.tcc.exceptions.ExistentRecordException;
import br.com.saber.tcc.filter.ProfileFilter;
import br.com.saber.tcc.persistence.IDao;
import br.com.saber.tcc.persistence.ProfileDao;
import br.com.saber.tcc.presentation.JsfUtil;

/**
 * @author Victor Lira
 *
 */
@Stateless
public class ProfileControllerImpl extends AbstractRegisterController<Profile, ProfileFilter> implements ProfileController {
	
	@EJB
    private ProfileDao dao;
    
    @Override
    protected IDao<Profile, ProfileFilter> getDao() {
        return dao;
    }

    @Override
    protected void validateEntity(Profile profile) throws BusinessException, ExistentRecordException {
        boolean isUnique = true;
        Integer id = profile.getId();
        ProfileFilter filter = new ProfileFilter();
        filter.setName(profile.getName());
        
        List<Profile> profiles = dao.list(filter);
        for (Profile savedProfile : profiles) {
            int savedIdentifier = savedProfile.getId();
            if (savedIdentifier != id) {
                isUnique = false;
                break;
            }
        }
        if (isUnique == false) {
            throw new ExistentRecordException(JsfUtil.getBundleMessage("NEW_PROFILE_EXISTING") + profile.getName());
        }
    }

    @Override
    protected void prepareInsert(Profile profile) {

    }
	
}
