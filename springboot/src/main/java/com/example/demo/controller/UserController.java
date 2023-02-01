package com.example.demo.controller;


import com.example.demo.config.Result;
import com.example.demo.entity.User;
import com.example.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zsq
 * @since 2023-01-31
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("")
    public Result save(@RequestBody User user) {
        log.info("save(user = {})", user);
        userService.save(user);
        return Result.success();
    }

    @GetMapping("")
    public Result<List<User>> get() {
        List<User> list = userService.list();
        return Result.success(list);
    }

}
