package com.devloveops.zeus.domain.system;

import lombok.*;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table system_user
 */
@Data
public class SystemUser {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;
}