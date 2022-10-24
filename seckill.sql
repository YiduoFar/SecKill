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

 Date: 24/10/2022 21:37:44
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
INSERT INTO `good` VALUES (1, 'HUAWEI mate 50', 'HUAWEI mate 50 128GB', '/img/HUAWEI mate 50.png', '网络类型：4G全网通品牌：Huawei/华为华为型号：MATE 50\r\n售后服务：全国联保接口类型：Type-C分辨率：FHD+ 2700 x 1224像素\r\n蓝牙版本：5.2CPU核心数：八核机身颜色：流光紫 曜金黑 昆仑破晓 昆仑霞光 冰霜银\r\n套餐类型：官方标配后置摄像头：5000万像素超光变摄像头+ 1300万像素超广角摄像头+ 1200万像素潜望式长焦摄像头生产企业：华为终端\r\n存储容量：128GB 256GB 512GB屏幕材质：OLED手机类型：智能手机\r\n摄像头类型：前一后三网络模式：双卡双待屏幕刷新率：90HZ\r\n版本类型：中国大陆操作系统：HarmonyOS耳机插头类型：TYPE-C\r\n屏幕类型：2.5D屏幕款式：直板CPU品牌：高通\r\n上市时间：2022-09屏幕尺寸：6.7英寸电池容量：4460mAh\r\n机身厚度：7.98mmCPU型号：第一代骁龙8+ 4G是否支持无线充电：是\r\n是否支持NFC：是充电功率：66W前置摄像头像素：1300万像素', 5299.00, 108, 40520);
INSERT INTO `good` VALUES (2, 'HUAWEI mate 50 PRO', 'HUAWEI mate 50 PRO 128GB', '/img/HUAWEI mate 50 PRO.png', '网络类型：4G全网通华为型号：Mate 50 PRO品牌：Huawei/华为\r\n售后服务：全国联保接口类型：Type-C分辨率：FHD+ 2616 x 1212 像素\r\n蓝牙版本：5.2CPU核心数：八核机身颜色：曜金黑 流光紫 冰霜银 昆仑破晓 昆仑霞光\r\n套餐类型：官方标配后置摄像头：5000万像素超光变摄像头+ 1300万像素超广角摄像头+ 6400万像素潜望式长焦摄像头生产企业：华为\r\n存储容量：256GB 512GB屏幕材质：OLED手机类型：智能手机\r\n摄像头类型：前双后三网络模式：双卡双待电信设备进网许可证编号：00-D710-208870\r\n屏幕刷新率：120Hz版本类型：中国大陆操作系统：HarmonyOS\r\n耳机插头类型：TYPE-C屏幕类型：3D屏幕款式：直板\r\nCPU品牌：高通上市时间：2022-09屏幕尺寸：6.74英寸\r\n电池容量：4700mAh机身厚度：8.5mmCPU型号：高通骁龙™ 778G 4G\r\n是否支持无线充电：是是否支持NFC：是充电功率：66W\r\n前置摄像头像素：1300万像素3C证书编号：2020011606272300', 6299.00, 132, 50400);
INSERT INTO `good` VALUES (3, 'HUAWEI P50', 'HUAWEI/华为 P50新款智能手机鸿蒙手机直面屏摄影拍照旗舰游戏通话新款华为官方旗舰店非P50pro', '/img/HUAWEI P50.png', '网络类型：4G 全网通华为型号：P50品牌：Huawei/华为\r\n售后服务：全国联保接口类型：Type-C分辨率：FHD+ 2700 x 1224 像素\r\n蓝牙版本：5.2CPU核心数：八核机身颜色：曜金黑 雪域白 可可茶金\r\n套餐类型：官方标配后置摄像头：5000万像素原色摄像头+ 1300万像素超广角摄像头+ 1200万像素长焦摄像头生产企业：华为终端有限公司\r\n存储容量：8+128GB 8+256GB屏幕材质：OLED最大光圈：F1.8\r\n手机类型：智能手机摄像头类型：四摄像头（前一后三）网络模式：双卡双待\r\n电信设备进网许可证编号：02-D710-222168屏幕刷新率：90HZ版本类型：中国大陆\r\n操作系统：HarmonyOS耳机插头类型：TYPE-C屏幕类型：全面屏\r\n款式：直板CPU品牌：高通上市时间：2021-09\r\n屏幕尺寸：6.5英寸电池容量：4100mAh机身厚度：7.92mm\r\nCPU型号：骁龙888 4G解锁方式：屏下指纹是否支持无线充电：否\r\n是否支持NFC：是充电功率：66W前置摄像头像素：1300万像素\r\n3C证书编号：2021011606406275', 4488.00, 3016, 10000);
INSERT INTO `good` VALUES (4, 'asus 华硕天选3', 'asus华硕天选3 12代英特尔酷睿i7/i5游戏本RTX3060独显手提便携笔记本电脑学生高配电竞商务办公15.6英寸', '/img/asus 天选3.png', '品牌：Asus/华硕系列：天选型号：天选3\r\n内存容量：16GB硬盘容量：512G固态硬盘 1TB 固态硬盘CPU：英特尔 酷睿 i7-12700H\r\n售后服务：全国联保屏幕比例：16:9显存容量：6GB\r\n厚度：20.0mm及以上颜色分类：日蚀灰RTX3050/i5-12500H/144Hz高色域屏 魔幻青RTX3050Ti/i5-12500H/144Hz高色域屏 【热卖推荐】日蚀灰RTX3060/i7-12700H/165Hz 2.5K屏 【热卖推荐】魔幻青RTX3060/i7-12700H/165Hz 2.5K屏 日蚀灰 RTX3070/i7-12700H/165Hz 2.5K屏 天选3Plus RTX3060/i7-12700H/360Hz高色域屏/灰 天选3Plus RTX3070/i7-12700H/360Hz高色域屏/灰 日蚀灰RTX3050/i7-12700H/144Hz高色域屏 魔幻青RTX3050Ti/i7-12700H/144Hz高色域屏商品条形码：0000000000000\r\n套餐类型：官方标配 官方标配+149元购TUF电竞套装 官方标配+189元购天选游戏鼠标/月耀白生产企业：华硕股份有限公司固态硬盘：512GB\r\n能效等级：无笔记本电脑类型：游戏本显卡类型：NVIDIA GeForce RTX3060\r\n操作系统：Windows 11适用场景：学生 家庭影音 家庭娱乐 高清游戏 尊贵旗舰 设计制图通信技术类型：蓝牙 无线网卡\r\n屏幕类型：FHD背光，防炫目屏CPU品牌：英特尔屏幕分辨率：1920x1080像素\r\n屏幕尺寸：15.6英寸色域：DCI-P3≥97%屏幕刷新率：165Hz\r\n保修期：12个月3C证书编号：2018010902053307', 7599.00, 208, 10000);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `good_id` bigint NULL DEFAULT NULL COMMENT '商品id',
  `delivery_addr_id` bigint NULL DEFAULT NULL COMMENT '收货地址id',
  `good_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '冗余过来的商品名称',
  `good_count` int NULL DEFAULT NULL COMMENT '商品数量',
  `good_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品单价',
  `order_channel` tinyint NULL DEFAULT NULL COMMENT '1pc,2android,3ios',
  `order_status` tinyint NULL DEFAULT NULL COMMENT '订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
  `pay_date` timestamp NULL DEFAULT NULL COMMENT '订单支付时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (3, 1, 1, 0, 'HUAWEI mate 50', 1, 529.00, 1, 0, '2022-10-24 21:25:27', NULL);
