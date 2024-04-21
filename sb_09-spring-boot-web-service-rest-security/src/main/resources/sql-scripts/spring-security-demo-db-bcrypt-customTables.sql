USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `our_roles`;
DROP TABLE IF EXISTS `our_members`;

--
-- Table structure for table `our_members`
--

-- For bcrypt password the string should be of min 68 char long, 8+60
CREATE TABLE `our_members` (
  `user_name` varchar(50) NOT NULL,
  `pwd` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- https://bcrypt-generator.com/
-- suresh=user01, rakesh=sde01, mahesh=admin, mayank=leo
INSERT INTO `our_members`
VALUES 
('suresh','{bcrypt}$2a$12$7wC85zbfSp7cEwLXkMdF5.RZ9/7LGvgwmtAg44LX.dnqiZ7O0tUWS',1),
('rakesh','{bcrypt}$2a$12$hCS2mggoCaKXi8Om4B7UfeA4wLuRTfr74r7gRxlfZpXyIc4CvUerm',1),
('mahesh','{bcrypt}$2a$12$vaiXdLDsL.ddpZ5hLsGrGua570BTlUnpcc7Nr9m6rkLHVki2NUZgq',1),
('mayank','{bcrypt}$2a$12$fn9yA66At6I0SMNFxEPkHuXlWgiiNsn4r5a6bYslQVhpeYFVOTaAW',1);

SELECT * FROM employee_directory.our_members;

--
-- Table structure for table `our_roles`
--

CREATE TABLE `our_roles` (
  `user_name` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `roles_idx_1` (`user_name`,`role`),
  CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `our_members` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `our_roles`
--

INSERT INTO `our_roles`
VALUES 
('suresh','ROLE_CLIENT'),
('rakesh','ROLE_DEVELOPER'),
('mahesh','ROLE_ADMIN'),
('mayank','ROLE_DEVELOPER'),
('mayank','ROLE_MANAGER'),
('mayank','ROLE_ADMIN');

SELECT * FROM employee_directory.our_roles;

-- SHOWING ALL ENTRIES FOR THE 'employee' TABLE
SELECT * FROM employee_directory.employee;