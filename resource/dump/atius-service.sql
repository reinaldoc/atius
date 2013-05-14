-- MySQL dump 10.13  Distrib 5.1.66, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: ATIUS
-- ------------------------------------------------------
-- Server version	5.1.66-0+squeeze1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `SERVICEAREA`
--

DROP TABLE IF EXISTS `SERVICEAREA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SERVICEAREA` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SERVICEAREA`
--

LOCK TABLES `SERVICEAREA` WRITE;
/*!40000 ALTER TABLE `SERVICEAREA` DISABLE KEYS */;
INSERT INTO `SERVICEAREA` VALUES (1,'Microinformática'),(2,'Aplicativos'),(3,'Redes de Computadores'),(4,'Sistemas Corporativos');
/*!40000 ALTER TABLE `SERVICEAREA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SERVICEGROUP`
--

DROP TABLE IF EXISTS `SERVICEGROUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SERVICEGROUP` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `area_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA00500EA4A9E14B0` (`area_id`),
  CONSTRAINT `FKA00500EA4A9E14B0` FOREIGN KEY (`area_id`) REFERENCES `SERVICEAREA` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SERVICEGROUP`
--

LOCK TABLES `SERVICEGROUP` WRITE;
/*!40000 ALTER TABLE `SERVICEGROUP` DISABLE KEYS */;
INSERT INTO `SERVICEGROUP` VALUES (1,'Sistema Operacional',1),(2,'Hardware',1),(3,'Mozilla Firefox',2),(4,'Microsoft Office',2),(5,'Cabeamento',3),(6,'Correio Eletrônico',3),(7,'SADP - Sistema de Acompanhamento de Processos',4),(8,'ACOF - Atualização do Cadastro Off-line',4);
/*!40000 ALTER TABLE `SERVICEGROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SERVICEITEM`
--

DROP TABLE IF EXISTS `SERVICEITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SERVICEITEM` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD6C73288AF22604` (`group_id`),
  CONSTRAINT `FKD6C73288AF22604` FOREIGN KEY (`group_id`) REFERENCES `SERVICEGROUP` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SERVICEITEM`
--

LOCK TABLES `SERVICEITEM` WRITE;
/*!40000 ALTER TABLE `SERVICEITEM` DISABLE KEYS */;
/*!40000 ALTER TABLE `SERVICEITEM` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-13 22:15:00
