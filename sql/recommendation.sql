/*
Navicat MySQL Data Transfer

Source Server         : linux
Source Server Version : 50173
Source Host           : 115.28.17.46:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-07-03 20:05:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for recommendation
-- ----------------------------
DROP TABLE IF EXISTS `recommendation`;
CREATE TABLE `recommendation` (
  `filehash` varchar(100) NOT NULL,
  `type` int(5) NOT NULL,
  `rank` int(5) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`filehash`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
