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

-- -----------------------------------------------------
-- Table `jobsitedb`.`usertype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobsitedb`.`usertype` (
  `UID` INT(11) NOT NULL,
  `Type` INT(11) NULL DEFAULT NULL,
  `Description` VARCHAR(255) NULL DEFAULT NULL,
  `CreatedDate` DATETIME NULL DEFAULT NULL,
  `UserTypecol` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`UID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jobsitedb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobsitedb`.`user` (
  `UID` INT(11) NOT NULL,
  `FirstName` VARCHAR(45) NULL DEFAULT NULL,
  `LastName` VARCHAR(45) NULL DEFAULT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `Password` VARCHAR(45) NULL DEFAULT NULL,
  `IsActive` TINYINT(4) NULL DEFAULT NULL,
  `IsLocked` TINYINT(4) NULL DEFAULT NULL,
  `CreatedDate` DATETIME NOT NULL,
  `ModifiedDate` DATETIME NOT NULL,
  `UserTypeID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`UID`, `CreatedDate`, `ModifiedDate`),
  INDEX `UID_idx` (`UserTypeID` ASC) VISIBLE,
  CONSTRAINT `User_UserTypeID_UserType_UID`
    FOREIGN KEY (`UserTypeID`)
    REFERENCES `jobsitedb`.`usertype` (`UID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jobsitedb`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobsitedb`.`company` (
  `UID` INT(11) NOT NULL,
  `CompanyName` VARCHAR(45) NULL DEFAULT NULL,
  `BusinessPhone` VARCHAR(45) NULL DEFAULT NULL,
  `WebSite` VARCHAR(45) NULL DEFAULT NULL,
  `CompanyImage` VARCHAR(45) NULL DEFAULT NULL,
  `UserID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`UID`),
  INDEX `FK_UserID_Company_User_ID_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `FK_UserID_Company_User_ID`
    FOREIGN KEY (`UserID`)
    REFERENCES `jobsitedb`.`user` (`UID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '		';


-- -----------------------------------------------------
-- Table `jobsitedb`.`jobcategory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobsitedb`.`jobcategory` (
  `UID` INT(11) NOT NULL,
  `Name` VARCHAR(45) NULL DEFAULT NULL,
  `Description` VARCHAR(45) NULL DEFAULT NULL,
  `CreatedBy` INT(11) NULL DEFAULT NULL,
  `CreateDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`UID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '		';


-- -----------------------------------------------------
-- Table `jobsitedb`.`job`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobsitedb`.`job` (
  `UID` INT(11) NOT NULL,
  `Title` VARCHAR(45) NULL DEFAULT NULL,
  `JobDescription` VARCHAR(45) NULL DEFAULT NULL,
  `Location` VARCHAR(45) NULL DEFAULT NULL,
  `JobCategoryID` INT(11) NULL DEFAULT NULL,
  `UserID` INT(11) NULL DEFAULT NULL,
  `CompanyID` INT(11) NULL DEFAULT NULL,
  `StartDate` DATETIME NULL DEFAULT NULL,
  `EndDate` DATETIME NULL DEFAULT NULL,
  `IsActive` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`UID`),
  INDEX `FK_JobCategory_ID_idx` (`JobCategoryID` ASC) VISIBLE,
  INDEX `FK_JobPost_User_ID_idx` (`UserID` ASC) VISIBLE,
  INDEX `FK_JobPost_Company_ID_idx` (`CompanyID` ASC) VISIBLE,
  CONSTRAINT `FK_JobPost_Company_ID`
    FOREIGN KEY (`CompanyID`)
    REFERENCES `jobsitedb`.`company` (`UID`),
  CONSTRAINT `FK_JobPost_JobCategory_ID`
    FOREIGN KEY (`JobCategoryID`)
    REFERENCES `jobsitedb`.`jobcategory` (`UID`),
  CONSTRAINT `FK_JobPost_User_ID`
    FOREIGN KEY (`UserID`)
    REFERENCES `jobsitedb`.`user` (`UID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jobsitedb`.`jobcandidate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobsitedb`.`jobcandidate` (
  `UID` INT(11) NOT NULL,
  `JobID` INT(11) NULL DEFAULT NULL,
  `UserID` INT(11) NULL DEFAULT NULL,
  `ApplyDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`UID`),
  INDEX `FK_JobCandidate_UserID_idx` (`UserID` ASC) VISIBLE,
  INDEX `FK_JobCandidate_JobID_idx` (`JobID` ASC) VISIBLE,
  CONSTRAINT `FK_JobCandidate_JobID`
    FOREIGN KEY (`JobID`)
    REFERENCES `jobsitedb`.`job` (`UID`),
  CONSTRAINT `FK_JobCandidate_UserID`
    FOREIGN KEY (`UserID`)
    REFERENCES `jobsitedb`.`user` (`UID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jobsitedb`.`jobseekerskill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobsitedb`.`jobseekerskill` (
  `UID` INT(11) NOT NULL,
  `UserID` INT(11) NULL DEFAULT NULL,
  `SkillID` INT(11) NULL DEFAULT NULL,
  `CreatedDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`UID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '		';


-- -----------------------------------------------------
-- Table `jobsitedb`.`skill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobsitedb`.`skill` (
  `UID` INT(11) NOT NULL,
  `Name` VARCHAR(45) NULL DEFAULT NULL,
  `Description` VARCHAR(255) NULL DEFAULT NULL,
  `CreatedDate` DATETIME NULL DEFAULT NULL,
  `CreatedBy` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`UID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jobsitedb`.`jobskill`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobsitedb`.`jobskill` (
  `UID` INT(11) NOT NULL,
  `SkillID` INT(11) NULL DEFAULT NULL,
  `JobID` INT(11) NULL DEFAULT NULL,
  `CreatedDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`UID`),
  INDEX `FK_JobPostSkill_Skill_ID_idx` (`SkillID` ASC) VISIBLE,
  INDEX `FK_JobPostSkill_Job_ID_idx` (`JobID` ASC) INVISIBLE,
  CONSTRAINT `FK_JobPostSkill_Job_ID`
    FOREIGN KEY (`JobID`)
    REFERENCES `jobsitedb`.`job` (`UID`),
  CONSTRAINT `FK_JobPostSkill_Skill_ID`
    FOREIGN KEY (`SkillID`)
    REFERENCES `jobsitedb`.`skill` (`UID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jobsitedb`.`usercontact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobsitedb`.`usercontact` (
  `UID` INT(11) NOT NULL,
  `Address` VARCHAR(45) NULL DEFAULT NULL,
  `Phone` VARCHAR(45) NULL DEFAULT NULL,
  `Mobile` VARCHAR(45) NULL DEFAULT NULL,
  `PostCode` INT(11) NULL DEFAULT NULL,
  `UserID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`UID`),
  INDEX `FK_UserID_UserContant_ID_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `FK_UserID_UserContant_ID`
    FOREIGN KEY (`UserID`)
    REFERENCES `jobsitedb`.`user` (`UID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jobsitedb`.`userstate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jobsitedb`.`userstate` (
  `UID` INT(11) NOT NULL,
  `IsEmailSent` TINYINT(4) NULL DEFAULT NULL,
  `IsSmsSent` TINYINT(4) NULL DEFAULT NULL,
  `PasswordResetGuid` VARCHAR(255) NULL DEFAULT NULL,
  `PasswordResetExpiryGuid` DATETIME NULL DEFAULT NULL,
  `CreatedDate` DATETIME NULL DEFAULT NULL,
  `ModifiedDate` DATETIME NULL DEFAULT NULL,
  `UserID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`UID`),
  INDEX `FK_UserProfile_UserID_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `FK_UserProfile_UserID`
    FOREIGN KEY (`UserID`)
    REFERENCES `jobsitedb`.`user` (`UID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
COMMENT = '	';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
