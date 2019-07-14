CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL DEFAULT '' COMMENT '用户名',
  `mobile` varchar(11) NOT NULL DEFAULT '''''' COMMENT '手机号',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_index` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '角色名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `rolename_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT 'user表的ID',
  `role_id` int(11) NOT NULL COMMENT 'role表的ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;