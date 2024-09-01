USE sql12728899;

SELECT @@sql_mode;
SET SESSION sql_mode = '';

CREATE TABLE IF NOT EXISTS contact_msg (
  contact_id int AUTO_INCREMENT  PRIMARY KEY,
  name varchar(100) NOT NULL,
  mobile_num varchar(10) NOT NULL,
  email varchar(100) NOT NULL,
  subject varchar(100) NOT NULL,
  message varchar(500) NOT NULL,
  status varchar(10) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by varchar(50) NOT NULL,
  updated_at TIMESTAMP,
  updated_by varchar(50) DEFAULT NULL
);

ALTER TABLE sql12728899.contact_msg MODIFY COLUMN updated_at timestamp NULL;

--

CREATE TABLE IF NOT EXISTS holidays (
  holiday_id int AUTO_INCREMENT  PRIMARY KEY,
  name varchar(20) NOT NULL,
  date date NOT NULL,
  type varchar(20) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  created_by varchar(50) NOT NULL,
  updated_at TIMESTAMP,
  updated_by varchar(50) DEFAULT NULL
);

ALTER TABLE sql12728899.holidays MODIFY COLUMN updated_at timestamp NULL;