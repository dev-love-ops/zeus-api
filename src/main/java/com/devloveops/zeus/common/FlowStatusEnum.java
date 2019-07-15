package com.devloveops.zeus.common;

/**
 * @author RockyWu
 * @date 2019-01-23 22:49
 */
public enum FlowStatusEnum {
    /**
     * 待审核
     */
    TO_BE_VERIFY(0, "待审核"),
    /**
     * 审核通过, 待处理
     */
    VERIFIED(1, "审核通过, 待处理"),
    /**
     * 审核失败
     */
    VERIFY_FAILED(2, "审核失败"),

    /**
     * 已完成
     */
    DONE(3, "已完成"),
    /**
     * 已撤销
     */
    REVOKED(4, "已撤销");


    /**
     * 定义 private 修饰的实例变量
     */
    private Integer code;
    private String desc;

    FlowStatusEnum(Integer code, String desc) {
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
    }
}
