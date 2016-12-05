/*
SQLyog v10.2 
MySQL - 5.1.73-community : Database - combination
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`combination` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `combination`;

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` varchar(36) DEFAULT NULL,
  `permission_name` varchar(30) DEFAULT NULL,
  `permission_desc` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_permission` */

insert  into `t_permission`(`id`,`permission_name`,`permission_desc`) values ('1','user:add','用户添加'),('2','user:delete','用户删除'),('3','user:update','用户修改'),('4','user:get','用户查询');

/*Table structure for table `t_query_table` */

DROP TABLE IF EXISTS `t_query_table`;

CREATE TABLE `t_query_table` (
  `id` varchar(36) NOT NULL COMMENT 'ID',
  `t_table_name` varchar(50) DEFAULT NULL COMMENT '表明',
  `t_role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `conditions` varchar(500) DEFAULT NULL COMMENT '查询条件',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_query_table` */

insert  into `t_query_table`(`id`,`t_table_name`,`t_role_name`,`conditions`,`priority`) values ('1','t_user','login','username=#{username}',0),('2','t_user','admin','1=1',2);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` varchar(36) NOT NULL,
  `role_name` varchar(36) DEFAULT NULL,
  `role_desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`role_name`,`role_desc`) values ('1','admin','管理员'),('2','login','普通用户');

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `id` varchar(36) DEFAULT NULL,
  `role_id` varbinary(36) DEFAULT NULL,
  `permission_id` varchar(36) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_permission` */

insert  into `t_role_permission`(`id`,`role_id`,`permission_id`) values ('1','1','1'),('2','1','2'),('3','1','3'),('4','1','4'),('5','2','3'),('6','2','4');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`) values ('1','admin','21232f297a57a5a743894a0e4a801fc3'),('2','test','098f6bcd4621d373cade4e832627b4f6'),('3','3','3'),('4','4','4'),('5','5','5'),('6','6','6');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` varchar(36) NOT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`id`,`role_id`,`user_id`) values ('1','1','1'),('2','2','1'),('3','2','2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
