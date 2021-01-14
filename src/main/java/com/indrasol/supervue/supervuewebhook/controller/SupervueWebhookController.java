package com.indrasol.supervue.supervuewebhook.controller;

import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.indrasol.supervue.supervuewebhook.dto.AdminDto;
import com.indrasol.supervue.supervuewebhook.dto.TenantDto;
import com.indrasol.supervue.supervuewebhook.model.Admin;
import com.indrasol.supervue.supervuewebhook.model.AdminCredentials;
import com.indrasol.supervue.supervuewebhook.model.DummyObject;
import com.indrasol.supervue.supervuewebhook.model.Event;
import com.indrasol.supervue.supervuewebhook.model.EventCode;
import com.indrasol.supervue.supervuewebhook.model.IncidentBodyPOJO;
import com.indrasol.supervue.supervuewebhook.model.Link;
import com.indrasol.supervue.supervuewebhook.model.Tenant;
import com.indrasol.supervue.supervuewebhook.repositories.AdminRepo;
import com.indrasol.supervue.supervuewebhook.repositories.CredentialsRepo;
import com.indrasol.supervue.supervuewebhook.repositories.TenantRepo;
import com.indrasol.supervue.supervuewebhook.service.SupervueWebhookService;
import com.indrasol.supervue.supervuewebhook.util.EncryptionDecryptionAES;
import com.indrasol.supervue.supervuewebhook.util.ObjectConversion;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/indrasol")
@Configuration
@PropertySource("classpath:application.properties")
public class SupervueWebhookController {

    @Autowired
    private SupervueWebhookService service;

    @Autowired
    private Environment env;

    @Autowired
    private WebClient.Builder webClientBuilder;
    
    @Autowired
    private TenantRepo repo;
    
    @Autowired
    private AdminRepo adminRepo;
    
    @Autowired
    private CredentialsRepo credRepo;
    
    
    @PostMapping("/webhook")
    public JsonObject handleSuperVueEvent(@RequestBody JsonObject eventData) throws ParseException, MessagingException, IOException {
        
    	//Find the client id
    	Admin admin = adminRepo.findByClientId(eventData.get("clientId"));
    	//Get the record for details
    	String url = admin.getUrl();
        String username = admin.getUsername();
        String password = admin.getPassword();
    	//Create the incident with the details    	
    	Gson gson = new Gson();
        IncidentBodyPOJO requestBody = service.requestBody(eventData);
        String jsonBody = gson.toJson(requestBody);
        System.out.print(jsonBody);
        
        System.out.println(requestBody);
        String incidentEventUrl = url+ "/hcmRestApi/resources/11.13.18.05/incidentKiosks";

        String response = webClientBuilder.build()
                .post()
                .uri(incidentEventUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers -> headers.setBasicAuth(username,password))
                .body(BodyInserters.fromValue(jsonBody))
                .exchange()
                .block()
                .bodyToMono(String.class)
                .block();
        System.out.println(response);
        JsonObject jsonResponse = gson.fromJson(response,JsonObject.class);
        System.out.println(jsonResponse.get("IncidentId"));
        //Send the email with the attachment
        service.sendEmailWithAttachment();
        return jsonResponse;
    }
    
    @PostMapping("/emailcheck")
    public JsonObject emailCheck(@RequestBody TenantDto tenantDto) throws IOException, URISyntaxException, MessagingException {
    	System.out.println(tenantDto);
    	String registrationId = 
    			tenantDto.getEmail().toLowerCase()+"-"+tenantDto.getFirstName().toLowerCase()+"-"+tenantDto.getCompanyName().toLowerCase();
    	boolean isregisteredId = repo.existsByRegistrationId(registrationId); 
    	
    	if(!isregisteredId) {
    		Tenant tenant = Tenant.builder()
    				.registrationId(registrationId)
    				.contact(tenantDto.getContact())
    				.email(tenantDto.getEmail())
    				.firstName(tenantDto.getFirstName())
    				.lastName(tenantDto.getLastName())
    				.country(tenantDto.getCountry())
    				.companyName(tenantDto.getCompanyName())
    				.build();
    		repo.save(tenant);
    		//Send Email notification
    		service.sendEmail(tenantDto);
    		JsonObject jsonResponse = service.giveResponse("Success");
        	return jsonResponse;
    	}
    	JsonObject jsonResponse = service.giveResponse("Already Registered!!!");
    	return jsonResponse; 
    }
    
    @PostMapping("/admin")
    public JsonObject adminApi(@RequestBody AdminDto adminDto) {
    	    	
    	String encrypted_pwd = EncryptionDecryptionAES.encrypt(adminDto.getPassword());
    	String encrypted_admin_password = EncryptionDecryptionAES.encrypt(adminDto.getAdminPassword());
    	
    	AdminCredentials adminCredentials = AdminCredentials.builder()
    			.password(encrypted_admin_password)
    			.username(adminDto.getAdminUsername())
    			.build();
    	boolean isAdmin = service.validateCredentials(adminCredentials);
    	if(isAdmin) {    		
    		boolean isClientExists = adminRepo.existsByClientId(adminDto.getClientId());
    		
    		if(!isClientExists) {    		
    			Admin admin = Admin.builder().email(adminDto.getEmail()).clientId(adminDto.getClientId()).url(adminDto.getUrl()).username(adminDto.getUsername())
    					.password(encrypted_pwd)
    					.build(); 
    			adminRepo.save(admin);
    			JsonObject jsonResponse = service.giveResponse("Success");
    			return jsonResponse;
    		}  		
    		JsonObject jsonResponse = service.giveResponse("Already Registered!!!");
        	return jsonResponse; 	
    	}
    	JsonObject jsonResponse = service.giveResponse("Please verify the Admin credentials!");
    	return jsonResponse;     	
    }
    
    
    @PostMapping("/login")
    public JsonObject login(@RequestBody AdminCredentials adminCredentials ){
    	
    	boolean isAdmin = service.validateCredentials(adminCredentials);  	
    	if(isAdmin) {    		
    		JsonObject jsonResponse = service.giveResponse("Success");
        	return jsonResponse;
    	}
    	JsonObject jsonResponse = service.giveResponse("Not Registered as Admin. Please check the credentials!!!");
    	return jsonResponse;
    }
    
    @PostMapping("/addAdmin")
    public JsonObject addConnection(@RequestBody AdminCredentials adminCredentials ) {
    	
    	boolean isNewCredentials = service.addCredentials(adminCredentials);
    	ObjectConversion objectConvertor  = new ObjectConversion();
    	if(isNewCredentials) {    		
    		JsonObject jsonResponse = service.giveResponse("Success");
        	return jsonResponse;
    	}
    	JsonObject jsonResponse = service.giveResponse("Already Admin!!");
    	return jsonResponse;
    }
    
}