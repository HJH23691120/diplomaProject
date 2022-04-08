DROP TABLE IF EXISTS `school_practice_evaluate`;
CREATE TABLE `school_practice_evaluate` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `user_table_id` varchar(255) DEFAULT NULL COMMENT '学生id',
  `teacher_id` varchar(255) DEFAULT NULL COMMENT '校内老师id',
  `teacher_appraise` varchar(500) DEFAULT NULL COMMENT '老师评价',
  `teacher_result` varchar(255) DEFAULT NULL,
  `firm_id` varchar(255) DEFAULT NULL COMMENT '企业导师id',
  `firm_result` varchar(255) DEFAULT NULL,
  `attend_qualified` varchar(255) DEFAULT NULL COMMENT '考勤是否合格',
  `work_complete` varchar(255) DEFAULT NULL COMMENT '工作是否完成',
  `firm_comment` varchar(255) DEFAULT NULL COMMENT '企业导师评语',
  `creat_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `creat_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
