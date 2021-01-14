package com.indrasol.supervue.supervuewebhook.service;


import com.google.gson.Gson;


import com.google.gson.JsonObject;
import com.indrasol.supervue.supervuewebhook.dto.TenantDto;
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
import com.indrasol.supervue.supervuewebhook.util.EncryptionDecryptionAES;
import com.indrasol.supervue.supervuewebhook.util.ObjectConversion;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SupervueWebhookService {
	
	@Autowired
	TenantRepo tenantRepo;
	
	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private CredentialsRepo credRepo;
	
	@Autowired
    private JavaMailSender javaMailSender;

    public Event eventObject(JsonObject eventData){
        Gson gson = new Gson();
        JsonObject jsonResponse = (JsonObject) gson.toJsonTree(eventData);
        JsonObject object = jsonResponse.getAsJsonObject("data").getAsJsonObject("object");
        JsonObject eventDetailsObject = object.getAsJsonObject("event_details");
        JsonObject eventAnalysisObject = object.getAsJsonObject("event_analysis");

        Event event = Event.builder()
                .eventCategory(eventDetailsObject.get("event_category").getAsString())
                .camId(eventDetailsObject.get("cam_id").getAsString())
                .totalPersons(eventAnalysisObject.get("no_persons").getAsString())
                .eventTimestamp(eventDetailsObject.get("event_timestamp").getAsString())
                .lowRiskPersons(eventAnalysisObject.get("peopleAtLowRisk").getAsString())
                .mediumRiskPersons(eventAnalysisObject.get("peopleAtMediumRisk").getAsString())
                .highRiskPersons(eventAnalysisObject.get("peopleAtHighRisk").getAsString())
                .build();
        return event;

    }

    public String summary(Event event){
        ZonedDateTime zonedDateTime = ZonedDateTime
                .parse(event.getEventTimestamp());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a");
        String eventTime = zonedDateTime.format(formatter);
//        System.out.println(eventTime);
        String date = eventTime.substring(0,12);
        String time = eventTime.substring(13,eventTime.length());
        return "Social Distance Violation occurred from cam "+ event.getCamId()
                +" on "+date+" at "+time+"."+
                "\nThere are "+ event.getTotalPersons() +" people in total from which "+event.getMediumRiskPersons() +
                " people are at Medium Risk and "+ event.getHighRiskPersons()+" people are at High risk."
                +"Related images - https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg";
    }



    public IncidentBodyPOJO requestBody(JsonObject eventData) throws ParseException {

        Event event = eventObject(eventData);
        String summary = summary(event);
        System.out.println(summary);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");
        SimpleDateFormat zzz = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date d = null;
        try {
            d = dateformat.parse(event.getEventTimestamp());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String istamp = sdf.format(new Date());
        String tstamp= zzz.format(new Date());

        System.out.println(tstamp);
        EventCode eventCode = EventCode.builder().IncidentEventCode("ORA_VEHICLE_INCIDENT").IncidentEventSummary("Summary").build();
        Link link = Link.builder()
        		.href("https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-260nw-1048185397.jpg")
        		.rel("image")
        		.build();
        List<EventCode> eventsList = new ArrayList<>();
        eventsList.add(eventCode);
        eventsList.add(EventCode.builder().IncidentEventCode("ORA_VEHICLE_INCIDENT").IncidentEventSummary("Summary").build());
        List<Link> links = new ArrayList<>();
        links.add(link);
        IncidentBodyPOJO requestBody = IncidentBodyPOJO.builder()
                .IncidentDate(istamp)
                .IncidentSummary(summary)
                //.IncidentDescription("httpsg")
                .LocationTypeCode("ORA_ON_SITE")
                .ReptrSpecificLocation("Specific Location")
                .ReporterTypeCode("ORA_EMPLOYEE")
                .EmployeeId("300000049341361")
                .LocationId("300000047013170")
                .IncidentTypeCode("ORA_HEALTH_AND_SAFETY_INC")
                .SeverityLevelCode("ORA_NO_EFFECT")
                .NotifiedPersonId(null)
                .IncidentReportedDate(tstamp)
                .IncidentSourceCode("ORA_INTERNAL")
//                .links(links)
                .IncidentDetailKiosk(eventsList).build();
        return requestBody;
    }
    
//    public boolean checkAndSendEmail(String registrationEmail) throws IOException, URISyntaxException {
//    	
//    	//Query DB and check
//    	//Send Email
//    }
    
    
    public void sendEmail(TenantDto tenantDto) throws MessagingException {
    	MimeMessage msg = javaMailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(msg, true);
    	String email = tenantDto.getEmail();
    	String contact = tenantDto.getContact();
    	String country = tenantDto.getCountry();
    	String company = tenantDto.getCompanyName();
    	helper.setFrom("rithingullapalli@gmail.com");
    	helper.setTo("rithin25295@gmail.com");
        helper.setSubject("New Subscription");
        if(!StringUtils.isEmpty(contact)) {
        	if(country.equalsIgnoreCase("usa") || 
        			country.equalsIgnoreCase("united states of america") || 
        			country.equalsIgnoreCase("america") ||
        			country.equalsIgnoreCase("united states")) {
        		contact = "+1 "+contact;
        		helper.setText("<p>We just got a new Subscription to our HCM Incident Management Application from </p>"
        				+"<h4>"+company+"</h4>"+"<p>Please reach out to them through</p>"+"<h4>"
        				+email+"</h4>"+"<p>or</p>"+"<h4>"+contact+"</h4>", true);      	
        	}
        	else if(country.equalsIgnoreCase("india")) {
        		contact = "+91 "+contact;
        		helper.setText("<p>We just got a new Subscription to our HCM Incident Management Application from </p>"
        				+"<h4>"+company+"</h4>"+"<p>Please reach out to them through</p>"+"<h4>"
        				+email+"</h4>"+"<p>or</p>"+"<h4>"+contact+"</h4>", true);      	
        	}
        	helper.setText("<p>We just got a new Subscription to our HCM Incident Management Application from </p>"
    				+"<h4>"+company+"</h4>"+"<p>Please reach out to them through</p>"+"<h4>"
    				+email+"</h4>"+"<p>or</p>"+"<h4>"+contact+"</h4>", true);
        }
        else {        	
        	helper.setText("<p>We just got a new Subscription to our HCM Incident Management Application from </p>"
        			+"<h4>"+company+"</h4>"+"<p>Please reach out to them through</p>"+"<h4>"+email+"</h4>", true);        	
        }
//        if(StringUtils.isEmpty(contact)) {        		
//        		helper.setText("<p>We just got a new Subscription to our HCM Incident Management Application from </p>"
//            		+"<h4>"+company+"</h4>"+"<p>Please reach out to them through</p>"+"<h4>"+email+"</h4>", true);        	
//        }
//        if(!StringUtils.isEmpty(country) && !StringUtils.isEmpty(contact)) {        	
//        	if(country.equalsIgnoreCase("usa") || 
//        			country.equalsIgnoreCase("united states of america") || 
//        			country.equalsIgnoreCase("america") ||
//        			country.equalsIgnoreCase("united states")) {
//        		helper.setText("<p>We just got a new Subscription to our HCM Incident Management Application from </p>"
//        				+"<h4>"+company+"</h4>"+"<p>Please reach out to them through</p>"+"<h4>"
//        				+email+"</h4>"+"<p>or</p>"+"<h4>+1 "+contact+"</h4>", true);      	
//        	}
//        	else if(country.equalsIgnoreCase("india")) {
//        		helper.setText("<p>We just got a new Subscription to our HCM Incident Management Application from </p>"
//        				+"<h4>"+company+"</h4>"+"<p>Please reach out to them through</p>"+"<h4>"
//        				+email+"</h4>"+"<p>or</p>"+"<h4>+91 "+contact+"</h4>", true);      	
//        	}
//        }
         javaMailSender.send(msg);
    }
    
    public void sendEmailWithAttachment() throws MessagingException, IOException{
    	
    	MimeMessage msg = javaMailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        
        helper.setFrom("rithingullapalli@gmail.com");
    	helper.setTo("rithin25295@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check the incident attachment!</h1>", true);

        // hard coded a file path
//        FileSystemResource file = new FileSystemResource(new File("/images/thankyou_page.JPG"));
        
        helper.addAttachment("my_photo.png", new ClassPathResource("/images/thankyou_page.JPG"));

//        helper.addAttachment("my_photo.png", file);

        javaMailSender.send(msg);
    	
    }
    

	public boolean validateCredentials(AdminCredentials adminCredentials) {
		boolean isExists = credRepo.existsByUsername(adminCredentials.getUsername());
		if(isExists) {			
			AdminCredentials cred = credRepo.findByUsername(adminCredentials.getUsername());
			String pwd = EncryptionDecryptionAES.decrypt(adminCredentials.getPassword());
			if(pwd.equals(adminCredentials.getPassword())){
				return true;
			}
		}
		return false;
	}
	
	public boolean addCredentials(AdminCredentials adminCredentials) {
		boolean isExists = credRepo.existsByUsername(adminCredentials.getUsername());
		if(!isExists) {
			String pwd = EncryptionDecryptionAES.encrypt(adminCredentials.getPassword());
			AdminCredentials newCred = AdminCredentials.builder()
					.password(pwd)
					.username(adminCredentials.getUsername())
					.build();
			credRepo.save(newCred);
			return true;
		}
		return false;
	}
	
	public JsonObject giveResponse(String message) {
		ObjectConversion objectConvertor = new ObjectConversion();
		DummyObject dummy = DummyObject.builder().msg(message).build();
		JsonObject jsonResponse = objectConvertor.objecToJson(dummy);
		return jsonResponse; 
	}
   
    
//    public boolean isTenant(String tenantName, String url) {
//    	
//    	return tenantRepo.existsByTenantNameAndUrl(tenantName, url);
//
//    }

}
