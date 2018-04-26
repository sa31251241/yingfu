/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : yingfu

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-25 11:35:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `cus_id` int(11) NOT NULL COMMENT '客户id',
  `cus_name` varchar(255) NOT NULL COMMENT '客户姓名',
  `cus_sex` varchar(255) NOT NULL COMMENT '客户性别',
  `cus_phone` varchar(255) NOT NULL COMMENT '客户联系方式',
  `cus_address` varchar(255) NOT NULL COMMENT '客户住址',
  `receive_name` varchar(255) DEFAULT NULL COMMENT '接收人姓名',
  `receive_phone` varchar(255) DEFAULT NULL COMMENT '接收人电话',
  `purchase_count` varchar(255) DEFAULT NULL COMMENT '够买单数',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '消费总金额',
  `reserve` varchar(255) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`cus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
