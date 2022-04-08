package com.school.mapper;

import com.school.pojo.SchoolPracticeEvaluate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SchoolPracticeEvaluateMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolPracticeEvaluate record);

    SchoolPracticeEvaluate selectByPrimaryKey(String id);

    List<SchoolPracticeEvaluate> selectAll();

    int updateByPrimaryKey(SchoolPracticeEvaluate record);

    SchoolPracticeEvaluate selectOne(String userId);
}