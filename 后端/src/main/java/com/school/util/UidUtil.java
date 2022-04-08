package com.school.util;

import java.util.Random;
import java.util.UUID;

/**
 * @Author mjz
 * @Date 2022/3/23 14:59
 * @Version 1.0
 */
public class UidUtil {
    final int number=5;
    int num=0;

    public static String genCodes(String begin,int length){
        String usetId=begin;
        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; i++)
        {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                val += (char) (choice + random.nextInt(26));
            }
            else if("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                val += String.valueOf(random.nextInt(10));
            }
        }
        usetId=usetId+val.toLowerCase();

        return usetId;
    }

}
