/*
Navicat MySQL Data Transfer

Source Server         : qfy
Source Server Version : 50523
Source Host           : localhost:3306
Source Database       : db_manager

Target Server Type    : MYSQL
Target Server Version : 50523
File Encoding         : 65001

Date: 2016-04-11 22:37:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_commodity`
-- ----------------------------
DROP TABLE IF EXISTS `tb_commodity`;
CREATE TABLE `tb_commodity` (
  `cno` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `inprice` int(11) DEFAULT NULL,
  `outprice` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_commodity
-- ----------------------------
INSERT INTO `tb_commodity` VALUES ('1111', '小明', '555', '777', '80', '专卖小明');

-- ----------------------------
-- Table structure for `tb_indent`
-- ----------------------------
DROP TABLE IF EXISTS `tb_indent`;
CREATE TABLE `tb_indent` (
  `ino` varchar(255) NOT NULL,
  `cno` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `superior` varchar(255) DEFAULT NULL,
  `icount` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `isoutsell` int(11) DEFAULT NULL,
  `istate` int(11) DEFAULT NULL,
  `itime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  PRIMARY KEY (`ino`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_indent
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `superior` varchar(255) DEFAULT NULL,
  `isstock` int(11) DEFAULT NULL,
  `issell` int(11) DEFAULT NULL,
  `ismgr` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('admin', 'admin', '梁培鹏', '0', 'admin0', '1', '1', '1', '2016-02-24 17:42:03');
