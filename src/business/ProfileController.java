package br.com.saber.tcc.business;

import javax.ejb.Local;

import br.com.saber.tcc.entities.Profile;
import br.com.saber.tcc.filter.ProfileFilter;

@Local
public interface ProfileController extends IController<Profile, ProfileFilter>{

}
