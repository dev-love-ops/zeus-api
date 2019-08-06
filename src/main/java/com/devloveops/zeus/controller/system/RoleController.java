package com.devloveops.zeus.controller.system;

import com.devloveops.zeus.domain.system.SystemRole;
import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.service.system.RoleService;
import com.devloveops.zeus.service.system.UserService;
import com.devloveops.zeus.support.CommonVo;
import com.devloveops.zeus.support.exception.system.ExistsException;
import com.devloveops.zeus.support.query.QuerySystemRole;
import com.devloveops.zeus.support.query.QuerySystemUser;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @author rocky
 */
@RestController
@RequestMapping("/api/system/role")
@CrossOrigin(value = "*")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoleService roleService;


    @GetMapping
    public CommonVo<PageInfo<SystemRole>> getRoleList(QuerySystemRole queryCondition){

        return CommonVo.success("OK", roleService.getRoleList(queryCondition));

    }

    @PostMapping
    public CommonVo createRole(@RequestBody SystemRole systemRole){

        try{
            roleService.createRole(systemRole);
            return CommonVo.success("OK");
        } catch (ExistsException e){
            return CommonVo.fail(e.getMessage());
        }

    }

    @PutMapping
    public CommonVo modifyRole(@RequestBody SystemRole systemRole){
        roleService.modifyRole(systemRole);
        return CommonVo.success("OK");
    }

    @PutMapping("/permission")
    public CommonVo modifyRolePerm(@RequestBody SystemRole systemRole){
        roleService.modifyRolePerm(systemRole);
        return CommonVo.success("OK");
    }

    @DeleteMapping
    public CommonVo deleteRole(@RequestBody SystemRole systemRole){
        roleService.deleteRole(systemRole);
        return CommonVo.success("OK");
    }

}
