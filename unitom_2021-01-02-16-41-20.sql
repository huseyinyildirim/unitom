# ************************************************************
# Sequel Ace SQL dump
# Version 3008
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.5.8-MariaDB)
# Database: unitom
# Generation Time: 2021-01-02 13:41:20 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table tbl_departments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_departments`;

CREATE TABLE `tbl_departments` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `tbl_departments` WRITE;
/*!40000 ALTER TABLE `tbl_departments` DISABLE KEYS */;

INSERT INTO `tbl_departments` (`id`, `name`)
VALUES
	(1,'Bilgisayar Mühendisliği'),
	(2,'Fizik Mühendisliği'),
	(3,'Kimya Mühendisliği');

/*!40000 ALTER TABLE `tbl_departments` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_staffs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_staffs`;

CREATE TABLE `tbl_staffs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `department_id` int(11) unsigned NOT NULL,
  `identity_no` varchar(11) NOT NULL DEFAULT '',
  `name` varchar(50) NOT NULL DEFAULT '',
  `surname` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `fk_staffs_department_id` FOREIGN KEY (`department_id`) REFERENCES `tbl_departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `tbl_staffs` WRITE;
/*!40000 ALTER TABLE `tbl_staffs` DISABLE KEYS */;

INSERT INTO `tbl_staffs` (`id`, `department_id`, `identity_no`, `name`, `surname`)
VALUES
	(1,1,'62839042712','Salih','Yıldırım');

/*!40000 ALTER TABLE `tbl_staffs` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tbl_students
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tbl_students`;

CREATE TABLE `tbl_students` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `department_id` int(11) unsigned NOT NULL,
  `student_no` varchar(20) NOT NULL DEFAULT '',
  `identity_no` varchar(11) NOT NULL DEFAULT '',
  `name` varchar(50) NOT NULL DEFAULT '',
  `surname` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `departments_id` (`department_id`),
  CONSTRAINT `fk_students_department_id` FOREIGN KEY (`department_id`) REFERENCES `tbl_departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `tbl_students` WRITE;
/*!40000 ALTER TABLE `tbl_students` DISABLE KEYS */;

INSERT INTO `tbl_students` (`id`, `department_id`, `student_no`, `identity_no`, `name`, `surname`)
VALUES
	(1,1,'9001','62809043732','Hüseyin','Yıldırım'),
	(2,1,'9002','63213498762','Elif Duru','Yıldırım');

/*!40000 ALTER TABLE `tbl_students` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
