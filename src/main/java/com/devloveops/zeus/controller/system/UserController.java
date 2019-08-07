package com.devloveops.zeus.controller.system;

import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.service.system.UserService;
import com.devloveops.zeus.support.CommonVo;
import com.devloveops.zeus.support.exception.system.ExistsException;
import com.devloveops.zeus.support.query.QuerySystemUser;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rocky
 */
@RestController
@RequestMapping("/api/system/user")
@CrossOrigin(value = "*")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;


    @GetMapping
    public CommonVo<PageInfo<SystemUser>> getUserList(QuerySystemUser queryCondition){

        return CommonVo.success("OK", userService.getUserList(queryCondition));

    }

    @GetMapping("/menuList")
    public CommonVo getUserDetail(HttpServletRequest request){

        return CommonVo.success("OK", userService.getMenuListByUserId(request.getUserPrincipal().getName()));
    }

    @PostMapping
    public CommonVo createUser(@RequestBody SystemUser systemUser){

        try{
            userService.createUser(systemUser);
            return CommonVo.success("OK");
        } catch (ExistsException e){
            return CommonVo.fail(e.getMessage());
        }

    }

    @PutMapping
    public CommonVo modifyUser(@RequestBody SystemUser systemUser){
        userService.modifyUser(systemUser);
        return CommonVo.success("OK");
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('system:user:delete')")
    public CommonVo deleteUser(@RequestBody SystemUser systemUser){
        userService.deleteUser(systemUser);
        return CommonVo.success("OK");
    }

}
