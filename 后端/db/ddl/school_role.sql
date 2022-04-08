DROP TABLE IF EXISTS `school_role`;
CREATE TABLE `school_role` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `role_level` varchar(255) DEFAULT NULL COMMENT '角色等级',
  `creat_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `creat_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
