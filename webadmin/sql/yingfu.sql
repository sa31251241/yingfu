/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50544
Source Host           : localhost:3306
Source Database       : yingfu

Target Server Type    : MYSQL
Target Server Version : 50544
File Encoding         : 65001

Date: 2018-04-24 14:57:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_invin
-- ----------------------------
DROP TABLE IF EXISTS `t_invin`;
CREATE TABLE `t_invin` (
  `id` varchar(50) NOT NULL,
  `vendor_id` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `total_price` decimal(10,0) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_invin
-- ----------------------------
INSERT INTO `t_invin` VALUES ('6394232795689914368', '6391877130178793472', '12', '2018-04-10', '42', '8', 'oihjoih ', '1', '2018-04-24 01:18:17', null, '2018-04-24 12:29:35', null);

-- ----------------------------
-- Table structure for t_invin_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_invin_detail`;
CREATE TABLE `t_invin_detail` (
  `id` varchar(50) NOT NULL,
  `inv_in_id` varchar(50) DEFAULT NULL,
  `product_sku_id` varchar(50) DEFAULT NULL,
  `product_sku_code` varchar(50) DEFAULT NULL,
  `product_sku_name` varchar(50) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_price` decimal(10,0) DEFAULT NULL,
  `sub_total` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_invin_detail
-- ----------------------------
INSERT INTO `t_invin_detail` VALUES ('6394399736547250176', '6394232795689914368', null, 'af', 'saf', '3', '4', '12');
INSERT INTO `t_invin_detail` VALUES ('6394399736593387520', '6394232795689914368', null, 'safsaf', 'afasf', '5', '6', '30');

-- ----------------------------
-- Table structure for t_inv_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_inv_detail`;
CREATE TABLE `t_inv_detail` (
  `id` varchar(50) NOT NULL,
  `product_sku_id` varchar(50) DEFAULT NULL,
  `product_sku_code` varchar(50) DEFAULT NULL,
  `product_sku_name` varchar(50) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_price` decimal(10,0) DEFAULT NULL,
  `sub_total` decimal(10,0) DEFAULT NULL,
  `inv_order_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_inv_detail
-- ----------------------------
INSERT INTO `t_inv_detail` VALUES ('6394401731538587648', null, 'af', 'saf', '3', '4', '12', '1');
INSERT INTO `t_inv_detail` VALUES ('6394401731823800320', null, 'safsaf', 'afasf', '5', '6', '30', '1');

-- ----------------------------
-- Table structure for t_inv_sum
-- ----------------------------
DROP TABLE IF EXISTS `t_inv_sum`;
CREATE TABLE `t_inv_sum` (
  `id` varchar(50) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_inv_sum
-- ----------------------------
INSERT INTO `t_inv_sum` VALUES ('af', '3');
INSERT INTO `t_inv_sum` VALUES ('safsaf', '5');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `resource_type` int(11) DEFAULT NULL,
  `resource_id` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `available` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_product_sku
-- ----------------------------
DROP TABLE IF EXISTS `t_product_sku`;
CREATE TABLE `t_product_sku` (
  `id` varchar(50) NOT NULL,
  `sku_code` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type_id` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `vendor_id` varchar(50) DEFAULT NULL,
  `shelf_life` int(11) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_sku
-- ----------------------------
INSERT INTO `t_product_sku` VALUES ('6394436103545294848', '32134324214', '2314124', '63923605269737676800001000100010002', '1241214', '124214', '6391874946619936768', '12123', 'qwrewqr', null, '2018-04-24 14:46:09', null, '2018-04-24 14:52:01');

-- ----------------------------
-- Table structure for t_product_sku_config
-- ----------------------------
DROP TABLE IF EXISTS `t_product_sku_config`;
CREATE TABLE `t_product_sku_config` (
  `id` varchar(50) NOT NULL,
  `product_sku_id` varchar(50) DEFAULT NULL,
  `max_storage` int(11) DEFAULT NULL,
  `min_storage` int(11) DEFAULT NULL,
  `expire_warning` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_sku_config
-- ----------------------------
INSERT INTO `t_product_sku_config` VALUES ('6394436103885033472', '6394436103545294848', '4', '2', '3');

-- ----------------------------
-- Table structure for t_product_type
-- ----------------------------
DROP TABLE IF EXISTS `t_product_type`;
CREATE TABLE `t_product_type` (
  `id` varchar(50) NOT NULL,
  `pid` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_type
-- ----------------------------
INSERT INTO `t_product_type` VALUES ('63923605269737676800001', '-1', 'sadfsaf', 'saf', null, '2018-04-18 21:18:33', null, '2018-04-18 21:18:33');
INSERT INTO `t_product_type` VALUES ('639236052697376768000010001', '63923605269737676800001', 'asfsa', 'fasf', null, '2018-04-18 21:20:38', null, '2018-04-18 21:20:38');
INSERT INTO `t_product_type` VALUES ('6392360526973767680000100010001', '639236052697376768000010001', '阿发斯蒂芬', null, null, '2018-04-19 17:21:19', null, '2018-04-19 17:21:19');
INSERT INTO `t_product_type` VALUES ('63923605269737676800001000100010001', '6392360526973767680000100010001', '啊发顺丰', null, null, '2018-04-19 17:21:25', null, '2018-04-19 17:21:25');
INSERT INTO `t_product_type` VALUES ('63923605269737676800001000100010002', '6392360526973767680000100010001', '阿发', 'dsaf', null, '2018-04-23 22:21:36', null, '2018-04-23 22:21:36');
INSERT INTO `t_product_type` VALUES ('639236052697376768000010001000100020001', '63923605269737676800001000100010002', '阿发阿发', null, null, '2018-04-23 22:21:45', null, '2018-04-23 22:21:45');
INSERT INTO `t_product_type` VALUES ('6392360526973767680000100010001000200010001', '639236052697376768000010001000100020001', '啊发顺丰', null, null, '2018-04-23 22:21:49', null, '2018-04-23 22:21:49');
INSERT INTO `t_product_type` VALUES ('6392360526973767680000100010002', '639236052697376768000010001', '阿什顿发', null, null, '2018-04-23 22:21:40', null, '2018-04-23 22:21:40');
INSERT INTO `t_product_type` VALUES ('639236052697376768000010002', '63923605269737676800001', '撒发顺丰', null, null, '2018-04-23 22:21:55', null, '2018-04-23 22:21:55');
INSERT INTO `t_product_type` VALUES ('6392360526973767680000100020001', '639236052697376768000010002', '啊发发', null, null, '2018-04-23 22:22:00', null, '2018-04-23 22:22:00');
INSERT INTO `t_product_type` VALUES ('6392360526973767680000100020002', '639236052697376768000010002', '撒旦法师法', null, null, '2018-04-23 22:22:06', null, '2018-04-23 22:22:06');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `desc` varchar(500) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `access_all` int(11) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(50) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role_id` varchar(50) DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('12321', 'haha', '21312', '3213', '123213', '213213', '213', '213123', '213123');
INSERT INTO `t_user` VALUES ('1241234', 'ha123', '213213', '123', '213', '2134', null, '124', null);

-- ----------------------------
-- Table structure for t_vendor
-- ----------------------------
DROP TABLE IF EXISTS `t_vendor`;
CREATE TABLE `t_vendor` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `telphone` varchar(50) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL,
  `contact_mobile` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `fax` varchar(50) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_vendor
-- ----------------------------
INSERT INTO `t_vendor` VALUES ('6391661716744179712', 'aaa', 'safaaa', 'sadf', 'safaaa', 'safdaaa', 'asf', 'asf', 'safsaf', null, null, null, '2018-04-18 17:09:45');
INSERT INTO `t_vendor` VALUES ('6391874946619936768', 'asf', 'af', 'saf', 'saf', 'safasf', 'asf', 'saf', '', null, null, null, null);
INSERT INTO `t_vendor` VALUES ('6391877130178793472', '333', '33', '33', '33', '33', '333', '333', '333', null, null, null, null);
INSERT INTO `t_vendor` VALUES ('6391894958210551808', 'dsag', 'asgf', 'af', 'ad', 'asfasf', 'afa', 'fasfsaf', '', null, null, null, null);
INSERT INTO `t_vendor` VALUES ('6391895014586191872', 'avc', 'av', 'af', 'av', 'afasf', 'asf', 'sad', 'asdfa', null, null, null, null);
INSERT INTO `t_vendor` VALUES ('6391895038233677824', 'asf', 'saf', 'as', 'v', 'avcasf', 'asf', '', '', null, null, null, null);
INSERT INTO `t_vendor` VALUES ('6391895057066102784', 'saf', 'asf', 'asf', 'v', 'a', 'ds', 'fasdf', '', null, null, null, null);
INSERT INTO `t_vendor` VALUES ('6391895088863121408', 'asf', 'fg', 'asf', 'asdfsadf', 'asf', 'asf', 'asfasf', '', null, null, null, null);
INSERT INTO `t_vendor` VALUES ('6392352143004667904', 'asf', 'saf', 'asf', 'a', 'asfsaf', null, null, null, null, '2018-04-18 20:45:15', null, '2018-04-18 20:45:15');
INSERT INTO `t_vendor` VALUES ('6392352150457946112', 'asf', 'saf', 'asf', 'a', 'asfsaf', null, null, null, null, '2018-04-18 20:45:16', null, '2018-04-18 20:45:16');
INSERT INTO `t_vendor` VALUES ('6392353266147004416', 'afas', 'fasf', 'fsadf', 'asf', 'asfsa', 'asf', null, null, null, '2018-04-18 20:49:42', null, '2018-04-18 20:49:42');
INSERT INTO `t_vendor` VALUES ('6392353615352172544', 'asf', 'asf', 'asf', 'saf', 'saf', null, null, null, null, '2018-04-18 20:51:06', null, '2018-04-18 20:51:06');
INSERT INTO `t_vendor` VALUES ('6392353734759813120', 'saf', 'asf', 'saf', 'asf', 'saf', null, null, null, null, '2018-04-18 20:51:34', null, '2018-04-18 20:51:34');
INSERT INTO `t_vendor` VALUES ('6392353995658104832', 'asfsa', 'f', 'fasf', 'safsa', 'fsad', null, null, null, null, '2018-04-18 20:52:36', null, '2018-04-18 20:52:36');
