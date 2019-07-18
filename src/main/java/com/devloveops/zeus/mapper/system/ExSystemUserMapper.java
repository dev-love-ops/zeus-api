package com.devloveops.zeus.mapper.system;

import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.support.query.QuerySystemUser;

import java.util.List;

public interface ExSystemUserMapper extends SystemUserMapper {

    List<SystemUser> selectByQueryCondition(QuerySystemUser queryCondition);
}