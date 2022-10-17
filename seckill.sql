/*
 Navicat Premium Data Transfer

 Source Server         : Windows 10
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : seckill

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 18/10/2022 02:00:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good`  (
  `id` bigint NOT NULL COMMENT 'id',
  `good_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名',
  `good_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品标题',
  `good_img` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品图片',
  `good_detail` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '详情',
  `good_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `good_stock` int NULL DEFAULT NULL COMMENT '库存，-1表示无限制',
  `good_pay_num` int NULL DEFAULT NULL COMMENT '付款人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES (1, 'HUAWEI mate 50', 'HUAWEI mate 50 128GB', '/img/HUAWEI mate 50.png', 'HUAWEI mate 50', 5299.00, 100, 40520);
INSERT INTO `good` VALUES (2, 'HUAWEI mate 50 PRO', 'HUAWEI mate 50 PRO 128GB', '/img/HUAWEI mate 50 PRO.png', 'HUAWEI mate 50 PRO', 6299.00, 100, 50400);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` bigint NOT NULL COMMENT 'id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `good_id` bigint NULL DEFAULT NULL COMMENT '商品id',
  `delivery_addr_id` bigint NULL DEFAULT NULL COMMENT '收货地址id',
  `good_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '冗余过来的商品名称',
  `good_count` int NULL DEFAULT NULL COMMENT '商品数量',
  `good_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `order_channel` tinyint NULL DEFAULT NULL COMMENT '1pc,2android,3ios',
  `status` tinyint NULL DEFAULT NULL COMMENT '订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
  `pay_date` timestamp NULL DEFAULT NULL COMMENT '订单支付时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for seckill_good
-- ----------------------------
DROP TABLE IF EXISTS `seckill_good`;
CREATE TABLE `seckill_good`  (
  `id` bigint NOT NULL COMMENT '秒杀商品id',
  `good_id` bigint NULL DEFAULT NULL COMMENT '商品id',
  `good_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名（冗余）',
  `seckill_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '秒杀价格',
  `stock_count` int NULL DEFAULT NULL COMMENT '库存数量',
  `start_date` datetime NULL DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` datetime NULL DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seckill_good
-- ----------------------------
INSERT INTO `seckill_good` VALUES (1, 1, 'HUAWEI mate 50', 529.00, 10, '2022-11-01 08:00:00', '2022-11-01 09:00:00');
INSERT INTO `seckill_good` VALUES (2, 2, 'HUAWEI mate 50 PRO', 629.00, 10, '2022-11-01 08:00:00', '2022-11-01 09:00:00');

-- ----------------------------
-- Table structure for seckill_order
-- ----------------------------
DROP TABLE IF EXISTS `seckill_order`;
CREATE TABLE `seckill_order`  (
  `id` bigint NOT NULL COMMENT '秒杀订单id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `order_id` bigint NULL DEFAULT NULL COMMENT '订单id',
  `good_id` bigint NULL DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seckill_order
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL COMMENT '用户ID, 手机号码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'MD5(MD5(pass明文+固定salt)+salt)',
  `salt` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `head` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `register_date` timestamp NULL DEFAULT NULL COMMENT '注册时间',
  `last_login_date` timestamp NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `login_count` int NULL DEFAULT 0 COMMENT '登录次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (13042001124, 'user_13042001124', '$2a$10$CUofHdgQVNzvRmK8mGc4CufpH6nOA1YdabL/N1SWrOCcUF1giFarW', NULL, NULL, NULL, NULL, 0);
INSERT INTO `user` VALUES (13168162412, 'yiduofar', '$2a$10$MnAmRa7bH34yonCybzoBbupibfoKO3dqWRyYNGP6v6w3YrXGqGJPu', '1a2b3c4d', NULL, '2022-10-02 17:05:56', '2022-10-02 17:05:58', 0);
INSERT INTO `user` VALUES (18907998754, 'user_18907998754', '$2a$10$PrWHJqn74SClCAsQfLxpkOl8JAxc/Y/G5L1PFfA.aSE9KJnFFyxlW', NULL, NULL, NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
