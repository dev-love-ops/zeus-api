CREATE TABLE `system_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL DEFAULT '' COMMENT '用户名',
  `user_id` varchar(16) NOT NULL DEFAULT '' COMMENT '用户ID',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(11) NOT NULL DEFAULT '' COMMENT '邮箱',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
