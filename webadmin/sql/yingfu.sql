/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : yingfu

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-05-11 16:50:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `cus_id` varchar(255) NOT NULL,
  `cus_name` varchar(255) DEFAULT NULL,
  `cus_sex` varchar(255) DEFAULT NULL,
  `cus_phone` varchar(255) DEFAULT NULL,
  `cus_address` varchar(255) DEFAULT NULL,
  `add_time` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('6399080216886513664', '廖文武', '男', '17293472190', 'sadUK假期哦危机', '2018-05-07', '少时诵诗书');
INSERT INTO `t_customer` VALUES ('6399529239971893248', '湛芳芳', '男', '2132412412', '2阿斯顿你', '2018-05-08', 'sad 请问');

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
INSERT INTO `t_invin` VALUES ('6399087299757346816', '6399080860020117504', '11', '2018-05-07', '60000', '20', '健康路附近萨洛克', '1', '2018-05-07 10:48:21', null, '2018-05-07 10:48:25', null);

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
INSERT INTO `t_invin_detail` VALUES ('6399087299837038592', '6399087299757346816', null, 'YINGFU001', '舒达', '20', '3000', '60000');

-- ----------------------------
-- Table structure for t_invout
-- ----------------------------
DROP TABLE IF EXISTS `t_invout`;
CREATE TABLE `t_invout` (
  `id` varchar(50) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `take_user_id` varchar(50) DEFAULT NULL,
  `total_price` decimal(10,0) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_invout
-- ----------------------------
INSERT INTO `t_invout` VALUES ('6399853013401341952', '21', '2018-05-09', 'liaoww', '22222', 'lllll', '0', '2018-05-09 13:31:01', null, '2018-05-09 13:31:01', null);
INSERT INTO `t_invout` VALUES ('6399854608608399360', '23', '2018-05-09', 'liaoww', '9999', 'sdanfio', '1', '2018-05-09 13:37:22', null, '2018-05-09 17:19:02', null);

-- ----------------------------
-- Table structure for t_invout_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_invout_detail`;
CREATE TABLE `t_invout_detail` (
  `id` varchar(50) NOT NULL,
  `inv_out_id` varchar(50) DEFAULT NULL,
  `product_sku_id` varchar(50) DEFAULT NULL,
  `product_sku_code` varchar(50) DEFAULT NULL,
  `product_sku_name` varchar(100) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unit_price` decimal(10,0) DEFAULT NULL,
  `sub_total` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_invout_detail
-- ----------------------------
INSERT INTO `t_invout_detail` VALUES ('6399853013535559680', '6399853013401341952', null, 'YINGFU001', '舒达', '1', '22222', '22222');
INSERT INTO `t_invout_detail` VALUES ('6399854608658731008', '6399854608608399360', null, 'YINGFU001', '舒达', '1', '9999', '9999');

-- ----------------------------
-- Table structure for t_inv_check
-- ----------------------------
DROP TABLE IF EXISTS `t_inv_check`;
CREATE TABLE `t_inv_check` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_inv_check
-- ----------------------------
INSERT INTO `t_inv_check` VALUES ('6394782510395428864', '', '2018-04-26', 'sadfasfsaf', '0', '2018-04-25 13:42:39', null, '2018-04-26 10:27:19', null);

-- ----------------------------
-- Table structure for t_inv_check_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_inv_check_detail`;
CREATE TABLE `t_inv_check_detail` (
  `id` varchar(50) NOT NULL,
  `inv_check_id` varchar(50) DEFAULT NULL,
  `product_sku_id` varchar(50) DEFAULT NULL,
  `product_sku_code` varchar(50) DEFAULT NULL,
  `product_sku_name` varchar(255) DEFAULT NULL,
  `product_type_name` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `vendor_name` varchar(255) DEFAULT NULL,
  `check_quantity` int(11) DEFAULT NULL,
  `diff_quantity` int(11) DEFAULT NULL,
  `stocks` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_inv_check_detail
-- ----------------------------
INSERT INTO `t_inv_check_detail` VALUES ('6395095740426752000', '6394782510395428864', null, '648923A1', '沙发一号', '真皮沙发', '2米*3米', '真皮', 'aaa', '3', '0', '0');

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
INSERT INTO `t_inv_detail` VALUES ('6394579112207323136', null, '32134324214', '2314124', '4', '5', '20', '1');
INSERT INTO `t_inv_detail` VALUES ('6394731743647109120', null, '32134324214', '2314124', '2', '3', '6', '2');
INSERT INTO `t_inv_detail` VALUES ('6394732852260376576', null, '32134324214', '2314124', '2', '5', '10', '2');
INSERT INTO `t_inv_detail` VALUES ('6394782183973720064', null, '32134324214', '2314124', '10', '100', '1000', '1');
INSERT INTO `t_inv_detail` VALUES ('6394782184225378304', null, '3243214321', '43214', '20', '30', '600', '1');
INSERT INTO `t_inv_detail` VALUES ('6395102547824087040', null, '648923A1', '沙发一号', '42', '4', '168', '1');
INSERT INTO `t_inv_detail` VALUES ('6395102895775158272', null, '648923A1', '沙发一号', '2', '10', '20', '2');
INSERT INTO `t_inv_detail` VALUES ('6395108759659614208', null, '648923A1', '沙发一号', '1', '1', '1', '2');
INSERT INTO `t_inv_detail` VALUES ('6399087314148003840', null, 'YINGFU001', '舒达', '20', '3000', '60000', '1');
INSERT INTO `t_inv_detail` VALUES ('6399134953283522560', null, 'YINGFU001', '舒达', '1', '3000', '3000', '2');
INSERT INTO `t_inv_detail` VALUES ('6399910394600624128', null, 'YINGFU001', '舒达', '1', '9999', '9999', '2');

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
INSERT INTO `t_inv_sum` VALUES ('YINGFU001', '18');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` varchar(50) DEFAULT NULL,
  `pid` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  KEY `id` (`id`)
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
INSERT INTO `t_product_sku` VALUES ('6399086252389306368', 'YINGFU001', '舒达', '6399082856840171520000100020001', '3m*2m', '舒达', '6399080860020117504', '0', '1111111111', null, '2018-05-07 10:44:11', null, '2018-05-07 10:44:11');
INSERT INTO `t_product_sku` VALUES ('6399086494362898432', 'YINGFU002', '席梦思', '6399082856840171520000100020002', '3m*2m', '席梦思', '6399081081357733888', '0', '22222', null, '2018-05-07 10:45:09', null, '2018-05-07 10:45:09');
INSERT INTO `t_product_sku` VALUES ('6399086788660432896', 'YINGFU003', '慕思', '6399082856840171520000100020001', '3m*2m', '慕思', '6399080860020117504', '0', '222221', null, '2018-05-07 10:46:19', null, '2018-05-07 10:46:19');

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
INSERT INTO `t_product_sku_config` VALUES ('6394436103885033472', '6394436103545294848', '80', '40', '3');
INSERT INTO `t_product_sku_config` VALUES ('6394579835015925760', '6394579834818793472', '1234', '231', '123213');
INSERT INTO `t_product_sku_config` VALUES ('6394829189396041728', '6394829189077274624', '0', '0', '0');
INSERT INTO `t_product_sku_config` VALUES ('6399086252473192448', '6399086252389306368', '30', '2', '2');
INSERT INTO `t_product_sku_config` VALUES ('6399086494404841472', '6399086494362898432', '30', '2', '2');
INSERT INTO `t_product_sku_config` VALUES ('6399086788698181632', '6399086788660432896', '30', '2', '2');

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
INSERT INTO `t_product_type` VALUES ('63990828568401715200001', '-1', '卧室家具', null, null, '2018-05-07 10:30:42', null, '2018-05-07 10:30:42');
INSERT INTO `t_product_type` VALUES ('639908285684017152000010001', '63990828568401715200001', '床', null, null, '2018-05-07 10:30:54', null, '2018-05-07 10:30:54');
INSERT INTO `t_product_type` VALUES ('639908285684017152000010002', '63990828568401715200001', '床垫', null, null, '2018-05-07 10:31:04', null, '2018-05-07 10:31:04');
INSERT INTO `t_product_type` VALUES ('6399082856840171520000100020001', '639908285684017152000010002', '棕垫', '睡感较硬，一般价格比较低廉，缺点因为工艺需要用到黏合剂，所以其实并不太环保，易生虫', null, '2018-05-07 10:37:39', null, '2018-05-07 10:37:39');
INSERT INTO `t_product_type` VALUES ('6399082856840171520000100020002', '639908285684017152000010002', '连体弹簧床垫', '睡感偏硬，经常说的席梦思床垫就是弹簧床垫，其实席梦思是个品牌。优点经久耐用，缺点不透气，因为床垫是连成一块整体的，所以有局部有晃动全床垫也会跟着动。', null, '2018-05-07 10:37:58', null, '2018-05-07 10:37:58');
INSERT INTO `t_product_type` VALUES ('6399082856840171520000100020003', '639908285684017152000010002', '独立筒袋装弹簧', '是一个个独立弹簧用无纺布包装成一个整体的床垫，睡感中性，根据独立筒口径大小不一软硬不一，抗干扰性好，适合作息时间不一致的夫妻使用，耐用性不如连体弹簧。', null, '2018-05-07 10:38:17', null, '2018-05-07 10:38:17');
INSERT INTO `t_product_type` VALUES ('6399082856840171520000100020004', '639908285684017152000010002', '乳胶床垫', '透气性好，睡感柔软，原材料不一分为天然乳胶和人工乳胶，天然的更环保，人工合成的更耐用。注意天然乳胶一般都不可以曝晒。因为工艺原因所以一般纯乳胶床垫都不能做太厚，支撑性要比弹簧床垫差很多。耐用性也不如弹簧床垫。好的天然乳胶床垫使用三五年就氧化了是很正常的事情。用个十年八年反而你要担心材质是否天然了。', null, '2018-05-07 10:38:49', null, '2018-05-07 10:38:49');
INSERT INTO `t_product_type` VALUES ('6399082856840171520000100020005', '639908285684017152000010002', '记忆棉床垫', '又称慢回弹太空棉，睡感柔软，根据环境温度及压力的变化会产生变化，此类床垫躺上会有包裹感。', null, '2018-05-07 10:39:08', null, '2018-05-07 10:39:08');
INSERT INTO `t_product_type` VALUES ('6399082856840171520000100020006', '639908285684017152000010002', '儿童床垫', '婴儿床垫这个概念已经广为人知，婴儿床垫主要作用是支撑其身体，防止婴儿脊椎变形，令宝宝四肢放松，促进血液循环，有利于婴儿健康发育。', null, '2018-05-07 10:40:43', null, '2018-05-07 10:40:43');
INSERT INTO `t_product_type` VALUES ('639908285684017152000010003', '63990828568401715200001', '化妆台', null, null, '2018-05-07 10:31:25', null, '2018-05-07 10:31:25');
INSERT INTO `t_product_type` VALUES ('639908285684017152000010004', '63990828568401715200001', '床头柜', null, null, '2018-05-07 10:31:39', null, '2018-05-07 10:31:39');
INSERT INTO `t_product_type` VALUES ('639908285684017152000010005', '63990828568401715200001', '穿衣镜', null, null, '2018-05-07 10:32:02', null, '2018-05-07 10:32:02');
INSERT INTO `t_product_type` VALUES ('639908285684017152000010006', '63990828568401715200001', '衣柜', null, null, '2018-05-07 10:32:14', null, '2018-05-07 10:32:14');
INSERT INTO `t_product_type` VALUES ('63990828568401715200002', '-1', '客厅家具', null, null, '2018-05-07 10:32:37', null, '2018-05-07 10:32:37');
INSERT INTO `t_product_type` VALUES ('639908285684017152000020001', '63990828568401715200002', '沙发', null, null, '2018-05-07 10:32:45', null, '2018-05-07 10:32:45');
INSERT INTO `t_product_type` VALUES ('639908285684017152000020002', '63990828568401715200002', '茶几', null, null, '2018-05-07 10:32:56', null, '2018-05-07 10:32:56');
INSERT INTO `t_product_type` VALUES ('639908285684017152000020003', '63990828568401715200002', '电视柜', null, null, '2018-05-07 10:33:05', null, '2018-05-07 10:33:05');
INSERT INTO `t_product_type` VALUES ('639908285684017152000020004', '63990828568401715200002', '背景墙', null, null, '2018-05-07 10:33:14', null, '2018-05-07 10:33:14');
INSERT INTO `t_product_type` VALUES ('63990828568401715200003', '-1', '餐厅家具', null, null, '2018-05-07 10:33:26', null, '2018-05-07 10:33:26');
INSERT INTO `t_product_type` VALUES ('639908285684017152000030001', '63990828568401715200003', '餐桌', null, null, '2018-05-07 10:33:41', null, '2018-05-07 10:33:41');
INSERT INTO `t_product_type` VALUES ('639908285684017152000030002', '63990828568401715200003', '餐椅', null, null, '2018-05-07 10:33:48', null, '2018-05-07 10:33:48');
INSERT INTO `t_product_type` VALUES ('639908285684017152000030003', '63990828568401715200003', '酒柜', null, null, '2018-05-07 10:33:57', null, '2018-05-07 10:33:57');
INSERT INTO `t_product_type` VALUES ('639908285684017152000030004', '63990828568401715200003', '餐边柜', null, null, '2018-05-07 10:34:09', null, '2018-05-07 10:34:09');
INSERT INTO `t_product_type` VALUES ('63990828568401715200004', '-1', '书房家具', null, null, '2018-05-07 10:34:21', null, '2018-05-07 10:34:21');
INSERT INTO `t_product_type` VALUES ('639908285684017152000040001', '63990828568401715200004', '书桌', null, null, '2018-05-07 10:34:34', null, '2018-05-07 10:34:34');
INSERT INTO `t_product_type` VALUES ('639908285684017152000040002', '63990828568401715200004', '书架/书柜', null, null, '2018-05-07 10:34:45', null, '2018-05-07 10:34:45');
INSERT INTO `t_product_type` VALUES ('639908285684017152000040003', '63990828568401715200004', '会议桌', null, null, '2018-05-07 10:34:59', null, '2018-05-07 10:34:59');
INSERT INTO `t_product_type` VALUES ('639908285684017152000040004', '63990828568401715200004', '文件柜', null, null, '2018-05-07 10:35:09', null, '2018-05-07 10:35:09');
INSERT INTO `t_product_type` VALUES ('63990828568401715200005', '-1', '厨房家具', null, null, '2018-05-07 10:35:19', null, '2018-05-07 10:35:19');
INSERT INTO `t_product_type` VALUES ('63990828568401715200006', '-1', '卫生间家具', null, null, '2018-05-07 10:35:39', null, '2018-05-07 10:35:39');
INSERT INTO `t_product_type` VALUES ('63990828568401715200007', '-1', '儿童房家具', null, null, '2018-05-07 10:35:52', null, '2018-05-07 10:35:52');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `desc` varchar(500) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', '打算还几万', '2222', '隅开完票', '2018-05-09', null, null);
INSERT INTO `t_role` VALUES ('2', '用户', '开打时候 ', '哦哈搜房', '佛奥', '2018-05009', null, null);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  `permission_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `t_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `t_role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_sale_order
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_order`;
CREATE TABLE `t_sale_order` (
  `id` varchar(50) NOT NULL,
  `customer_id` varchar(50) DEFAULT NULL,
  `total_amount` decimal(10,0) DEFAULT NULL,
  `discount` decimal(10,0) DEFAULT NULL,
  `free_amount` decimal(10,0) DEFAULT NULL,
  `final_amount` decimal(10,0) DEFAULT NULL,
  `received_amount` decimal(10,0) DEFAULT NULL,
  `status` varchar(500) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `update_time` varchar(255) DEFAULT NULL,
  `update_user` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `inv_status` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sale_order
-- ----------------------------
INSERT INTO `t_sale_order` VALUES ('11231231asa', '6399080216886513664', '21', '10', '1', '20', '10', '1', '2018-05-10', '421412', '421421', '421', null, '0');

-- ----------------------------
-- Table structure for t_sale_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_order_detail`;
CREATE TABLE `t_sale_order_detail` (
  `id` varchar(255) NOT NULL,
  `sale_order_id` varchar(255) DEFAULT NULL,
  `product_sku_code` varchar(255) DEFAULT NULL,
  `product_sku_name` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `product_type_name` varchar(255) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `sub_total` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sale_order_detail
-- ----------------------------
INSERT INTO `t_sale_order_detail` VALUES ('122', '11231231', 'YINGFU001', '舒达', '舒达', '3m*2m', 'dsadsa', '21', '1', '21');
INSERT INTO `t_sale_order_detail` VALUES ('6400551986088906752', '6400551985874997248', 'YINGFU001', '舒达', '舒达', '3m*2m', null, '110', '1', '110');

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
INSERT INTO `t_user` VALUES ('1', 'admin', '123456', '1', '管理员', '安德玛', '2018-05-09', null, null);
INSERT INTO `t_user` VALUES ('2', 'liaoww', '123456', '1', '用户', '叫', '2018-05-09', null, null);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1', '1');
INSERT INTO `t_user_role` VALUES ('2', '2', '2');

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
INSERT INTO `t_vendor` VALUES ('6399080860020117504', '供应商00021', '广东轻微', '21389217398', '胡志军', '1238172193', '21368912982@qq.com', '123898dsw', '反反复复付付付付付付付', null, '2018-05-07 10:22:46', null, '2018-05-07 10:22:46');
INSERT INTO `t_vendor` VALUES ('6399081081357733888', '供应商823', '撒会否还欠我就', '21496298739', '胡志杰', '21837918', '21362897@qq.com', 'KONDOF12', '哈哈哈哈或或或', null, '2018-05-07 10:23:38', null, '2018-05-07 10:23:38');
