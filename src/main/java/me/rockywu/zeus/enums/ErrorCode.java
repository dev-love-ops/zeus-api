package me.rockywu.zeus.enums;

/**
 * HTTP状态码
 * @author RockyWu
 * @date 2019-01-23 23:43
 */
public enum ErrorCode {
    //用户和登录相关
    LOGIN_PASSWORD_WRONG(400,"密码错误"),

    //工单相关
    RESOURCE_APPLY_FLOW_CREATED_FAILED(410, "资源申请工单创建失败!");


    private Integer code;
    private String desc;

    ErrorCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }}
