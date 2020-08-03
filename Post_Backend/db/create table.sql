create database if not exists plog;

use plog;

CREATE TABLE `member` (
  `memId` int NOT NULL AUTO_INCREMENT,
  `memUserid` varchar(30) NOT NULL,
  `memEmail` varchar(30) NOT NULL,
  `memPassword` varchar(30) NOT NULL,
  `memUsername` varchar(30) NOT NULL,
  `memPhone` varchar(30) NOT NULL,
  `memBirthday` varchar(30) NOT NULL,
  `memRegisterDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `memNickname` varchar(30) NOT NULL,
  `memIntro` varchar(150) NOT NULL,
  PRIMARY KEY (`memId`)
);

CREATE TABLE `schedule` (
  `sId` int NOT NULL AUTO_INCREMENT,
  `sName` varchar(50) NOT NULL,
  `sContent` varchar(2000) NOT NULL,
  `sStartdate` timestamp NOT NULL,
  `sEnddate` timestamp NOT NULL,
  `sMember` int, 
  PRIMARY KEY (`sId`),
  CONSTRAINT `schedule_to_member_fk` FOREIGN KEY (`sMember`) REFERENCES `member` (`memId`)
); 

CREATE TABLE `category` (
  `cId` int NOT NULL AUTO_INCREMENT,
  `cName` varchar(50) NOT NULL,
  `cMember` int, 
  PRIMARY KEY (`cId`),
  CONSTRAINT `category_to_member_fk` FOREIGN KEY (`cMember`) REFERENCES `member` (`memId`)
); 

CREATE TABLE `post` (
  `pId` int NOT NULL AUTO_INCREMENT,
  `pTitle` varchar(50) NOT NULL,
  `pContent` varchar(16000) NOT NULL,
  `pDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `pMember` int NOT NULL,
  `pSchedule` int NOT NULL,
  `pCategory` int NOT NULL,
  PRIMARY KEY (`pId`),
  CONSTRAINT `post_to_member_fk` FOREIGN KEY (`pMember`) REFERENCES `member` (`memId`),
  CONSTRAINT `post_to_schedule_fk` FOREIGN KEY (`pSchedule`) REFERENCES `schedule` (`sId`),
  CONSTRAINT `post_to_category_fk` FOREIGN KEY (`pCategory`) REFERENCES `category` (`cId`)
); 

CREATE TABLE `tmppost` (
  `tpId` int NOT NULL AUTO_INCREMENT,
  `tpTitle` varchar(50) NOT NULL,
  `tpContent` varchar(16000) NOT NULL,
  `tpDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tpMember` int NOT NULL,
  PRIMARY KEY (`tpId`),
  CONSTRAINT `tmppost_to_member_fk` FOREIGN KEY (`tpMember`) REFERENCES `member` (`memId`)
);



CREATE TABLE `hashtag` (
  `hId` int NOT NULL AUTO_INCREMENT,
  `hName` varchar(50) NOT NULL,
  PRIMARY KEY (`hId`)
); 

CREATE TABLE `postHashtag` (
  `phId` int NOT NULL AUTO_INCREMENT,
  `phPost` int NOT NULL,
  `phHashtag` int NOT NULL,
  PRIMARY KEY (`phId`),
  CONSTRAINT `post_to_ph_fk` FOREIGN KEY (`phPost`) REFERENCES `post` (`pId`),
  CONSTRAINT `hashtag_to_ph_fk` FOREIGN KEY (`phHashtag`) REFERENCES `hashtag` (`hId`)
);



CREATE TABLE `image` (
  `iId` int NOT NULL AUTO_INCREMENT,
  `iAddress` varchar(300) NOT NULL,
  `iPost` int NOT NULL,
  PRIMARY KEY (`iId`),
  CONSTRAINT `post_to_image_fk` FOREIGN KEY (`iPost`) REFERENCES `post` (`pId`)
);

CREATE TABLE `favorite` (
  `fId` int NOT NULL AUTO_INCREMENT,
  `fMember` int NOT NULL,
  `fPost` int NOT NULL,
  PRIMARY KEY (`fId`),
  CONSTRAINT `favorite_to_member_fk` FOREIGN KEY (`fMember`) REFERENCES `member` (`memId`),
  CONSTRAINT `favorite_to_post_fk` FOREIGN KEY (`fPost`) REFERENCES `post` (`pId`)
);

