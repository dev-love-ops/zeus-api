CREATE TABLE `resource_apply_flow` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `flow_id` varchar(32) NOT NULL DEFAULT '' COMMENT '工单ID',
  `apply_user` varchar(32) NOT NULL DEFAULT '' COMMENT '申请人',
  `verify_user` varchar(32) NOT NULL DEFAULT '' COMMENT '审核人',
  `operate_user` varchar(32) NOT NULL DEFAULT '' COMMENT '处理人',
  `apply_time` datetime NOT NULL COMMENT '申请时间',
  `verify_time` datetime NOT NULL COMMENT '审核时间',
  `close_time` datetime NOT NULL COMMENT '关闭时间',
  `failed_reason` text DEFAULT NULL COMMENT '失败原因',
  `status` int(1) NOT NULL COMMENT '状态',
  `server_data` text DEFAULT NULL COMMENT '申请人申请服务器请求数据',
  PRIMARY KEY (`id`),
  UNIQUE KEY `flow_id` (`flow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;