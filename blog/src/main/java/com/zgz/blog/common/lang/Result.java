package com.zgz.blog.common.lang;

import lombok.Data;

import java.io.Serializable;
@Data
public class Result implements Serializable {
    private int code;//200为正常否则异常
    private String message;
    private Object data;

    public static Result seccess(int code,String message,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
    public static Result seccess(Object data){
        return seccess(200,"操作成功！",data);
    }

    public static Result fail(String message){
        return fail(400,message,null);
    }
    public static Result fail(String message,Object data){

        return fail(400,message,data);
    }
    public static Result fail(int code,String message,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
