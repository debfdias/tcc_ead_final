package br.com.saber.tcc.persistence;

import javax.ejb.Local;

import br.com.saber.tcc.entities.Advisor;
import br.com.saber.tcc.filter.AdvisorFilter;

@Local
public interface AdvisorDao extends IDao<Advisor, AdvisorFilter> {
}
