--
-- Using the `employee_directory` db
--
USE `employee_directory`;


--
-- Dropping the tables if they already exists and making the check for the foreign key
--
SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `our_roles`;
DROP TABLE IF EXISTS `our_members`;
DROP TABLE IF EXISTS `members_roles`;
SET foreign_key_checks = 1;


--
-- Table structure for table `our_members`
--
CREATE TABLE `our_members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `pwd` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Inserting data for table `users`, For passwords: https://bcrypt-generator.com/
--
-- suresh=user01, rakesh=sde01, mahesh=admin
INSERT INTO `our_members`
VALUES 
('suresh','{bcrypt}$2a$12$7wC85zbfSp7cEwLXkMdF5.RZ9/7LGvgwmtAg44LX.dnqiZ7O0tUWS',1),
('rakesh','{bcrypt}$2a$12$hCS2mggoCaKXi8Om4B7UfeA4wLuRTfr74r7gRxlfZpXyIc4CvUerm',1),
('mahesh','{bcrypt}$2a$12$vaiXdLDsL.ddpZ5hLsGrGua570BTlUnpcc7Nr9m6rkLHVki2NUZgq',1);


--
-- Displaying table `our_members`
--
SELECT * FROM employee_directory.our_members;


--
-- Table structure for table `our_roles`
--
CREATE TABLE `our_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Inserting data for table `our_roles`
--
INSERT INTO `our_roles`
VALUES
('ROLE_EMPLOYEE'),
('ROLE_MANAGER'),
('ROLE_ADMIN');


--
-- Displaying table `our_roles`
--
SELECT * FROM employee_directory.our_roles;


--
-- Table structure for table `users_roles`
--
CREATE TABLE `members_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,

  PRIMARY KEY (`user_id`,`role_id`),

  KEY `FK_ROLE_idx` (`role_id`),

  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
  REFERENCES `role` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Dumping data for table `users_roles`
--
INSERT INTO `members_roles` (user_id,role_id)
VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3);