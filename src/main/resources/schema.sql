CREATE TABLE IF NOT EXISTS `user` (
  `id` char(36) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` char(32) NOT NULL,
  `email` varchar(100) NOT NULL,
  `balance` float unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `transaction`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_email` varchar(100) NOT NULL,
  `receiver_email` varchar(100) NOT NULL,
  `amount` float unsigned NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `sender_email` (`sender_email`),
  KEY `transaction_ibfk_1` (`receiver_email`),
  CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`receiver_email`) REFERENCES `user` (`email`),
  CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`sender_email`) REFERENCES `user` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;