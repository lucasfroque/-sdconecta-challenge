CREATE TABLE `tb_user` (
  `id` BIGINT AUTO_INCREMENT,
  `authorization_status` VARCHAR(255),
  `email` VARCHAR(255) NOT NULL,
  `mobile_phone` VARCHAR(255),
  `name` VARCHAR(255),
  `password` VARCHAR(255) NOT NULL,
  `roles` VARCHAR(255),
  `surname` VARCHAR(255),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;