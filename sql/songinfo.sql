/*
Navicat MySQL Data Transfer

Source Server         : linux
Source Server Version : 50173
Source Host           : 115.28.17.46:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-07-03 20:05:49
*/

SET FOREIGN_KEY_CHECKS=0;

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
