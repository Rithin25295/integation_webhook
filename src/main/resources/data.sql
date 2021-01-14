--insert into TENANT(email, tenant_name,username,url,contact) values('sa@gmail.com','ds','asf','asf','asd') ;


insert into TENANT(registration_id,email, first_name,last_name,company_name,contact,country) select 'sa@gmail.com-ds-asf' as registration_id,'sa@gmail.com' as email,'ds' as first_name,'asf' as last_name,'asf' as company_name,'asd' as contact, 'usa' as country where not exists (select 1 from TENANT where first_name = 'ds' and email='sa@gmail.com')

insert into ADMIN(email, clientId,username,url,password) select 'sa1@gmail.com' as email,'19' as clientId,'asf' as username,'asf' as url,'asd' as password where not exists (select 1 from ADMIN where username = 'asf' and email='sa1@gmail.com') 

insert into CREDENTIALS(username,password) select 'xyz' as username,'uvxyz' as password where not exists (select 1 from CREDENTIALS where username = 'xyz') 