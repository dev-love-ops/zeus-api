package com.devloveops.zeus.controller.system;

import com.devloveops.zeus.domain.system.SystemPermission;
import com.devloveops.zeus.domain.system.SystemRole;
import com.devloveops.zeus.service.system.PermissionService;
import com.devloveops.zeus.service.system.RoleService;
import com.devloveops.zeus.support.CommonVo;
import com.devloveops.zeus.support.exception.system.ExistsException;
import com.devloveops.zeus.support.query.QuerySystemRole;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author rocky
 */
@RestController
@RequestMapping("/api/system/permission")
@CrossOrigin(value = "*")
public class PermissionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PermissionService permissionService;


    @GetMapping("/all")
    public CommonVo<List<SystemPermission>> getAllPermission(){

        return CommonVo.success("OK", permissionService.getAllPermission());

    }

    @PostMapping
    public CommonVo addPermission(@RequestBody SystemPermission systemPermission){

        permissionService.insert(systemPermission);
        return CommonVo.success("OK");

    }

    @PutMapping
    public CommonVo modifyPermission(@RequestBody SystemPermission systemPermission){

        permissionService.updateByPrimaryKey(systemPermission);
        return CommonVo.success("OK");

    }

    @DeleteMapping
    public CommonVo deletePermission(@RequestBody List<Integer> ids){

        permissionService.batchDeleteById(ids);
        return CommonVo.success("OK");

    }

}
