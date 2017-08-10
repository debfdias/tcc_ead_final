package br.com.saber.tcc.persistence;

import javax.ejb.Local;

import br.com.saber.tcc.entities.Profile;
import br.com.saber.tcc.filter.ProfileFilter;

@Local
public interface ProfileDao extends IDao<Profile, ProfileFilter>{

}
