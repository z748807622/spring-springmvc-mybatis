/*
Navicat MySQL Data Transfer

Source Server         : linux
Source Server Version : 50173
Source Host           : 115.28.17.46:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-06-28 20:32:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `album_name` varchar(255) DEFAULT NULL,
  `album_id` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for authors
-- ----------------------------
DROP TABLE IF EXISTS `authors`;
CREATE TABLE `authors` (
  `is_publish` varchar(255) DEFAULT NULL,
  `author_id` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `author_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for kutop500
-- ----------------------------
DROP TABLE IF EXISTS `kutop500`;
CREATE TABLE `kutop500` (
  `rank` int(10) NOT NULL,
  `songName` varchar(255) NOT NULL,
  `singerName` varchar(255) DEFAULT NULL,
  `fileHash` varchar(255) NOT NULL,
  `img` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `lyrics` text,
  `MvHash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rank`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for songinfo
-- ----------------------------
DROP TABLE IF EXISTS `songinfo`;
CREATE TABLE `songinfo` (
  `fileHash` varchar(255) NOT NULL,
  `songName` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `lyrics` text,
  `singerName` varchar(255) DEFAULT NULL,
  `MvHash` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fileHash`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `passwd` varchar(50) NOT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `zhuceriqi` date DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for visitor
-- ----------------------------
DROP TABLE IF EXISTS `visitor`;
CREATE TABLE `visitor` (
  `ip` varchar(255) NOT NULL,
  `times` int(5) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`ip`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
