package com.devloveops.zeus.controller.system;

import com.devloveops.zeus.domain.system.ExSystemUser;
import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.service.system.UserService;
import com.devloveops.zeus.support.CommonVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public CommonVo<List<ExSystemUser>> createUser(){
        return CommonVo.success("OK", userService.getUserList());
    }

}
