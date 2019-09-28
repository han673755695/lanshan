/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : lanshan

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2019-09-28 14:34:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` varchar(50) NOT NULL COMMENT '主键id',
  `parent_id` varchar(50) NOT NULL COMMENT '父id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `status` varchar(2) DEFAULT '1' COMMENT '是否可用(1:可用,2:不可用)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `menu_type` varchar(1) DEFAULT NULL COMMENT '菜单类型(1,菜单,2按钮)',
  `url` varchar(50) DEFAULT NULL COMMENT '连接地址',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `active` varchar(50) DEFAULT NULL COMMENT '是否可用(1:可用,2:不可用)',
  `level` varchar(50) DEFAULT NULL COMMENT '菜单等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1187e3d7df0d4b9986c4eb4c2c285943', 'ad820fc16cdf41c2a550fb0e4ce7e1ce', '2019-09-28 04:46:50', '2019-09-28 04:46:50', '查看', '1', '3', '2', '/s/user/look', '', '1', '3');
INSERT INTO `t_menu` VALUES ('380c5308239d47bc8054e421d72c9025', 'ad820fc16cdf41c2a550fb0e4ce7e1ce', '2019-09-28 06:12:31', '2019-09-28 06:12:31', '导出用户', '1', '4', '1', '/s/user/list/export', '', '1', '3');
INSERT INTO `t_menu` VALUES ('4916c5eaa6854edfa676c63e2b4d425b', '-1', '2019-09-09 20:10:37', '2019-09-09 20:10:39', '系统管理', '1', '1', '1', null, '&#xe6ae;', '1', '1');
INSERT INTO `t_menu` VALUES ('55e4d423bea044e982f90f5d8edd5c22', 'bc9cdee2cce142d69715437892d7e5c4', '2019-09-28 05:01:17', '2019-09-28 05:01:17', '列表', '1', '4', '1', '/s/role/list', '', '1', '3');
INSERT INTO `t_menu` VALUES ('5fd252e7942f4ad9a4c82f0e1d25f9ae', 'bf099fc1d9c54a9fbe825eb7697e134f', '2019-09-27 08:50:33', '2019-09-27 08:50:33', '编辑菜单', '1', '1', '2', '/s/menu/update', '&#xe6a7;', '1', '3');
INSERT INTO `t_menu` VALUES ('768bfcd88560419092d9a0b7d3156047', 'bf099fc1d9c54a9fbe825eb7697e134f', '2019-09-28 04:45:47', '2019-09-28 04:45:47', '查看菜单', '1', '3', '2', '/s/menu/look', '', '1', '3');
INSERT INTO `t_menu` VALUES ('8037b3c3fe2149d0af76018c0cd00e7d', '-1', '2019-09-09 20:01:34', '2019-09-09 20:01:37', '基础权限', '1', '1000', '2', null, null, '1', '1');
INSERT INTO `t_menu` VALUES ('a4c22760e6ef42ec827d78992dc3357d', 'bf099fc1d9c54a9fbe825eb7697e134f', '2019-09-27 08:51:46', '2019-09-27 08:51:46', '删除菜单', '1', '2', '2', '/s/menu/delete', '&#xe6a7;', '1', '3');
INSERT INTO `t_menu` VALUES ('ad820fc16cdf41c2a550fb0e4ce7e1ce', '4916c5eaa6854edfa676c63e2b4d425b', '2019-09-09 20:09:46', '2019-09-09 20:09:44', '用户管理', '1', '1', '1', '/s/user/list', '&#xe6a7;', '1', '2');
INSERT INTO `t_menu` VALUES ('adf9456db28549189c4e7afac7e90a37', 'ad820fc16cdf41c2a550fb0e4ce7e1ce', '2019-09-09 20:19:35', '2019-09-09 20:19:37', '删除用户', '1', '2', '2', '/s/user/delete', null, '1', '3');
INSERT INTO `t_menu` VALUES ('b345fe33752d4c1f82d9f908e9cdb6fa', 'ad820fc16cdf41c2a550fb0e4ce7e1ce', '2019-09-09 20:15:52', '2019-09-09 20:15:47', '编辑用户', '1', '1', '2', '/s/user/update', null, '1', '3');
INSERT INTO `t_menu` VALUES ('b5f00d23b9a6459b9898a76c0f1ff507', 'bc9cdee2cce142d69715437892d7e5c4', '2019-09-28 04:47:46', '2019-09-28 04:47:46', '编辑', '1', '1', '2', '/s/role/update', '', '1', '3');
INSERT INTO `t_menu` VALUES ('bc9cdee2cce142d69715437892d7e5c4', '4916c5eaa6854edfa676c63e2b4d425b', '2019-09-27 09:01:56', '2019-09-27 09:01:56', '角色管理', '1', '3', '1', '/s/role/list', '&#xe6a7;', '1', '2');
INSERT INTO `t_menu` VALUES ('bd263492b12a4eec99b93cc244e7516b', '8037b3c3fe2149d0af76018c0cd00e7d', '2019-09-09 20:04:34', '2019-09-09 20:04:36', '进入欢迎页', '1', '1', '2', '/s/system/login/toWelcome', null, '1', '2');
INSERT INTO `t_menu` VALUES ('bf099fc1d9c54a9fbe825eb7697e134f', '4916c5eaa6854edfa676c63e2b4d425b', '2019-09-28 05:04:24', '2019-09-28 05:04:24', '菜单管理', '1', '3', '1', '/s/menu/list', '&#xe6a7;', '1', '2');
INSERT INTO `t_menu` VALUES ('c18f523f52dd49ebb264e71742a946ca', 'bf099fc1d9c54a9fbe825eb7697e134f', '2019-09-28 05:01:51', '2019-09-28 05:01:51', '菜单列表', '1', '4', '2', '/s/menu/list', '', '1', '3');
INSERT INTO `t_menu` VALUES ('c718566d6d4248df87ec42c118dd9e7b', 'bc9cdee2cce142d69715437892d7e5c4', '2019-09-28 04:48:30', '2019-09-28 04:48:30', '删除', '1', '2', '1', '/s/role/delete', '', '1', '3');
INSERT INTO `t_menu` VALUES ('edd5e517afef4b4fa9d1cd3897ec58f6', 'ad820fc16cdf41c2a550fb0e4ce7e1ce', '2019-09-28 05:00:30', '2019-09-28 05:00:30', '列表', '1', '4', '2', '/s/user/list', '', '1', '3');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(255) NOT NULL COMMENT '主键id',
  `roleName` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `bewrite` varchar(255) DEFAULT NULL COMMENT '描述',
  `createUser` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateUser` varchar(255) DEFAULT NULL COMMENT '修改人',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `active` varchar(255) DEFAULT NULL COMMENT '是否可用(1:可用,2:不可用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('0b23f6c95ed443a294b787187600d0d7', '管理员', '管理员', '879ad685385d4d949d31e5a963957bf4', '2019-09-27 11:01:57', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:48', '1');
