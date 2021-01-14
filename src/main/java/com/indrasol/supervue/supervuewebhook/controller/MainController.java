package com.indrasol.supervue.supervuewebhook.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indrasol.supervue.supervuewebhook.model.AdminCredentials;
import com.indrasol.supervue.supervuewebhook.model.Tenant;
import com.indrasol.supervue.supervuewebhook.repositories.TenantRepo;

@Controller
public class MainController {
	
	@Autowired
	TenantRepo repo;

//	@RequestMapping("/")
//    public String index(){
//        return "login.screen";
//    }
	
	@RequestMapping("/")
    public String login(){
        return "connector.screen";
    }
    
	@RequestMapping("/thankyou")
    public String addTenant(Tenant tenant){
		
		return "thankyou.screen";  	
    }
	
	@RequestMapping("/admin")
    public String admin(Tenant tenant){
		
		return "admin.screen";  	
    }
	
	@RequestMapping("/adminlogin")
    public String adminLogin(AdminCredentials adminCredentials){
		
		return "login.screen";  	
    } 	
}
