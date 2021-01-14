package com.indrasol.supervue.supervuewebhook.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indrasol.supervue.supervuewebhook.model.Tenant;


@Repository
public interface TenantRepo extends CrudRepository<Tenant, Integer>{
	
	public List<Tenant> findByRegistrationId(String registrationId);
	
	public boolean existsByRegistrationId(String registrationId);

}
