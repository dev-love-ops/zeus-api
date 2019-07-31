package com.devloveops.zeus.support.query;

import lombok.Data;

/**
 * @author RockyWu
 * @date 2019-07-17 15:27
 */
@Data
public class QuerySystemUser extends BasicCondition{
    private String username;
    private String userId;
    private String roleId;
}
