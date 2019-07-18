package com.devloveops.zeus.controller.system;

import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.service.system.UserService;
import com.devloveops.zeus.support.CommonVo;
import com.devloveops.zeus.support.exception.system.UserExistsException;
import com.devloveops.zeus.support.query.QuerySystemUser;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public CommonVo createUser(@RequestBody SystemUser systemUser){
        try{
            userService.createUser(systemUser);
            return CommonVo.success("OK");
        } catch (UserExistsException e){
            return CommonVo.fail(e.getMessage());
        }

    }

    @PutMapping
    public CommonVo modifyUser(@RequestBody SystemUser systemUser){
        userService.modifyUser(systemUser);
        return CommonVo.success("OK");
    }

    @DeleteMapping
    public CommonVo deleteUser(@RequestBody SystemUser systemUser){
        userService.deleteUser(systemUser);
        return CommonVo.success("OK");
    }

}
