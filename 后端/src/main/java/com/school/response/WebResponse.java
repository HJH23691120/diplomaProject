package com.school.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Author mjz
 * @Date 2022/3/23 14:30
 * @Version 1.0
 */
public class WebResponse {

    public static final int CODE_SUCCESS=0;
    public static final int CODE_ERROE=-1;


    private  int code;
    private String msg="success";
    private Object data;

    public WebResponse(){ code= CODE_SUCCESS; }

    public void setThrowable(Throwable t){
        code=-1;
        msg=t.getMessage();
    }

    public static WebResponse success(){return success(null);}

    public static WebResponse success(Object data){
        WebResponse webResponse = new WebResponse();
        webResponse.setCode(CODE_SUCCESS);
        webResponse.setData(data);
        return webResponse;
    }
    public static WebResponse error(int code,String msg){
        WebResponse webResponse = new WebResponse();
        webResponse.setCode(code);
        webResponse.setMsg(msg);
        return webResponse;
    }
    @JsonIgnore
    public boolean isSuccess(){return this.code==CODE_SUCCESS;}

    @JsonIgnore
    public boolean isError(){return this.code==CODE_ERROE;}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
