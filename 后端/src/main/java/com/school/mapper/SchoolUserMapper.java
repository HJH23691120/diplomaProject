package com.school.mapper;

import com.school.pojo.SchoolUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface SchoolUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SchoolUser record);

    SchoolUser selectByPrimaryKey(String id);

    List<SchoolUser> selectAll(String userClass, String userRole,String userId);

    List<SchoolUser> selectListByFirmTutor(String userRole,String userName);

    int updateByPrimaryKey(SchoolUser record);

    SchoolUser selectOne(String userId);

    List<Map<String,String>> getFirmTutro(String userName);

    SchoolUser selectOneByUsetId(String userId);

    int updatePwd(String userId, String newPwd,Date updateDate);

}