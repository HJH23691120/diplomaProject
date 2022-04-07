package com.school.mapper;

import com.school.pojo.SchoolRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SchoolRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolRole record);

    SchoolRole selectByPrimaryKey(String id);

    List<SchoolRole> selectAll();

    int updateByPrimaryKey(SchoolRole record);
}