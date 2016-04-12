CREATE DATABASE IF NOT EXISTS `db_manager`
  DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE db_manager;

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
INSERT INTO `tb_commodity` VALUES ('1001', '小明', '5288', '6028', '5213', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1002', '小明', '7010', '7991', '19429', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1003', '小明', '1940', '2289', '2811', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1004', '小明', '3066', '3280', '8516', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1005', '小明', '3396', '3769', '19142', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1006', '小明', '4800', '5376', '6527', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1007', '小明', '6314', '6882', '19893', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1008', '小明', '3511', '4213', '11167', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1009', '小明', '3915', '4463', '8506', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1010', '小明', '5272', '6168', '5379', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1011', '小明', '8722', '9594', '16629', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1012', '小明', '4521', '4837', '2903', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1013', '小明', '6675', '7476', '4960', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1014', '小明', '1309', '1400', '12639', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1015', '小明', '507', '588', '8816', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1016', '小明', '3806', '4376', '13231', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1017', '小明', '5108', '6078', '16073', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1018', '小明', '5368', '5958', '10884', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1019', '小明', '7081', '8284', '11702', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1020', '小明', '6103', '6591', '16458', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1021', '小明', '5465', '5956', '1306', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1022', '小明', '2405', '2693', '4713', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1023', '小明', '5448', '6374', '10681', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1024', '小明', '9567', '10141', '6281', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1025', '小明', '9409', '10161', '7661', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1026', '小明', '4926', '5320', '13006', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1027', '小明', '6232', '7416', '18807', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1028', '小明', '1888', '2171', '11993', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1029', '小明', '7442', '7814', '2296', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1030', '小明', '2155', '2305', '11179', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1031', '小明', '635', '736', '15304', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1032', '小明', '3596', '4315', '10056', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1033', '小明', '9253', '10548', '9662', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1034', '小明', '2721', '3183', '4871', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1035', '小明', '5872', '6752', '10916', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1036', '小明', '5705', '6560', '9547', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1037', '小明', '4940', '5187', '16569', '专卖小明');
INSERT INTO `tb_commodity` VALUES ('1038', '小明', '9626', '11551', '11236', '专卖小明');

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
  -- 是否出货订单。0表示进货，1表示为出货
  `isoutsell` int(11) DEFAULT NULL,
  -- 审核状态。0表示待审核，1表示成功，2表示审核失败
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
-- 等级，是否需要再建一个子表显示这个。0表示等级最高，1为普通管理员，2为普通用户
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
INSERT INTO `tb_user` VALUES ('tom', '12345678', '汤姆克鲁斯', '0', 'admin0', '1', '1', '1', '2016-02-24 17:45:12');
INSERT INTO `tb_user` VALUES ('alex', '12345678', '亚历克斯', '0', 'admin0', '1', '1', '1', '2016-02-24 17:46:34');
INSERT INTO `tb_user` VALUES ('david', '12345678', '戴维', '0', 'admin0', '1', '1', '1', '2016-02-24 17:48:28');
