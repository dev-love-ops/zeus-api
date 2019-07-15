package com.devloveops.zeus.controller;

import com.devloveops.zeus.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author rocky
 */
@RestController
@RequestMapping("/v1/user")
@CrossOrigin(value = "*")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @PostMapping
//    @PreAuthorize("hasRole('admin')")
    public JsonResult createUser(){

        return JsonResult.success("用户创建成功!");
    }

}
