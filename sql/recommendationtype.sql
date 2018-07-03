/*
Navicat MySQL Data Transfer

Source Server         : linux
Source Server Version : 50173
Source Host           : 115.28.17.46:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-07-03 20:05:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for recommendationtype
-- ----------------------------
DROP TABLE IF EXISTS `recommendationtype`;
CREATE TABLE `recommendationtype` (
  `id` int(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