INSERT INTO `t_role` VALUES ('8a53b373006f41d58ed6c8503ac49be5', '普通用户', '普通用户', '879ad685385d4d949d31e5a963957bf4', '2019-09-27 14:51:50', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `id` varchar(255) NOT NULL COMMENT '主键id',
  `roleId` varchar(255) DEFAULT NULL COMMENT '角色id',
  `menuId` varchar(255) DEFAULT NULL COMMENT '菜单id',
  `createUser` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateUser` varchar(255) DEFAULT NULL COMMENT '修改人',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `active` varchar(255) DEFAULT NULL COMMENT '是否可用(1:可用,2:不可用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('0117d256d9024cc9bddc7540c481bf80', '8a53b373006f41d58ed6c8503ac49be5', '55e4d423bea044e982f90f5d8edd5c22', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('066a2e2de0664df7883db67d394388c1', '0b23f6c95ed443a294b787187600d0d7', 'edd5e517afef4b4fa9d1cd3897ec58f6', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('070283dcf05d4d99992100c44a8dd44f', '8a53b373006f41d58ed6c8503ac49be5', 'bd263492b12a4eec99b93cc244e7516b', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('0dd1b70e87604323aff8bc60678814c2', '0b23f6c95ed443a294b787187600d0d7', 'c18f523f52dd49ebb264e71742a946ca', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('1b85b2d4c64c476ea4f37fc40091205b', '8a53b373006f41d58ed6c8503ac49be5', 'ad820fc16cdf41c2a550fb0e4ce7e1ce', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('1ff89a0dbd0a4af0a5a8f14a7bbbddc7', '0b23f6c95ed443a294b787187600d0d7', '768bfcd88560419092d9a0b7d3156047', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('2032ca696dec4684ba5d8767616ac93c', '0b23f6c95ed443a294b787187600d0d7', '380c5308239d47bc8054e421d72c9025', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('24e0d299ff1a4afa97cc39b860aeb328', '0b23f6c95ed443a294b787187600d0d7', '5fd252e7942f4ad9a4c82f0e1d25f9ae', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('25c1c9a70fc14e91a66ea056d5b98f16', '0b23f6c95ed443a294b787187600d0d7', 'b5f00d23b9a6459b9898a76c0f1ff507', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('2aaf6f0953c344f7a92f2cb9503e3c41', '0b23f6c95ed443a294b787187600d0d7', '55e4d423bea044e982f90f5d8edd5c22', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('3906a2ddc82740f3b05df83fe7325a8c', '0b23f6c95ed443a294b787187600d0d7', 'c718566d6d4248df87ec42c118dd9e7b', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('3b9dd037cbda4537b9017fb8b4f5a92e', '0b23f6c95ed443a294b787187600d0d7', 'ad820fc16cdf41c2a550fb0e4ce7e1ce', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('3bcf53b84a864d128c4a2915c00a25bc', '0b23f6c95ed443a294b787187600d0d7', 'adf9456db28549189c4e7afac7e90a37', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('3f342bb9ef8343f38e9271ac466ffb6c', '8a53b373006f41d58ed6c8503ac49be5', 'b345fe33752d4c1f82d9f908e9cdb6fa', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('3f5afe8f8bfa4861b7ff17ebe09fa810', '8a53b373006f41d58ed6c8503ac49be5', 'a4c22760e6ef42ec827d78992dc3357d', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('4a4265db90a04af7b453f361ee760df0', '0b23f6c95ed443a294b787187600d0d7', 'bc9cdee2cce142d69715437892d7e5c4', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('549d139b6ca244a2b1e44153ee8a1c37', '8a53b373006f41d58ed6c8503ac49be5', 'c18f523f52dd49ebb264e71742a946ca', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('60280b225d54476ab6265a611ff05d89', '8a53b373006f41d58ed6c8503ac49be5', 'adf9456db28549189c4e7afac7e90a37', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('60aa0cff2451455e8fe1eccad75a4966', '0b23f6c95ed443a294b787187600d0d7', '1187e3d7df0d4b9986c4eb4c2c285943', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('75d2680ec18f499ea0d42e3b8ae1c88c', '8a53b373006f41d58ed6c8503ac49be5', '5fd252e7942f4ad9a4c82f0e1d25f9ae', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('80bd807216074594b432f07988623d65', '8a53b373006f41d58ed6c8503ac49be5', 'bf099fc1d9c54a9fbe825eb7697e134f', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('9165960fdb2f4cb8959a6b80803b0072', '0b23f6c95ed443a294b787187600d0d7', 'a4c22760e6ef42ec827d78992dc3357d', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('96ea0168eb6b4ebdbc510b8856fdc25a', '0b23f6c95ed443a294b787187600d0d7', 'bf099fc1d9c54a9fbe825eb7697e134f', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('bdea039177dc4590a3f13c07537800f1', '8a53b373006f41d58ed6c8503ac49be5', 'bc9cdee2cce142d69715437892d7e5c4', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('d031482c13244eaf8dc561842896f110', '0b23f6c95ed443a294b787187600d0d7', 'b345fe33752d4c1f82d9f908e9cdb6fa', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('d964480425cb4de3827cf902ef0db613', '0b23f6c95ed443a294b787187600d0d7', '8037b3c3fe2149d0af76018c0cd00e7d', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('daf054e52fb34f85950ceee4c66ae777', '8a53b373006f41d58ed6c8503ac49be5', '8037b3c3fe2149d0af76018c0cd00e7d', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('e79e5f85b2d8431ea41eab4ff797d644', '0b23f6c95ed443a294b787187600d0d7', '4916c5eaa6854edfa676c63e2b4d425b', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');
INSERT INTO `t_role_menu` VALUES ('f1fe6322a8214a90976589842ca3dd42', '8a53b373006f41d58ed6c8503ac49be5', '4916c5eaa6854edfa676c63e2b4d425b', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 05:12:10', '1');
INSERT INTO `t_role_menu` VALUES ('fdc882f860474aaa935cce5c48ad4676', '0b23f6c95ed443a294b787187600d0d7', 'bd263492b12a4eec99b93cc244e7516b', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 06:19:49', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(50) NOT NULL DEFAULT '' COMMENT '主键id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `hobby` varchar(50) DEFAULT NULL COMMENT '爱好',
  `status` varchar(50) DEFAULT '1' COMMENT '是否可用(1:可用,2:不可用)',
  `account` varchar(50) DEFAULT NULL COMMENT '账号',
  `userType` varchar(50) DEFAULT NULL COMMENT '用户类型(0:超级管理员;1:管理员;2:普通用户)',
  `sex` varchar(50) DEFAULT NULL COMMENT '性别',
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('326294cc8c824dcc82e41170355e064d', '张三', '23', '202cb962ac59075b964b07152d234b70', '123456@qq.com', '2019-09-01 22:15:26', '2019-09-01 23:25:02', '玩游戏', '1', 'zhangsan', '2', '男', null, null, null);
INSERT INTO `t_user` VALUES ('4bd9dbe9dec848ff929509c155102423', '阿斯弗', '55', null, 'asfdas@qq.com', null, null, '的v啊', '2', null, '2', null, null, null, null);
INSERT INTO `t_user` VALUES ('879ad685385d4d949d31e5a963957bf4', 'admin', '23', '202cb962ac59075b964b07152d234b70', 'han673755695@163.com', '2019-05-11 17:14:03', '2019-06-06 22:06:20', 'playgame', '1', 'admin', '0', '男', null, null, null);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` varchar(255) NOT NULL COMMENT '主键id',
  `userId` varchar(255) DEFAULT NULL COMMENT '用户id',
  `roleId` varchar(255) DEFAULT NULL COMMENT '角色id',
  `createUser` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateUser` varchar(255) DEFAULT NULL COMMENT '修改人',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `active` varchar(255) DEFAULT NULL COMMENT '是否可用(1:可用,2:不可用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('4949ab1fde7a4f61ac86f0061c7a6245', '326294cc8c824dcc82e41170355e064d', '8a53b373006f41d58ed6c8503ac49be5', '879ad685385d4d949d31e5a963957bf4', '2019-09-28 04:40:39', null, null, '1');
INSERT INTO `t_user_role` VALUES ('c36c2d72f5274b0ca3a68e412165efba', '879ad685385d4d949d31e5a963957bf4', '0b23f6c95ed443a294b787187600d0d7', '879ad685385d4d949d31e5a963957bf4', '2019-09-27 14:14:18', null, null, '1');
