--
-- Database `employee_directory` for table `employee`
--
CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;


--
-- Table structure for table `employee`
--
DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Data for table `employee`
--
INSERT INTO `employee` VALUES 
	(1,'Haris','Khan','codewithharry@yt.com'),
	(2,'Aman','Dhatarwal','apnikaksha@yt.com'),
	(3,'Sumeet','Mallik','pepcoding@yt.com'),
	(4,'Luv','Babbar','luvbabbar@yt.com'),
	(5,'Prashant','Dhawan','worldaffairs@yt.com');