package com.indrasol.supervue.supervuewebhook.dto;

import com.indrasol.supervue.supervuewebhook.model.Tenant.TenantBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class TenantDto {
	
	private String firstName;
	
	private String lastName;
	
	private String companyName;

	private String email;	
		
	private String contact;	

	private String country;
}
