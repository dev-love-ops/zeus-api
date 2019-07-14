package me.rockywu.zeus.controller;

import me.rockywu.zeus.common.JsonResult;
import me.rockywu.zeus.domain.User;
import me.rockywu.zeus.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author rocky
 */
@RestController
@RequestMapping("/v1/user")
@CrossOrigin(value = "*")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping
//    @PreAuthorize("hasRole('admin')")
    public JsonResult createUser(@RequestBody User user){
        if(userService.getUserByName(user.getUsername()) != null){
            return JsonResult.error(410, "用户已存在");
        }
        userService.createUser(user);
        return JsonResult.success("用户创建成功!");
    }

    @GetMapping
    public JsonResult getUserList(){
        System.out.println(ResponseEntity.ok("OK"));
        return JsonResult.success("查询成功", userService.getUserByName("wufeiqun"));
    }

    @GetMapping("/getUserInfo")
    public JsonResult getUserInfo(){
        System.out.println(ResponseEntity.ok("OK"));
        return JsonResult.success("查询成功", userService.getUserByName("wufeiqun"));
    }

}
