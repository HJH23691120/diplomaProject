package com.school.mapper;

import com.school.pojo.SchoolWeekReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SchoolWeekReportMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolWeekReport record);

    SchoolWeekReport selectByPrimaryKey(String id);

    List<SchoolWeekReport> selectAll();

    int updateByPrimaryKey(SchoolWeekReport record);

    SchoolWeekReport selectOne(String userId, String weekNum);

    int selectWeekNum(String userId);
}