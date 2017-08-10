package br.com.saber.tcc.business;

import java.util.List;

import javax.ejb.Local;

import br.com.saber.tcc.entities.Advisor;
import br.com.saber.tcc.filter.AdvisorFilter;

@Local
public interface AdvisorController extends IController<Advisor, AdvisorFilter>{
	
	List<Advisor> list(AdvisorFilter options, boolean orderByName);

}
