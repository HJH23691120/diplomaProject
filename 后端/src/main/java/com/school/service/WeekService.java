package com.school.service;

import com.school.pojo.SchoolWeekReport;
import com.school.response.WebResponse;

/**
 * @Author mjz
 * @Date 2022/3/27 20:22
 * @Version 1.0
 */
public interface WeekService {

    WebResponse addWeek(SchoolWeekReport schoolWeekReport);

    WebResponse updateWeek(SchoolWeekReport schoolWeekReport);

    SchoolWeekReport getWeek(String userId,String weekNum);

    WebResponse getWeekNum(String usetId);
}
