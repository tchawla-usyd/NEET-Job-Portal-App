-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: jobsitedb
-- ------------------------------------------------------
-- Server version 8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema jobsitedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jobsitedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jobsitedb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `jobsitedb` ;

--
-- Table structure for table `candidateinfo`
--

DROP TABLE IF EXISTS `candidateinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidateinfo` (
  `Id` int(11) NOT NULL,
  `education` text,
  `experience` text,
  `resume` varchar(2083) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidateinfo`
--

LOCK TABLES `candidateinfo` WRITE;
/*!40000 ALTER TABLE `candidateinfo` DISABLE KEYS */;
INSERT INTO `candidateinfo` VALUES (4,NULL,NULL,NULL),(6,'123','123',NULL),(9,'2016-2019: Computer Science, USYD','2018: Pizza Engineer, Dominos','https://s3-ap-southeast-2.amazonaws.com/neet-bucket/1572102332116-lucifersresume.png'),(10,'1986-1990: Master Roshi\n\n1990-2000: Kami\n\n2000-2010: King Kai\n\n2018: Beerus','saves earth for fun\n\ndestroying stuff\n\nbecoming god','https://s3-ap-southeast-2.amazonaws.com/neet-bucket/1572105226643-goku.jpg');
/*!40000 ALTER TABLE `candidateinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidatejobapplied`
--

DROP TABLE IF EXISTS `candidatejobapplied`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidatejobapplied` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `JobID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `ApplyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_JobCandidate_UserID_idx` (`UserID`),
  KEY `FK_JobCandidate_JobID_idx` (`JobID`),
  CONSTRAINT `FK_JobCandidate_JobID` FOREIGN KEY (`JobID`) REFERENCES `job` (`Id`),
  CONSTRAINT `FK_JobCandidate_UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidatejobapplied`
--

LOCK TABLES `candidatejobapplied` WRITE;
/*!40000 ALTER TABLE `candidatejobapplied` DISABLE KEYS */;
INSERT INTO `candidatejobapplied` VALUES (1,13,5,'2019-10-26 00:00:00'),(2,13,4,'2019-10-26 00:00:00'),(3,13,4,'2019-10-26 00:00:00'),(4,13,4,'2019-10-26 00:00:00'),(5,14,9,'2019-10-26 00:00:00'),(6,15,9,'2019-10-26 00:00:00'),(7,16,9,'2019-10-26 00:00:00'),(8,13,10,'2019-10-26 00:00:00'),(9,14,10,'2019-10-26 00:00:00');
/*!40000 ALTER TABLE `candidatejobapplied` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidateskills`
--

DROP TABLE IF EXISTS `candidateskills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidateskills` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) DEFAULT NULL,
  `SkillID` int(11) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_CandidateSkills_User_Id_idx` (`UserID`),
  KEY `FK_CandidateSkills_SkillID_idx` (`SkillID`),
  CONSTRAINT `FK_CandidateSkills_SkillID` FOREIGN KEY (`SkillID`) REFERENCES `skillset` (`Id`),
  CONSTRAINT `FK_CandidateSkills_User_Id` FOREIGN KEY (`UserID`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='    ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidateskills`
--

