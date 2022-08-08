CREATE DATABASE newservice;

CREATE TABLE `tbl_keywords` (
    `keywordId` int NOT NULL AUTO_INCREMENT,
    `keywordName` varchar(100) NOT NULL,
    PRIMARY KEY (`keywordId`)
)

CREATE TABLE `tbl_member` (
    `memberId` int NOT NULL AUTO_INCREMENT,
    `loginId` varchar(12) NOT NULL,
    `password` varchar(120) NOT NULL,
    `name` varchar(5) NOT NULL,
    PRIMARY KEY (`memberId`)
)

CREATE TABLE `tbl_news` (
    `newsNo` int NOT NULL AUTO_INCREMENT,
    `title` varchar(100) NOT NULL,
    `originallink` varchar(100) NOT NULL,
    `link` varchar(100) NOT NULL,
    `description` text,
    `pubDate` varchar(100) DEFAULT NULL,
    `site` varchar(100) NOT NULL,
    `registerDate` datetime DEFAULT NULL,
    `keyword` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`newsNo`)
)