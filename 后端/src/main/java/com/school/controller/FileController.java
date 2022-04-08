package com.school.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.school.mapper.SchoolPracticeApplyMapper;
import com.school.pojo.SchoolPracticeApply;
import com.school.response.WebResponse;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author mjz
 * @Date 2022/3/28 19:33
 * @Version 1.0
 */
@RestController
@CrossOrigin
public class FileController extends HttpServlet {
    @Autowired
    SchoolPracticeApplyMapper schoolPracticeApplyMapper;
    /*
    上传接口
     */
    //MultipartFile用于接受前端传过来的file对象
    @PostMapping("/upload")

    public WebResponse upload(String userId,@RequestPart MultipartFile file) throws IOException {
//     IOException   String userId=map.get("userId").toString();
//        Object file1=map.get("File");
//        MultipartFile file= (MultipartFile) file1;
        String filename = file.getOriginalFilename();//获取文件的名称

        //判断此用户是否上传过文件
        boolean directory = FileUtil.isDirectory(System.getProperty("user.dir") + "/src/main/resources/files/"+userId);
        if(!directory){
            FileUtil.mkdir(System.getProperty("user.dir") + "/src/main/resources/files/"+userId);
        }
        /*
        我们需要给我们的文件加一个文件前缀，不可以加文件后缀，因为我们的文件还有后缀名
        加文件前缀的目的是为了防止文件重名，文件重名的话后续的重名文件会覆盖掉前面的文件
         */
        //修改文件名称（日期+原为文件名称）
        filename=new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"_"+ filename;
        String rootFilePath = System.getProperty("user.dir") + "/src/main/resources/files/" + userId + "/"+filename;
//        String rootFilePath = "G:\\schoolspringboot/src/main/resources/files/s_s278er/"+filename;
        //使用Hutool工具包将我们接收到文件保存到rootFilePath中
        FileUtil.writeBytes(file.getBytes(), rootFilePath);
        //把文件名称填到实习申请里面
        SchoolPracticeApply schoolPracticeApply = schoolPracticeApplyMapper.selectOne(userId);
        schoolPracticeApply.setUploaadProve(filename);
        schoolPracticeApplyMapper.updateByPrimaryKey(schoolPracticeApply);
        return WebResponse.success(filename);//返回结果
    }


    /*
    下载接口
     */
    @GetMapping("/download")
    public void getFiles(String userId,String fileName,HttpServletResponse response){
        OutputStream os;//新建一个输出流对象
        String basePath = System.getProperty("user.dir") + "/src/main/resources/files/"+userId+"/"; //定义文件上传的根路径
        List<String> fileNames = FileUtil.listFileNames(basePath);//获取所有的文件名称
        String file = fileNames.stream().filter(name -> name.contains(fileName)).findAny().orElse("");//找到跟参数一致的文件
        try {
            if(StrUtil.isNotEmpty(file)){
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + file);//通过文件的路径读取文件字节流
                os = response.getOutputStream();//通过response的输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("文件下载失败");
        }

    }
}
