package com.school.controller;

import com.school.pojo.SchoolPracticeEvaluate;
import com.school.response.WebResponse;
import com.school.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author mjz
 * @Date 2022/3/27 21:20
 * @Version 1.0
 */
@RestController
public class EvaluateController {
    @Autowired
    EvaluateService evaluateService;

    /**
     * 查询实习评价/成绩
     * @param userId
     * @return
     */
    @RequestMapping("/getEvaluate")
    public WebResponse getEvaluate(@RequestBody Map<String,String> map){
        String userId=map.get("userId");
        SchoolPracticeEvaluate evaluate = evaluateService.getEvaluate(userId);
        if(ObjectUtils.isEmpty(evaluate)){
            return WebResponse.error(-1,"暂时没有成绩");
        }
        return WebResponse.success(evaluate);
    }

    /**
     * 企业导师对学生实习的评价
     * @param schoolPracticeEvaluate
     * @return
     */
    @RequestMapping("/addEvaluate")
    public WebResponse addEvaluate(@RequestBody  SchoolPracticeEvaluate schoolPracticeEvaluate){
        return evaluateService.addEvaluate(schoolPracticeEvaluate);
    }

    /**
     * 校内老师对学生评价
     * @param schoolPracticeEvaluate
     * @return
     */
    @RequestMapping("/updateEvaluate")
    public WebResponse updateEvaluate(@RequestBody SchoolPracticeEvaluate schoolPracticeEvaluate){
        return evaluateService.updateEvaluate(schoolPracticeEvaluate);
    }
}
