DROP TABLE IF EXISTS `devices`;
DROP TABLE IF EXISTS `gateways`;

CREATE TABLE `gateways` (
  `serial_number` varchar(255) NOT NULL,
  `ip_address` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`serial_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `devices` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime NOT NULL,
  `status` varchar(255) NOT NULL,
  `vendor` varchar(255) NOT NULL,
  `fk_gateway` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfl8u6mqont2g6kmem9hr9vl7b` (`fk_gateway`),
  CONSTRAINT `FKfl8u6mqont2g6kmem9hr9vl7b` FOREIGN KEY (`fk_gateway`) REFERENCES `gateways` (`serial_number`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

