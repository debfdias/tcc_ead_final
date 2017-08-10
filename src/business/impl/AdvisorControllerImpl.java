package br.com.saber.tcc.business.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.saber.tcc.business.AdvisorController;
import br.com.saber.tcc.entities.Advisor;
import br.com.saber.tcc.exceptions.BusinessException;
import br.com.saber.tcc.exceptions.ExistentRecordException;
import br.com.saber.tcc.filter.AdvisorFilter;
import br.com.saber.tcc.persistence.AdvisorDao;
import br.com.saber.tcc.persistence.IDao;

@Stateless
public class AdvisorControllerImpl extends AbstractRegisterController<Advisor, AdvisorFilter> implements AdvisorController{
	
	@EJB
	private AdvisorDao dao;
	
	@Override
	protected IDao<Advisor, AdvisorFilter> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	protected void validateEntity(Advisor entity) throws BusinessException, ExistentRecordException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void prepareInsert(Advisor entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Advisor> list(AdvisorFilter options, boolean orderByName) {
		List<Advisor> advisors = getDao().list(options);
		
		if(orderByName) {
			Collections.sort(advisors, new Comparator<Advisor>() {
				
				@Override
				public int compare(Advisor a, Advisor b) {
					return a.getName().compareTo(b.getName());
				}
				
			});
		}
		
		return advisors;
	}

}
