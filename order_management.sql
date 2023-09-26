/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.32 : Database - order_management
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`order_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `order_management`;

/*Table structure for table `t_commodity` */

DROP TABLE IF EXISTS `t_commodity`;

CREATE TABLE `t_commodity` (
  `c_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_commodity` */

insert  into `t_commodity`(`c_id`,`name`,`price`) values (1,'小米8',4999),(2,'锤子',66),(3,'阿迪',2000),(4,'老村长',88),(5,'劲酒',35),(6,'小熊饼干',3.5),(7,'娃哈哈',10),(8,'卫龙辣条',3),(9,'干脆面',0.5);

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `com_id` int DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `count` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `com_id` (`com_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`com_id`) REFERENCES `t_commodity` (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`com_id`,`time`,`count`,`price`) values (1,3,'2023-09-18 00:00:00',1,2000),(5,2,'2023-09-18 00:00:00',3,198);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
