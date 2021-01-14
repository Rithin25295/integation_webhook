package com.indrasol.supervue.supervuewebhook.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.indrasol.supervue.supervuewebhook.model.Admin;
import com.indrasol.supervue.supervuewebhook.model.AdminCredentials;

@Repository
public interface CredentialsRepo extends CrudRepository<AdminCredentials, Integer>{
	
	public boolean existsByUsername(String username);

	public AdminCredentials findByUsername(String username);
}
