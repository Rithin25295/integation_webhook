package com.indrasol.supervue.supervuewebhook.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonElement;
import com.indrasol.supervue.supervuewebhook.model.Admin;


@Repository
public interface AdminRepo extends CrudRepository<Admin, Integer>{
	
	public boolean existsByClientId(String clientId);
	
	public Admin findByClientId(JsonElement clientId);

}
