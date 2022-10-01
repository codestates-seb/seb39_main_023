drop all objects;

CREATE TABLE `MEMBER` (
  `MEMBER_ID` int PRIMARY KEY AUTO_INCREMENT,
  `LOGIN_ID` varchar(25) UNIQUE NOT NULL,
  `PASSWORD` varchar(25) NOT NULL,
  `EMAIL` varchar(50) UNIQUE NOT NULL,
  `NICKNAME` varchar(25) UNIQUE NOT NULL,
  `PROFILE_IMAGE_ID` int,
  `CREATED_AT` datetime,
  `NAME` varchar(50)
);

CREATE TABLE `RENT_POST` (
  `RENT_POST_ID` int PRIMARY KEY AUTO_INCREMENT,
  `RENT_POST_CONTENTS` varchar(255) NOT NULL,
  `RENT_POST_NAME` varchar(50) NOT NULL,
  `WRITER_ID` int,
  `WRITE_DATE` datetime,
  `UPDATE_DATE` datetime,
  `RENT_STATUS` boolean DEFAULT false,
  `CATEGORY` varchar(100),
  `LOCATION` varchar(100),
  `RENT_PRICE` int,
  `VIEW_COUNT` int
);

CREATE TABLE `PICTURE` (
  `PICTURE_ID` int PRIMARY KEY AUTO_INCREMENT,
  `FILE_NAME` varchar(100),
  `RENT_POST_ID` int
);

CREATE TABLE `COMMENT` (
  `COMMENT_ID` int PRIMARY KEY AUTO_INCREMENT,
  `COMMENT_CONTENTS` varchar(1000),
  `WRITE_DATE` datetime,
  `UPDATE_DATE` datetime,
  `WRITER_ID` int,
  `TARGET_POST_ID` int
);

CREATE TABLE `LOGIN` (
  `LOGIN_ID` int PRIMARY KEY AUTO_INCREMENT,
  `MEMBER_ID` int,
  `TOKEN` text ,
  `LAST_LOGIN_DATE` datetime,
  `LOGOUT_DATE` datetime,
  `LOGOUTED` boolean DEFAULT false
);

CREATE TABLE `rent_history` (
  `RENT_HISTORY_ID` int PRIMARY KEY AUTO_INCREMENT,
  `TARGET_MEMBER_ID` int,
  `RENT_DATA_TYPE` boolean DEFAULT false,
  `RENT_STATUS` varchar(100),
  `RENT_START_DATE` datetime,
  `RENT_END_DATE` datetime,
  `REQUESTER_ID` int,
  `MSG` varchar(200),
  `TARGET_POST_ID` int,
  `created_time` datetime,
  `UPDATE_TIME` datetime,
  `RELATE_RENT_HISTORY` int
);

CREATE TABLE `category`(
`CID` int PRIMARY KEY AUTO_INCREMENT,
`NAME` varchar(100)
);

CREATE TABLE `location`(
`LID` int PRIMARY KEY AUTO_INCREMENT,
`NAME` varchar(100)
);

ALTER TABLE `MEMBER` ADD FOREIGN KEY (`PROFILE_IMAGE_ID`) REFERENCES `PICTURE` (`PICTURE_ID`);

ALTER TABLE `RENT_POST` ADD FOREIGN KEY (`WRITER_ID`) REFERENCES `MEMBER` (`MEMBER_ID`);

ALTER TABLE `PICTURE` ADD FOREIGN KEY (`RENT_POST_ID`) REFERENCES `RENT_POST` (`RENT_POST_ID`);

ALTER TABLE `COMMENT` ADD FOREIGN KEY (`WRITER_ID`) REFERENCES `MEMBER` (`MEMBER_ID`);

ALTER TABLE `COMMENT` ADD FOREIGN KEY (`TARGET_POST_ID`) REFERENCES `RENT_POST` (`RENT_POST_ID`);

ALTER TABLE `LOGIN` ADD FOREIGN KEY (`MEMBER_ID`) REFERENCES `MEMBER` (`MEMBER_ID`);

ALTER TABLE `RENT_HISTORY` ADD FOREIGN KEY (`TARGET_MEMBER_ID`) REFERENCES `MEMBER` (`MEMBER_ID`);

ALTER TABLE `RENT_HISTORY` ADD FOREIGN KEY (`REQUESTER_ID`) REFERENCES `MEMBER` (`MEMBER_ID`);

ALTER TABLE `RENT_HISTORY` ADD FOREIGN KEY (`TARGET_POST_ID`) REFERENCES `RENT_POST` (`RENT_POST_ID`);

ALTER TABLE `RENT_HISTORY` ADD FOREIGN KEY (`RELATE_RENT_HISTORY`) REFERENCES `RENT_HISTORY` (`RENT_HISTORY_ID`);
