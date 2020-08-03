create database if not exists plog;

use plog;

CREATE TABLE `member` (
  `mem_id` int NOT NULL AUTO_INCREMENT,
  `mem_userid` varchar(30) NOT NULL,
  `mem_email` varchar(30) NOT NULL,
  `mem_password` varchar(30) NOT NULL,
  `mem_username` varchar(30) NOT NULL,
  `mem_phone` varchar(30) NOT NULL,
  `mem_birthday` varchar(30) NOT NULL,
  `mem_register_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mem_nickname` varchar(30) NOT NULL,
  `mem_intro` varchar(150) NOT NULL,
  PRIMARY KEY (`mem_id`)
);

CREATE TABLE `schedule` (
  `s_id` int NOT NULL AUTO_INCREMENT,
  `s_name` varchar(50) NOT NULL,
  `s_content` varchar(2000) NOT NULL,
  `s_startdate` timestamp NOT NULL,
  `s_enddate` timestamp NOT NULL,
  PRIMARY KEY (`s_id`)
); 

CREATE TABLE `category` (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `c_name` varchar(50) NOT NULL,
  PRIMARY KEY (`c_id`)
); 

CREATE TABLE `post` (
  `p_id` int NOT NULL AUTO_INCREMENT,
  `p_title` varchar(50) NOT NULL,
  `p_content` varchar(16000) NOT NULL,
  `p_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `p_member` int NOT NULL,
  `p_schedule` int NOT NULL,
  `p_category` int NOT NULL,
  PRIMARY KEY (`p_id`),
  CONSTRAINT `post_to_member_fk` FOREIGN KEY (`p_member`) REFERENCES `member` (`mem_id`),
  CONSTRAINT `post_to_schedule_fk` FOREIGN KEY (`p_schedule`) REFERENCES `schedule` (`s_id`),
  CONSTRAINT `post_to_category_fk` FOREIGN KEY (`p_category`) REFERENCES `category` (`c_id`)
); 

CREATE TABLE `tmppost` (
  `tp_id` int NOT NULL AUTO_INCREMENT,
  `tp_title` varchar(50) NOT NULL,
  `tp_content` varchar(16000) NOT NULL,
  `tp_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tp_member` int NOT NULL,
  PRIMARY KEY (`tp_id`),
  CONSTRAINT `tmppost_to_member_fk` FOREIGN KEY (`tp_member`) REFERENCES `member` (`mem_id`)
);



CREATE TABLE `hashtag` (
  `h_id` int NOT NULL AUTO_INCREMENT,
  `h_name` varchar(50) NOT NULL,
  PRIMARY KEY (`h_id`)
); 

CREATE TABLE `post_hashtag` (
  `ph_id` int NOT NULL AUTO_INCREMENT,
  `ph_post` int NOT NULL,
  `ph_hashtag` int NOT NULL,
  PRIMARY KEY (`ph_id`),
  CONSTRAINT `post_to_ph_fk` FOREIGN KEY (`ph_post`) REFERENCES `post` (`p_id`),
  CONSTRAINT `hashtag_to_ph_fk` FOREIGN KEY (`ph_hashtag`) REFERENCES `hashtag` (`h_id`)
);



CREATE TABLE `image` (
  `i_id` int NOT NULL AUTO_INCREMENT,
  `i_address` varchar(300) NOT NULL,
  `i_post` int NOT NULL,
  PRIMARY KEY (`i_id`),
  CONSTRAINT `post_to_image_fk` FOREIGN KEY (`i_post`) REFERENCES `post` (`p_id`)
);

CREATE TABLE `favorite` (
  `f_id` int NOT NULL AUTO_INCREMENT,
  `f_member` int NOT NULL,
  `f_post` int NOT NULL,
  PRIMARY KEY (`f_id`),
  CONSTRAINT `favorite_to_member_fk` FOREIGN KEY (`f_member`) REFERENCES `member` (`mem_id`),
  CONSTRAINT `favorite_to_post_fk` FOREIGN KEY (`f_post`) REFERENCES `post` (`p_id`)
);