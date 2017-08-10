package br.com.saber.tcc.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Advisor extends User implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Collection<AdvisorClassAllocation> allocations;

	@OneToMany(mappedBy = "advisor")
	public Collection<AdvisorClassAllocation> getAllocations() {
		return allocations;
	}

	public void setAllocations(Collection<AdvisorClassAllocation> allocations) {
		this.allocations = allocations;
	}
	
}
