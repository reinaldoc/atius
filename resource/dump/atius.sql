-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.51a-24+lenny5-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema atius
--

CREATE DATABASE IF NOT EXISTS atius;
USE atius;

--
-- Definition of table `atius`.`securityresource`
--

DROP TABLE IF EXISTS `atius`.`securityresource`;
CREATE TABLE  `atius`.`securityresource` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `atius`.`securityresource`
--

/*!40000 ALTER TABLE `securityresource` DISABLE KEYS */;
LOCK TABLES `securityresource` WRITE;
INSERT INTO `atius`.`securityresource` VALUES  (1,'public_url','/security/login.jsf',''),
 (2,'private_url','/index.jsf','Url da página inicial'),
 (3,'public_url_startswith','/javax.faces.resource/',''),
 (11,'private_url','/sites/report','URL para Acesso aos relatórios de Website'),
 (12,'button','SITES-DELETE','Controla a exibição do butão que deleta sites'),
 (13,'button','SITES-REPORT','xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx'),
 (14,'menu','ATIUS-DHCP-MODULE','Item \"DHCP\" do menu superior'),
 (15,'menu','ATIUS-SITES-MODULE','Item \"Sites\" do menu superior'),
 (16,'menu','ATIUS-EMAIL-MODULE','Item \"E-mail\" do menu superior'),
 (17,'menu','ATIUS-SECURITY-MODULE','Item \"Segurança\" do menu superior');
UNLOCK TABLES;
/*!40000 ALTER TABLE `securityresource` ENABLE KEYS */;


--
-- Definition of table `atius`.`securityrole`
--

DROP TABLE IF EXISTS `atius`.`securityrole`;
CREATE TABLE  `atius`.`securityrole` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(255) default NULL,
  `humanName` varchar(255) default NULL,
  `name` varchar(255) NOT NULL,
  `restriction` bit(1) NOT NULL,
  `shortDescription` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `atius`.`securityrole`
--

/*!40000 ALTER TABLE `securityrole` DISABLE KEYS */;
LOCK TABLES `securityrole` WRITE;
INSERT INTO `atius`.`securityrole` VALUES  (12,'Papél que agrupa os recursos para remoção de website',NULL,'ROLE-SITES-DELETE',0x00,'Papél que agrupa os recursos para remoção de website'),
 (13,'Papel que agrupa recursos para gerar relatórios asda sdasdasd ',NULL,'ROLE-SITES-REPORT',0x00,'Papel que agrupa recursos para gerar relatórios'),
 (15,'Acesso ao Módulo \"DHCP\"',NULL,'ROLE-DHCP-MODULE',0x00,'Acesso ao Módulo \"DHCP\"'),
 (16,'Acesso ao Módulo \"Sites\"',NULL,'ROLE-SITES-MODULE',0x00,'Acesso ao Módulo \"Sites\"'),
 (17,'Acesso ao Módulo \"E-mail\"',NULL,'ROLE-EMAIL-MODULE',0x00,'Acesso ao Módulo \"E-mail\"'),
 (18,'Acesso ao Módulo \"Segurança\"',NULL,'ROLE-SECURITY-MODULE',0x00,'Acesso ao Módulo \"Segurança\"');
UNLOCK TABLES;
/*!40000 ALTER TABLE `securityrole` ENABLE KEYS */;


--
-- Definition of table `atius`.`securityrole_resource`
--

DROP TABLE IF EXISTS `atius`.`securityrole_resource`;
CREATE TABLE  `atius`.`securityrole_resource` (
  `ROLE_ID` bigint(20) NOT NULL,
  `RESOURCE_ID` bigint(20) NOT NULL,
  KEY `FKB88AF277F0E5200B` (`ROLE_ID`),
  KEY `FKB88AF277E2B9E88B` (`RESOURCE_ID`),
  CONSTRAINT `FKB88AF277E2B9E88B` FOREIGN KEY (`RESOURCE_ID`) REFERENCES `securityresource` (`id`),
  CONSTRAINT `FKB88AF277F0E5200B` FOREIGN KEY (`ROLE_ID`) REFERENCES `securityrole` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `atius`.`securityrole_resource`
--

/*!40000 ALTER TABLE `securityrole_resource` DISABLE KEYS */;
LOCK TABLES `securityrole_resource` WRITE;
INSERT INTO `atius`.`securityrole_resource` VALUES  (12,12),
 (13,13),
 (13,11),
 (15,14),
 (15,2),
 (16,15),
 (16,2),
 (17,16),
 (17,2),
 (18,17),
 (18,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `securityrole_resource` ENABLE KEYS */;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