LOCK TABLES `candidateskills` WRITE;
/*!40000 ALTER TABLE `candidateskills` DISABLE KEYS */;
INSERT INTO `candidateskills` VALUES (1,9,31,'2019-10-26 00:00:00'),(2,9,32,'2019-10-26 00:00:00'),(3,9,33,'2019-10-26 00:00:00'),(4,9,34,'2019-10-26 00:00:00'),(5,10,35,'2019-10-26 00:00:00'),(6,10,36,'2019-10-26 00:00:00'),(7,10,37,'2019-10-26 00:00:00'),(8,10,38,'2019-10-26 00:00:00'),(9,10,39,'2019-10-26 00:00:00'),(10,10,40,'2019-10-26 00:00:00'),(11,10,41,'2019-10-26 00:00:00'),(12,10,42,'2019-10-26 00:00:00');
/*!40000 ALTER TABLE `candidateskills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companyinfo`
--

DROP TABLE IF EXISTS `companyinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companyinfo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CompanyName` varchar(45) DEFAULT NULL,
  `BusinessPhone` varchar(45) DEFAULT NULL,
  `WebSite` varchar(45) DEFAULT NULL,
  `CompanyImage` varchar(45) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_UserID_Company_User_ID_idx` (`UserID`),
  CONSTRAINT `FK_CompanyInfo_UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='   ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companyinfo`
--

LOCK TABLES `companyinfo` WRITE;
/*!40000 ALTER TABLE `companyinfo` DISABLE KEYS */;
INSERT INTO `companyinfo` VALUES (1,'1234',NULL,NULL,NULL,5),(2,'Amazon',NULL,NULL,NULL,7),(3,'Facebook',NULL,NULL,NULL,8);
/*!40000 ALTER TABLE `companyinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) DEFAULT NULL,
  `JobDescription` text,
  `Location` varchar(256) DEFAULT NULL,
  `JobCategoryID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `IsActive` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_JobPost_User_ID_idx` (`UserID`),
  KEY `FK_JobPost_Category_Id_idx` (`JobCategoryID`),
  CONSTRAINT `FK_JobPost_Category_Id` FOREIGN KEY (`JobCategoryID`) REFERENCES `jobcategory` (`Id`),
  CONSTRAINT `FK_JobPost_User_ID` FOREIGN KEY (`UserID`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (13,'ww','ww','ww',1,5,'2019-10-25 00:00:00','2019-11-24 00:00:00',1),(14,'Frontend Developer','We are looking for a qualified Front-end developer to join our IT team. You will be responsible for building the âclient-sideâ of our web applications. You should be able to translate our company and customer needs into functional and appealing interactive applications.\n\nIf youâre interested in creating a user-friendly environment by writing code and moving forward in your career, then this job is for you. We expect you to be a tech-savvy professional, who is curious about new digital technologies and aspires to combine usability with visual design.\n\nUltimately, you should be able to create a functional and attractive digital environment for our company, ensuring great user experience.','Sydney',1,7,'2019-10-25 00:00:00','2019-11-29 00:00:00',0),(15,'Backend Developer','We are looking for an experienced Back-end developer to join our IT team. You will be responsible for the server side of our web applications.\n\nIf you have excellent programming skills and a passion for developing applications or improving existing ones, we would like to meet you. As a Back-end developer, youâll work closely with our engineers to ensure system consistency and improve user experience.\n\nUltimately, you should be able to develop and maintain functional and stable web applications to meet our companyâs needs.','Sydney',1,7,'2019-10-25 00:00:00','2019-11-29 00:00:00',0),(16,'Blockchain Developer','Do blockchain stuff','Los Angeles',1,8,'2019-10-31 00:00:00','2019-11-12 00:00:00',0),(17,'Data Scientist','Do something with the DATA','London',1,8,'2019-10-30 00:00:00','2019-11-23 00:00:00',0);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobcategory`
--

DROP TABLE IF EXISTS `jobcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobcategory` (
  `Id` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `CreatedDate` date DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='    ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobcategory`
--

LOCK TABLES `jobcategory` WRITE;
/*!40000 ALTER TABLE `jobcategory` DISABLE KEYS */;
INSERT INTO `jobcategory` VALUES (1,'Default','Default',1,'2019-10-26 00:00:00','2019-10-26');
/*!40000 ALTER TABLE `jobcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skill`
--

DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skill` (
  `UID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatedBy` int(11) DEFAULT NULL,
  `CreatedDate` date DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Id` bigint(20) NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

LOCK TABLES `skill` WRITE;
/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
/*!40000 ALTER TABLE `skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skillset`
--

DROP TABLE IF EXISTS `skillset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skillset` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skillset`
--

LOCK TABLES `skillset` WRITE;
/*!40000 ALTER TABLE `skillset` DISABLE KEYS */;
INSERT INTO `skillset` VALUES (15,'JAVA','2019-10-26 00:00:00',4),(16,'PYTHON','2019-10-26 00:00:00',5),(18,'REACT','2019-10-26 00:00:00',4),(19,'JAVASCRIPT','2019-10-26 00:00:00',7),(20,'HTML','2019-10-26 00:00:00',7),(21,'CSS','2019-10-26 00:00:00',7),(22,'REST API','2019-10-26 00:00:00',7),(23,'MYSQL','2019-10-26 00:00:00',7),(24,'MONGODB','2019-10-26 00:00:00',7),(25,'DATALOG','2019-10-26 00:00:00',8),(26,'C++','2019-10-26 00:00:00',8),(27,'C','2019-10-26 00:00:00',8),(28,'SQL','2019-10-26 00:00:00',8),(29,'BIG DATA','2019-10-26 00:00:00',8),(30,'MACHINE LEARNING','2019-10-26 00:00:00',8),(31,'JAPANESE','2019-10-26 00:00:00',9),(32,'JAVA','2019-10-26 00:00:00',9),(33,'CSS','2019-10-26 00:00:00',9),(34,'C++','2019-10-26 00:00:00',9),(35,'MARTIAL ARTS','2019-10-26 00:00:00',10),(36,'MUFFIN','2019-10-26 00:00:00',10),(37,'REACT','2019-10-26 00:00:00',10),(38,'JAVASCRIPT','2019-10-26 00:00:00',10),(39,'JAVA','2019-10-26 00:00:00',10),(40,'PYTHON','2019-10-26 00:00:00',10),(41,'HTML','2019-10-26 00:00:00',10),(42,'CSS','2019-10-26 00:00:00',10);
/*!40000 ALTER TABLE `skillset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skillsforjob`
--

DROP TABLE IF EXISTS `skillsforjob`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skillsforjob` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `SkillID` int(11) DEFAULT NULL,
  `JobID` int(11) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_SkillForJob_JobId_idx` (`JobID`),
  KEY `FK_SkillForJob_SkillId_idx` (`SkillID`),
  CONSTRAINT `FK_SkillForJob_JobId` FOREIGN KEY (`JobID`) REFERENCES `job` (`Id`),
  CONSTRAINT `FK_SkillForJob_SkillId` FOREIGN KEY (`SkillID`) REFERENCES `skillset` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skillsforjob`
--

LOCK TABLES `skillsforjob` WRITE;
/*!40000 ALTER TABLE `skillsforjob` DISABLE KEYS */;
INSERT INTO `skillsforjob` VALUES (13,15,13,'2019-10-26 00:00:00'),(14,16,13,'2019-10-26 00:00:00'),(15,18,13,'2019-10-26 00:00:00'),(16,19,14,'2019-10-26 00:00:00'),(17,20,14,'2019-10-26 00:00:00'),(18,21,14,'2019-10-26 00:00:00'),(19,18,14,'2019-10-26 00:00:00'),(20,22,14,'2019-10-26 00:00:00'),(21,15,15,'2019-10-26 00:00:00'),(22,16,15,'2019-10-26 00:00:00'),(23,23,15,'2019-10-26 00:00:00'),(24,24,15,'2019-10-26 00:00:00'),(25,25,16,'2019-10-26 00:00:00'),(26,26,16,'2019-10-26 00:00:00'),(27,15,16,'2019-10-26 00:00:00'),(28,27,16,'2019-10-26 00:00:00'),(29,23,17,'2019-10-26 00:00:00'),(30,28,17,'2019-10-26 00:00:00'),(31,29,17,'2019-10-26 00:00:00'),(32,30,17,'2019-10-26 00:00:00');
/*!40000 ALTER TABLE `skillsforjob` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `IsActive` tinyint(4) DEFAULT NULL,
  `IsLocked` tinyint(4) DEFAULT NULL,
  `CreatedDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  `UserTypeID` int(11) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`,`CreatedDate`,`ModifiedDate`),
  KEY `UID_idx` (`UserTypeID`),
  CONSTRAINT `User_UserTypeID_UserType_UID` FOREIGN KEY (`UserTypeID`) REFERENCES `usertype` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'a','a','a@a.com','$2a$10$likmdIoIHMfg12mSBiC1te7Y3GwNZusD3Ec81Dk9FQUmMIBNxpFO6',1,0,'2019-10-26 00:00:00','2019-10-26 00:00:00',4,'2019-10-26 00:00:00'),(5,'b','b','b@b.com','$2a$10$JMVMxExyMGYaGlJe83YjQOsAR6QOS3d/ZhR3XgTzwREbylYZgqElm',1,0,'2019-10-26 00:00:00','2019-10-26 00:00:00',3,'2019-10-26 00:00:00'),(6,'1','1','1@1.com','$2a$10$wh0Pq2zk0lC2cxms/qYZAuY/p3L3zU.ayD/Pa2qjAgxYnbLcgnD4u',1,0,'2019-10-26 00:00:00','2019-10-26 00:00:00',4,'2019-10-26 00:00:00'),(7,'David','Beckham','david@beckham.com','$2a$10$CIPxBpNF/tqX7bjcmW5hCu48JwG5hIhZ7DX/DDLFxOoUwwOkuX3M.',1,0,'2019-10-26 00:00:00','2019-10-26 00:00:00',3,'2019-10-26 00:00:00'),(8,'Mike','Tyson','iron@mike.com','$2a$10$PAlJgFBC6j0XwLoLPlDiIesliG/s2Y7WR0sBis1bIdzyUn1DH3wOi',1,0,'2019-10-26 00:00:00','2019-10-26 00:00:00',3,'2019-10-26 00:00:00'),(9,'Shinzo','Abe','me@japan.com','$2a$10$6JMdo76guz3h/ZYJzTyHDO/MEUcxxlc9aMBygz/y2KxAvmWFn7xzG',1,0,'2019-10-26 00:00:00','2019-10-26 00:00:00',4,'2019-10-26 00:00:00'),(10,'Son','Goku','son@goku.com','$2a$10$/BDfMIUu6qliHgxBnRAJkOXHJyMmnPFl1RhUOJAVGMaCEN9ZJ1fdy',1,0,'2019-10-26 00:00:00','2019-10-26 00:00:00',4,'2019-10-26 00:00:00');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userprofilestate`
--

DROP TABLE IF EXISTS `userprofilestate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userprofilestate` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `IsEmailSent` tinyint(4) DEFAULT NULL,
  `IsSmsSent` tinyint(4) DEFAULT NULL,
  `PasswordResetGuid` varchar(255) DEFAULT NULL,
  `PasswordResetExpiryGuid` datetime DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_UserProfile_UserID_idx` (`UserID`),
  CONSTRAINT `FK_UserProfile_UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='  ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userprofilestate`
--

LOCK TABLES `userprofilestate` WRITE;
/*!40000 ALTER TABLE `userprofilestate` DISABLE KEYS */;
/*!40000 ALTER TABLE `userprofilestate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userprofilestatus`
--

DROP TABLE IF EXISTS `userprofilestatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userprofilestatus` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatedDate` date DEFAULT NULL,
  `IsEmailSent` bit(1) NOT NULL,
  `IsSmsSent` bit(1) NOT NULL,
  `ModifiedDate` date DEFAULT NULL,
  `PasswordResetExpiryGuid` varchar(255) DEFAULT NULL,
  `PasswordResetGuid` varchar(255) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userprofilestatus`
--

LOCK TABLES `userprofilestatus` WRITE;
/*!40000 ALTER TABLE `userprofilestatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `userprofilestatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertype` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `Type` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` VALUES (1,'EnterpriseAdmin','Administrator Account','2019-10-05 00:00:00',0),(2,'Admin','Adminstrator of the Application','2019-10-05 00:00:00',0),(3,'Company','User Account for Company','2019-10-05 00:00:00',0),(4,'User','User Account for Job Seeker','2019-10-05 00:00:00',0);
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-27  2:55:26
