package com.devloveops.zeus.support;

/**
 * @author RockyWu
 * @date 2019-07-15 21:28
 */
public class CommonVo<T> {
    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static CommonVo success(String message){
        CommonVo commonVo = new CommonVo();
        commonVo.setCode(0);
        commonVo.setMessage(message);
        return commonVo;
    }

    public static CommonVo success(String message, Object data){
        CommonVo commonVo = new CommonVo();
        commonVo.setCode(0);
        commonVo.setMessage(message);
        commonVo.setData(data);
        return commonVo;
    }

    public static CommonVo fail(String message){
        CommonVo commonVo = new CommonVo();
        commonVo.setCode(1);
        commonVo.setMessage(message);
        return commonVo;
    }

}
