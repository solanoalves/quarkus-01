package br.solano.business;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import br.solano.repository.StoreRepository;

@Dependent
public class StoreBusiness {
	@Inject
	StoreRepository storeRepository;
	
	public void resetRepository() {
		storeRepository.populate();
	}
}
