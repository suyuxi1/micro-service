/*
 Navicat Premium Data Transfer

 Source Server         : su
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 120.25.149.156:3306
 Source Schema         : content_center

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 27/10/2020 09:59:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mid_user_share
-- ----------------------------
DROP TABLE IF EXISTS `mid_user_share`;
CREATE TABLE `mid_user_share`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `share_id` int NOT NULL COMMENT 'share.id',
  `user_id` int NOT NULL COMMENT 'user.id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_mid_user_share_share1_idx`(`share_id`) USING BTREE,
  INDEX `fk_mid_user_share_user1_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-分享中间表【描述用户购买的分享】' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mid_user_share
-- ----------------------------
INSERT INTO `mid_user_share` VALUES (1, 1, 1);
INSERT INTO `mid_user_share` VALUES (2, 2, 1);
INSERT INTO `mid_user_share` VALUES (3, 3, 1);
INSERT INTO `mid_user_share` VALUES (4, 4, 2);
INSERT INTO `mid_user_share` VALUES (5, 5, 2);
INSERT INTO `mid_user_share` VALUES (6, 6, 2);
INSERT INTO `mid_user_share` VALUES (7, 1, 2);
INSERT INTO `mid_user_share` VALUES (8, 2, 2);
INSERT INTO `mid_user_share` VALUES (9, 3, 2);
INSERT INTO `mid_user_share` VALUES (10, 6, 3);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `show_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示 0:否 1:是',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '微服务', 1, '2020-10-04 20:45:49');

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NOT NULL DEFAULT 0 COMMENT '发布人id',
  `title` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `is_original` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否原创 0:否 1:是',
  `author` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '作者',
  `cover` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '封面',
  `summary` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '概要信息',
  `price` int NOT NULL DEFAULT 0 COMMENT '价格（需要的积分）',
  `download_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '下载地址',
  `buy_count` int NOT NULL DEFAULT 0 COMMENT '下载数 ',
  `show_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示 0:否 1:是',
  `audit_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过',
  `reason` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '审核不通过原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分享表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES (1, 1, '黛西·米勒', '2020-10-18 19:20:16', '2020-10-18 19:20:18', 1, 'su', 'https://img1.doubanio.com/view/subject/s/public/s33689087.jpg', '黛西·米勒简介', 10, 'https://shimo.im/docs/RdjY8hcV3DHJ9dc9', 10, 1, 'PASS', '');
INSERT INTO `share` VALUES (2, 1, '生死有时', '2020-10-18 19:22:15', '2020-10-18 19:22:18', 1, 'su', 'https://img1.doubanio.com/view/subject/s/public/s33702557.jpg', '生死有时简介', 10, 'https://shimo.im/docs/RdjY8hcV3DHJ9dc9', 20, 1, 'PASS', '');
INSERT INTO `share` VALUES (3, 1, '怪奇人物博物馆\r\n', '2020-10-18 19:23:38', '2020-10-18 19:23:40', 1, 'su', 'https://img9.doubanio.com/view/subject/s/public/s33720766.jpg', '怪奇人物博物馆\r\n简介', 10, 'https://shimo.im/docs/RdjY8hcV3DHJ9dc9', 15, 1, 'PASS', '');
INSERT INTO `share` VALUES (4, 1, '艺术三万年\r\n', '2020-10-18 19:23:55', '2020-10-18 19:23:57', 1, 'su', 'https://img9.doubanio.com/view/subject/s/public/s33711305.jpg', '艺术三万年\r\n简介', 10, 'https://shimo.im/docs/RdjY8hcV3DHJ9dc9', 25, 1, 'PASS', '');
INSERT INTO `share` VALUES (5, 1, '庚子故事集\r\n', '2020-10-18 19:24:21', '2020-10-18 19:24:23', 1, 'su', 'https://img3.doubanio.com/view/subject/s/public/s33715422.jpg\r\n', '庚子故事集\r\n简介', 10, 'https://shimo.im/docs/RdjY8hcV3DHJ9dc9', 30, 1, 'PASS', '');
INSERT INTO `share` VALUES (6, 1, '碎片\r\n', '2020-10-18 19:24:47', '2020-10-18 19:24:49', 1, 'su', 'https://img2.doubanio.com/view/subject/s/public/s33734103.jpg', '碎片\r\n简介', 10, 'https://shimo.im/docs/RdjY8hcV3DHJ9dc9', 15, 1, 'PASS', '');
INSERT INTO `share` VALUES (7, 1, 'springboot', '2020-10-18 06:54:48', '2020-10-18 06:54:48', 1, 'su', 'https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg', 'springboot分享', 10, '', 0, 1, 'NOT_YET', '');
INSERT INTO `share` VALUES (8, 1, 'su', '2020-10-18 08:52:04', '2020-10-18 08:52:04', 1, 'su', 'https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg', 'su', 11, 'https://shimo.im/docs/RdjY8hcV3DHJ9dc9', 0, 1, 'NOT_YET', '');

SET FOREIGN_KEY_CHECKS = 1;
