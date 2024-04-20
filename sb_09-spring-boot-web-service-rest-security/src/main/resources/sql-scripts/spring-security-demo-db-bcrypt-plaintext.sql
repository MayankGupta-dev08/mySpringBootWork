USE `employee_directory`;

DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

-- For bcrypt password the string should be of min 68 char long, 8+60
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--
-- https://bcrypt-generator.com/
INSERT INTO `users` 
VALUES 
('suresh','{bcrypt}$2a$12$7wC85zbfSp7cEwLXkMdF5.RZ9/7LGvgwmtAg44LX.dnqiZ7O0tUWS',1),
('rakesh','{bcrypt}$2a$12$hCS2mggoCaKXi8Om4B7UfeA4wLuRTfr74r7gRxlfZpXyIc4CvUerm',1),
('mahesh','{bcrypt}$2a$12$vaiXdLDsL.ddpZ5hLsGrGua570BTlUnpcc7Nr9m6rkLHVki2NUZgq',1),
('mayank','{bcrypt}$2a$12$fn9yA66At6I0SMNFxEPkHuXlWgiiNsn4r5a6bYslQVhpeYFVOTaAW',1);

--INSERT INTO `users`
--VALUES
--('suresh','{noop}user01',1),
--('rakesh','{noop}sde01',1),
--('mahesh','{noop}admin',1),
--('mayank','{noop}leo',1);

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('suresh','ROLE_CLIENT'),
('rakesh','ROLE_DEVELOPER'),
('mahesh','ROLE_ADMIN'),
('mayank','ROLE_DEVELOPER'),
('mayank','ROLE_MANAGER'),
('mayank','ROLE_ADMIN');