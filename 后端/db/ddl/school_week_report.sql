DROP TABLE IF EXISTS `school_week_report`;
CREATE TABLE `school_week_report` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `user_table_id` varchar(255) DEFAULT NULL COMMENT '用户表id',
  `practice_week` varchar(255) DEFAULT NULL COMMENT '实习周',
  `practice_week_report` text COMMENT '实习周记',
  `audit_teacher_id` varchar(255) DEFAULT NULL COMMENT '审核老师id',
  `week_report_audit` text COMMENT '周记审核',
  `creat_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `creat_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
