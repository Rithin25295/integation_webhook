package com.indrasol.supervue.supervuewebhook.dto;

import com.indrasol.supervue.supervuewebhook.dto.TenantDto.TenantDtoBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
public class AdminDto {
	

	private String adminUsername;
	
	private String adminPassword;
	
	private String email;
	
	private String clientId;
	
	private String username;
	
	private String password;
	
	private String url;	
}
