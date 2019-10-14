-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: jobsitedb
-- ------------------------------------------------------
-- Server version	8.0.17

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

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `UID` int(11) NOT NULL,
  `CompanyName` varchar(45) DEFAULT NULL,
  `BusinessPhone` varchar(45) DEFAULT NULL,
  `WebSite` varchar(45) DEFAULT NULL,
  `CompanyImage` varchar(45) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  PRIMARY KEY (`UID`),
  KEY `FK_UserID_Company_User_ID_idx` (`UserID`),
  CONSTRAINT `FK_UserID_Company_User_ID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) DEFAULT NULL,
  `JobDescription` varchar(45) DEFAULT NULL,
  `Location` varchar(45) DEFAULT NULL,
  `JobCategoryID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `CompanyID` int(11) DEFAULT NULL,
  `StartDate` datetime DEFAULT NULL,
  `EndDate` datetime DEFAULT NULL,
  `IsActive` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`UID`),
  KEY `FK_JobCategory_ID_idx` (`JobCategoryID`),
  KEY `FK_JobPost_User_ID_idx` (`UserID`),
  KEY `FK_JobPost_Company_ID_idx` (`CompanyID`),
  CONSTRAINT `FK_JobPost_Company_ID` FOREIGN KEY (`CompanyID`) REFERENCES `company` (`UID`),
  CONSTRAINT `FK_JobPost_JobCategory_ID` FOREIGN KEY (`JobCategoryID`) REFERENCES `jobcategory` (`UID`),
  CONSTRAINT `FK_JobPost_User_ID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jobcandidate`
--

DROP TABLE IF EXISTS `jobcandidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobcandidate` (
  `UID` int(11) NOT NULL,
  `JobID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `ApplyDate` datetime DEFAULT NULL,
  PRIMARY KEY (`UID`),
  KEY `FK_JobCandidate_UserID_idx` (`UserID`),
  KEY `FK_JobCandidate_JobID_idx` (`JobID`),
  CONSTRAINT `FK_JobCandidate_JobID` FOREIGN KEY (`JobID`) REFERENCES `job` (`UID`),
  CONSTRAINT `FK_JobCandidate_UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jobcategory`
--

DROP TABLE IF EXISTS `jobcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobcategory` (
  `UID` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Description` varchar(45) DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  `CreateDate` datetime DEFAULT NULL,
  `CreatedDate` tinyblob,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jobseekerskill`
--

DROP TABLE IF EXISTS `jobseekerskill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobseekerskill` (
  `UID` int(11) NOT NULL,
  `UserID` int(11) DEFAULT NULL,
  `SkillID` int(11) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `jobskill`
--

DROP TABLE IF EXISTS `jobskill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobskill` (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `SkillID` int(11) DEFAULT NULL,
  `JobID` int(11) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`UID`),
  UNIQUE KEY `UC_SkillJob` (`SkillID`,`JobID`),
  KEY `FK_JobPostSkill_Skill_ID_idx` (`SkillID`),
  KEY `FK_JobPostSkill_Job_ID_idx` (`JobID`) /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `skill`
--

DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skill` (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `CreatedBy` int(11) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `skillset`
--

DROP TABLE IF EXISTS `skillset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skillset` (
  `UID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatedBy` int(11) DEFAULT NULL,
  `CreatedDate` tinyblob,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UID` int(11) NOT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `IsActive` tinyint(4) DEFAULT NULL,
  `IsLocked` tinyint(4) DEFAULT NULL,
  `CreatedDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  `UserTypeID` int(11) DEFAULT NULL,
  `Id` bigint(20) NOT NULL,
  PRIMARY KEY (`UID`,`CreatedDate`,`ModifiedDate`),
  KEY `UID_idx` (`UserTypeID`),
  CONSTRAINT `User_UserTypeID_UserType_UID` FOREIGN KEY (`UserTypeID`) REFERENCES `usertype` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usercontact`
--

DROP TABLE IF EXISTS `usercontact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usercontact` (
  `UID` int(11) NOT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  `Mobile` varchar(45) DEFAULT NULL,
  `PostCode` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  PRIMARY KEY (`UID`),
  KEY `FK_UserID_UserContant_ID_idx` (`UserID`),
  CONSTRAINT `FK_UserID_UserContant_ID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `userstate`
--

DROP TABLE IF EXISTS `userstate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userstate` (
  `UID` int(11) NOT NULL,
  `IsEmailSent` tinyint(4) DEFAULT NULL,
  `IsSmsSent` tinyint(4) DEFAULT NULL,
  `PasswordResetGuid` varchar(255) DEFAULT NULL,
  `PasswordResetExpiryGuid` datetime DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  PRIMARY KEY (`UID`),
  KEY `FK_UserProfile_UserID_idx` (`UserID`),
  CONSTRAINT `FK_UserProfile_UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertype` (
  `UID` int(11) NOT NULL,
  `Type` int(11) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `UserTypecol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-13 21:31:06
