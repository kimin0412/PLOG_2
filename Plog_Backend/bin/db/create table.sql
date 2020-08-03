create database if not exists plog;

use plog;

CREATE TABLE `user` (
  `u_id` int NOT NULL AUTO_INCREMENT,
  `u_email` varchar(30) NOT NULL,
  `u_password` varchar(30) NOT NULL,
  `u_username` varchar(30) NOT NULL,
  `u_phone` varchar(30),
  `u_birthday` varchar(30),
  `u_regdate` timestamp DEFAULT CURRENT_TIMESTAMP,
  `u_nickname` varchar(30),
  PRIMARY KEY (`u_id`)
);


CREATE TABLE `schedule` (
  `s_id` int NOT NULL AUTO_INCREMENT,
  `s_name` varchar(50) NOT NULL,
  `s_content` varchar(2000) NOT NULL,
  `s_startdate` timestamp NOT NULL,
  `s_enddate` timestamp NOT NULL,
  `s_user` int not null,
  PRIMARY KEY (`s_id`),
  CONSTRAINT `schedule_to_user_fk` FOREIGN KEY (`s_user`) REFERENCES `user` (`u_id`)
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
  `p_user` int NOT NULL,
  `p_schedule` int NOT NULL,
  `p_category` int NOT NULL,
  PRIMARY KEY (`p_id`),
  CONSTRAINT `post_to_user_fk` FOREIGN KEY (`p_user`) REFERENCES `user` (`u_id`),
  CONSTRAINT `post_to_schedule_fk` FOREIGN KEY (`p_schedule`) REFERENCES `schedule` (`s_id`),
  CONSTRAINT `post_to_category_fk` FOREIGN KEY (`p_category`) REFERENCES `category` (`c_id`)
); 

CREATE TABLE `tmppost` (
  `tp_id` int NOT NULL AUTO_INCREMENT,
  `tp_title` varchar(50) NOT NULL,
  `tp_content` varchar(16000) NOT NULL,
  `tp_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `tp_user` int NOT NULL,
  PRIMARY KEY (`tp_id`),
  CONSTRAINT `tmppost_to_user_fk` FOREIGN KEY (`tp_user`) REFERENCES `user` (`u_id`)
);

CREATE TABLE `hashtag` (
  `h_id` int NOT NULL AUTO_INCREMENT,
  `h_name` varchar(50) NOT NULL,
  PRIMARY KEY (`h_id`)
); 

CREATE TABLE `post_hashtag` (
  `ph_id` int NOT NULL AUTO_INCREMENT,
  `ph_post` int,
  `ph_hashtag` int NOT NULL,
  `ph_user` int NOT NULL,
  PRIMARY KEY (`ph_id`),
  KEY `post_to_ph_fk` (`ph_post`),
  KEY `hashtag_to_ph_fk` (`ph_hashtag`),
  KEY `user_to_ph_fk` (`ph_user`),
  CONSTRAINT `hashtag_to_ph_fk` FOREIGN KEY (`ph_hashtag`) REFERENCES `hashtag` (`h_id`),
  CONSTRAINT `post_to_ph_fk` FOREIGN KEY (`ph_post`) REFERENCES `post` (`p_id`) on DELETE SET NULL,
  CONSTRAINT `user_to_ph_fk` FOREIGN KEY (`ph_user`) REFERENCES `user` (`u_id`)
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
  `f_user` int NOT NULL,
  `f_post` int NOT NULL,
  PRIMARY KEY (`f_id`),
  CONSTRAINT `favorite_to_user_fk` FOREIGN KEY (`f_user`) REFERENCES `user` (`u_id`),
  CONSTRAINT `favorite_to_post_fk` FOREIGN KEY (`f_post`) REFERENCES `post` (`p_id`)
);

insert into user values(1,'test','test','test','test','test','2020-07-04','test');
insert into category values(1,'test');
insert into schedule values(1,'test','test','2020-12-04','2020-12-04',1);
insert into post values(1,'test','test','2020-12-04',1,1,1);
