package com.school.service;

import com.school.pojo.SchoolPracticeEvaluate;
import com.school.response.WebResponse;

/**
 * @Author mjz
 * @Date 2022/3/27 21:23
 * @Version 1.0
 */
public interface EvaluateService {

    SchoolPracticeEvaluate getEvaluate(String userId);

    WebResponse addEvaluate(SchoolPracticeEvaluate schoolPracticeEvaluate);

    WebResponse updateEvaluate(SchoolPracticeEvaluate schoolPracticeEvaluate);
}
