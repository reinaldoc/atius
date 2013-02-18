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
-- Create schema ATIUS
--

CREATE DATABASE IF NOT EXISTS ATIUS;
USE ATIUS;

--
-- Definition of table `ATIUS`.`SECURITYRESOURCE`
--

DROP TABLE IF EXISTS `ATIUS`.`SECURITYRESOURCE`;
CREATE TABLE  `ATIUS`.`SECURITYRESOURCE` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ATIUS`.`SECURITYRESOURCE`
--

/*!40000 ALTER TABLE `SECURITYRESOURCE` DISABLE KEYS */;
LOCK TABLES `SECURITYRESOURCE` WRITE;
INSERT INTO `ATIUS`.`SECURITYRESOURCE` VALUES  (1,'public_url','/security/login.jsf',''),
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
/*!40000 ALTER TABLE `SECURITYRESOURCE` ENABLE KEYS */;


--
-- Definition of table `ATIUS`.`SECURITYROLE`
--

DROP TABLE IF EXISTS `ATIUS`.`SECURITYROLE`;
CREATE TABLE  `ATIUS`.`SECURITYROLE` (
  `id` bigint(20) NOT NULL auto_increment,
  `description` varchar(255) default NULL,
  `humanName` varchar(255) default NULL,
  `name` varchar(255) NOT NULL,
  `restriction` bit(1) NOT NULL,
  `shortDescription` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ATIUS`.`SECURITYROLE`
--

/*!40000 ALTER TABLE `SECURITYROLE` DISABLE KEYS */;
LOCK TABLES `SECURITYROLE` WRITE;
INSERT INTO `ATIUS`.`SECURITYROLE` VALUES  (12,'Papél que agrupa os recursos para remoção de website',NULL,'ROLE-SITES-DELETE',0x00,'Papél que agrupa os recursos para remoção de website'),
 (13,'Papel que agrupa recursos para gerar relatórios asda sdasdasd ',NULL,'ROLE-SITES-REPORT',0x00,'Papel que agrupa recursos para gerar relatórios'),
 (15,'Acesso ao Módulo \"DHCP\"',NULL,'ROLE-DHCP-MODULE',0x00,'Acesso ao Módulo \"DHCP\"'),
 (16,'Acesso ao Módulo \"Sites\"',NULL,'ROLE-SITES-MODULE',0x00,'Acesso ao Módulo \"Sites\"'),
 (17,'Acesso ao Módulo \"E-mail\"',NULL,'ROLE-EMAIL-MODULE',0x00,'Acesso ao Módulo \"E-mail\"'),
 (18,'Acesso ao Módulo \"Segurança\"',NULL,'ROLE-SECURITY-MODULE',0x00,'Acesso ao Módulo \"Segurança\"');
UNLOCK TABLES;
/*!40000 ALTER TABLE `SECURITYROLE` ENABLE KEYS */;


--
-- Definition of table `ATIUS`.`SECURITYROLE_RESOURCE`
--

DROP TABLE IF EXISTS `ATIUS`.`SECURITYROLE_RESOURCE`;
CREATE TABLE  `ATIUS`.`SECURITYROLE_RESOURCE` (
  `ROLE_ID` bigint(20) NOT NULL,
  `RESOURCE_ID` bigint(20) NOT NULL,
  KEY `FKB88AF277F0E5200B` (`ROLE_ID`),
  KEY `FKB88AF277E2B9E88B` (`RESOURCE_ID`),
  CONSTRAINT `FKB88AF277E2B9E88B` FOREIGN KEY (`RESOURCE_ID`) REFERENCES `SECURITYRESOURCE` (`id`),
  CONSTRAINT `FKB88AF277F0E5200B` FOREIGN KEY (`ROLE_ID`) REFERENCES `SECURITYROLE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ATIUS`.`SECURITYROLE_RESOURCE`
--

/*!40000 ALTER TABLE `SECURITYROLE_RESOURCE` DISABLE KEYS */;
LOCK TABLES `SECURITYROLE_RESOURCE` WRITE;
INSERT INTO `ATIUS`.`SECURITYROLE_RESOURCE` VALUES  (12,12),
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
/*!40000 ALTER TABLE `SECURITYROLE_RESOURCE` ENABLE KEYS */;


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
