package com.devloveops.zeus.domain.system;

import lombok.Data;

import java.util.List;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table system_user
 */
@Data
public class SystemUserRole {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

}