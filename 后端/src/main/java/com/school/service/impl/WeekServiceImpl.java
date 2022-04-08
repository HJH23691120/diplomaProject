package com.school.service.impl;

import com.school.mapper.SchoolUserMapper;
import com.school.mapper.SchoolWeekReportMapper;
import com.school.pojo.SchoolUser;
import com.school.pojo.SchoolWeekReport;
import com.school.response.WebResponse;
import com.school.service.WeekService;
import com.school.util.UidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Author mjz
 * @Date 2022/3/27 20:23
 * @Version 1.0
 */
@Service
public class WeekServiceImpl implements WeekService {

    @Autowired
    SchoolWeekReportMapper schoolWeekReportMapper;
    @Autowired
    SchoolUserMapper schoolUserMapper;

    @Override
    public WebResponse addWeek(SchoolWeekReport schoolWeekReport) {
        if(StringUtils.isEmpty(schoolWeekReport.getId())){
            schoolWeekReport.setId(UidUtil.genCodes("",15));
            schoolWeekReport.setCreatDate(new Date());
            schoolWeekReport.setUpdateDate(new Date());
        }
        int i = schoolWeekReportMapper.insert(schoolWeekReport);
        if(i<1){
            return WebResponse.error(-1,"新增周记失败");
        }
        return WebResponse.success(schoolWeekReport);
    }

    @Override
    public WebResponse updateWeek(SchoolWeekReport schoolWeekReport) {
        schoolWeekReport.setUpdateDate(new Date());
        int i = schoolWeekReportMapper.updateByPrimaryKey(schoolWeekReport);
        if(i<1){
            return WebResponse.error(-1,"修改失败");
        }
        return WebResponse.success(schoolWeekReport);
    }

    @Override
    public SchoolWeekReport getWeek(String userId, String weekNum) {
        SchoolWeekReport schoolWeekReport = schoolWeekReportMapper.selectOne(userId, weekNum);
        if(!ObjectUtils.isEmpty(schoolWeekReport)&&!StringUtils.isEmpty(schoolWeekReport.getAuditTeacherId())) {
            //转换审核老师名称
            SchoolUser schoolUser = schoolUserMapper.selectOne(schoolWeekReport.getAuditTeacherId());
            schoolWeekReport.setAuditTeacherId(schoolUser.getUserName());
        }
        return schoolWeekReport;
    }

    @Override
    public WebResponse getWeekNum(String userId) {
        int i = schoolWeekReportMapper.selectWeekNum(userId);
        if(i<8){
            return WebResponse.error(-1,"学生周记篇数不足8篇，请联系学生补足周记");
        }
        return WebResponse.success(i);
    }
}
