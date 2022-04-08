package com.school.service;

import com.school.pojo.SchoolPracticeApply;
import com.school.response.WebResponse;

import java.util.List;
import java.util.Map;

/**
 * @Author mjz
 * @Date 2022/3/23 18:54
 * @Version 1.0
 */
public interface PracticeService {

    WebResponse applyPractice(SchoolPracticeApply schoolPracticeApply);

    List<Map<String,String>> getFirmTutro(String userName);

    SchoolPracticeApply getPracticeApply(String userId);

    WebResponse updatePracticeApply(SchoolPracticeApply schoolPracticeApply);
}
