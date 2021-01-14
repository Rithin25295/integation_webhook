--DROP TABLE IF EXISTS TENANT;
 --CONSTRAINT PK_Person PRIMARY KEY (email,username),

CREATE TABLE IF NOT EXISTS TENANT (
  tenant_id int NOT NULL  AUTO_INCREMENT,
  registration_id VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  company_name VARCHAR(250) NOT NULL,
  contact CHAR(10) DEFAULT NULL, 
  country VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS ADMIN (
  id int NOT NULL  AUTO_INCREMENT,
  email VARCHAR(250) NOT NULL,
  clientId VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL,
  url VARCHAR(250) NOT NULL,
  password VARCHAR(250) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
  admin_id int NOT NULL  AUTO_INCREMENT,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) DEFAULT NULL
);