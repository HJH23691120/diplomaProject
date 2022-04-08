package com.school.service.impl;

import com.school.mapper.SchoolPracticeApplyMapper;
import com.school.mapper.SchoolUserMapper;
import com.school.pojo.SchoolPracticeApply;
import com.school.pojo.SchoolUser;
import com.school.response.WebResponse;
import com.school.service.PracticeService;
import com.school.util.UidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author mjz
 * @Date 2022/3/23 18:54
 * @Version 1.0
 */
@Service
public class PracticeServiceImpl implements PracticeService {
    @Autowired
    SchoolPracticeApplyMapper schoolPracticeApplyMapper;
    @Autowired
    SchoolUserMapper schoolUserMapper;

    @Override
    public WebResponse applyPractice(SchoolPracticeApply schoolPracticeApply) {
        if(StringUtils.isEmpty(schoolPracticeApply.getId())) {
            schoolPracticeApply.setId(UidUtil.genCodes("",15));
            schoolPracticeApply.setCreatDate(new Date());
            schoolPracticeApply.setUpdateDate(new Date());
        }
        int insert = schoolPracticeApplyMapper.insert(schoolPracticeApply);
        if(insert<1){
            return WebResponse.error(-1,"提交实习申请失败");
        }
        return WebResponse.success(schoolPracticeApply);
    }

    @Override
    public List<Map<String, String>> getFirmTutro(String userName) {
        List<Map<String, String>> firmTutro = schoolUserMapper.getFirmTutro(userName);
        return firmTutro;
    }

    @Override
    public SchoolPracticeApply getPracticeApply(String userId) {
        SchoolPracticeApply schoolPracticeApply = schoolPracticeApplyMapper.selectOne(userId);
        return schoolPracticeApply;
    }

    @Override
    public WebResponse updatePracticeApply(SchoolPracticeApply schoolPracticeApply) {
        schoolPracticeApply.setUpdateDate(new Date());
        int i = schoolPracticeApplyMapper.updateByPrimaryKey(schoolPracticeApply);
        if(i<1){
            return WebResponse.error(-1,"修改实习申请失败");
        }
        return WebResponse.success(schoolPracticeApply);
    }
}
