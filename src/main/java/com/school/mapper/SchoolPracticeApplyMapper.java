package com.school.mapper;

import com.school.pojo.SchoolPracticeApply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SchoolPracticeApplyMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolPracticeApply record);

    SchoolPracticeApply selectByPrimaryKey(String id);

    List<SchoolPracticeApply> selectAll();

    int updateByPrimaryKey(SchoolPracticeApply record);

    SchoolPracticeApply selectOne(String userId);
}