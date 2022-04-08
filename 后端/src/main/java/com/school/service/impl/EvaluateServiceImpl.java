package com.school.service.impl;

import com.school.mapper.SchoolPracticeEvaluateMapper;
import com.school.mapper.SchoolUserMapper;
import com.school.pojo.SchoolPracticeEvaluate;
import com.school.pojo.SchoolUser;
import com.school.response.WebResponse;
import com.school.service.EvaluateService;
import com.school.util.UidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @Author mjz
 * @Date 2022/3/27 21:23
 * @Version 1.0
 */
@Service
public class EvaluateServiceImpl implements EvaluateService {
    @Autowired
    SchoolPracticeEvaluateMapper schoolPracticeEvaluateMapper;
    @Autowired
    SchoolUserMapper schoolUserMapper;
    @Override
    public SchoolPracticeEvaluate getEvaluate(String userId) {
        SchoolPracticeEvaluate schoolPracticeEvaluate = schoolPracticeEvaluateMapper.selectOne(userId);
        if(!ObjectUtils.isEmpty(schoolPracticeEvaluate)) {
            //转换校内老id为名称
            String teacherId = schoolPracticeEvaluate.getTeacherId();
            if (!StringUtils.isEmpty(teacherId)) {
                SchoolUser schoolUser = schoolUserMapper.selectOne(teacherId);
                schoolPracticeEvaluate.setTeacherId(schoolUser.getUserName());
            }
            //转换企业导师id为名称
            String firmId = schoolPracticeEvaluate.getFirmId();
            if (!StringUtils.isEmpty(schoolPracticeEvaluate.getFirmId())) {
                SchoolUser schoolUser = schoolUserMapper.selectOne(firmId);
                schoolPracticeEvaluate.setFirmId(schoolUser.getUserName());
            }
        }
        return schoolPracticeEvaluate;
    }

    @Override
    public WebResponse addEvaluate(SchoolPracticeEvaluate schoolPracticeEvaluate) {
        if(StringUtils.isEmpty(schoolPracticeEvaluate.getId())){
            schoolPracticeEvaluate.setId(UidUtil.genCodes("",15));
            schoolPracticeEvaluate.setCreatDate(new Date());
            schoolPracticeEvaluate.setUpdateDate(new Date());
        }
        int i = schoolPracticeEvaluateMapper.insert(schoolPracticeEvaluate);
        if(i<1){
            return WebResponse.error(-1,"企业导师对学生评价失败");
        }
        return WebResponse.success(schoolPracticeEvaluate);
    }

    @Override
    public WebResponse updateEvaluate(SchoolPracticeEvaluate schoolPracticeEvaluate) {
        schoolPracticeEvaluate.setUpdateDate(new Date());
        int i = schoolPracticeEvaluateMapper.updateByPrimaryKey(schoolPracticeEvaluate);
        if(i<1){
            return WebResponse.error(-1,"校内老师对写生评价失败");
        }
        return WebResponse.success(schoolPracticeEvaluate);
    }
}
