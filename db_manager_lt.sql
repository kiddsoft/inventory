/*
Navicat MySQL Data Transfer

Source Server         : qfy
Source Server Version : 50523
Source Host           : localhost:3306
Source Database       : db_manager

Target Server Type    : MYSQL
Target Server Version : 50523
File Encoding         : 65001

Date: 2016-04-30 14:47:54
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
INSERT INTO `tb_commodity` VALUES ('1111', '饼干', '2', '3', '4', '1b');
INSERT INTO `tb_commodity` VALUES ('123', '甲鱼', '1', '1', '1', '1');

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
INSERT INTO `tb_indent` VALUES ('52422034004742711294', '123', 'admin', 'admin', '12', '12', '1', '0', '2016-04-30 14:46:47', '2016-04-30 14:46:47');
INSERT INTO `tb_indent` VALUES ('87672789977204834530', '123', 'admin', 'admin', '66', '66', '1', '0', '2016-04-30 14:46:53', '2016-04-30 14:46:53');
INSERT INTO `tb_indent` VALUES ('95087602878098193908', '123', 'admin', 'admin', '11', '11', '1', '0', '2016-04-30 14:46:41', '2016-04-30 14:46:41');
INSERT INTO `tb_indent` VALUES ('95282482720059539886', '123', 'admin', 'admin', '7', '7', '1', '0', '2016-04-30 14:47:02', '2016-04-30 14:47:02');

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
INSERT INTO `tb_user` VALUES ('1111', 'admin', '邱飞燕', '1', 'admin', '1', '1', '0', '2016-03-27 21:57:28');
INSERT INTO `tb_user` VALUES ('2222', 'admin', '朱哥', '0', 'admin', '1', '1', '1', '2016-03-27 22:23:16');
INSERT INTO `tb_user` VALUES ('3333', 'admin', '小飞龙', '1', '2222', '1', '1', '1', '2016-03-27 22:22:55');
INSERT INTO `tb_user` VALUES ('4444', 'admin', '无敌', '1', 'admin', '1', '0', '0', '2016-03-27 21:57:28');
INSERT INTO `tb_user` VALUES ('5555', 'admin', '有敌', '1', 'admin', '1', '0', '0', '2016-03-27 21:57:28');
INSERT INTO `tb_user` VALUES ('666', 'admin', '出卖', '1', 'admin', '0', '1', '0', '2016-03-27 21:57:28');
INSERT INTO `tb_user` VALUES ('7777', 'admin', '卖出', '1', 'admin', '0', '1', '0', '2016-03-27 21:57:28');
INSERT INTO `tb_user` VALUES ('8888', 'admin', '样样有', '1', 'admin', '1', '1', '0', '2016-03-27 21:57:28');
INSERT INTO `tb_user` VALUES ('admin', 'admin', '小二明', '0', 'admin', '1', '1', '1', '2016-03-27 22:23:05');

-- ----------------------------
-- Table structure for `tb_bbs`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bbs`;
CREATE TABLE `tb_bbs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
