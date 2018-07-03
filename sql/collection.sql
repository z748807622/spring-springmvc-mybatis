/*
Navicat MySQL Data Transfer

Source Server         : linux
Source Server Version : 50173
Source Host           : 115.28.17.46:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-07-03 20:04:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `username` varchar(255) NOT NULL,
  `filehash` varchar(255) NOT NULL,
  `date` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