INSERT INTO `orders` VALUES (6, 2, 1, 0, 'HUAWEI mate 50', 1, 529.00, 1, 0, '2022-10-24 21:33:40', NULL);

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
INSERT INTO `seckill_good` VALUES (1, 1, 'HUAWEI mate 50', 529.00, 6, '2022-11-01 08:00:00', '2022-11-01 09:00:00');
INSERT INTO `seckill_good` VALUES (2, 2, 'HUAWEI mate 50 PRO', 629.00, 10, '2022-10-21 14:00:00', '2022-10-24 15:00:00');
INSERT INTO `seckill_good` VALUES (3, 3, 'HUAWEI P50', 448.00, 20, '2022-10-19 21:26:42', '2022-10-19 22:26:47');
INSERT INTO `seckill_good` VALUES (4, 4, 'asus 华硕天选3', 759.00, 8, '2022-10-18 21:44:14', '2022-10-18 21:44:17');

-- ----------------------------
-- Table structure for seckill_order
-- ----------------------------
DROP TABLE IF EXISTS `seckill_order`;
CREATE TABLE `seckill_order`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '秒杀订单id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `order_id` bigint NULL DEFAULT NULL COMMENT '订单id',
  `good_id` bigint NULL DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seckill_order
-- ----------------------------
INSERT INTO `seckill_order` VALUES (1, 1, 3, 1);
INSERT INTO `seckill_order` VALUES (1584538617903181826, 2, 6, 1);

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
