/*
Navicat MySQL Data Transfer

Source Server         : linux
Source Server Version : 50173
Source Host           : 115.28.17.46:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-07-03 20:05:12
*/

SET FOREIGN_KEY_CHECKS=0;

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
