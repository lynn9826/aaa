package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.Result;
import com.example.demo.config.ResultCode;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Result insert(@RequestBody User user) {
        log.info("user.insert(user = {})", user);
        userService.save(user);
        return Result.success();
    }

    @PutMapping("")
    public Result update(@RequestBody User user) {
        log.info("user.update(user = {})", user);
        userService.updateById(user);
        return Result.success();
    }

    // @GetMapping("")
    // public Result<List<User>> get() {
    //     List<User> list = userService.list();
    //     return Result.success(list);
    // }

    @GetMapping("")
    public Result<Page<User>> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @RequestParam(defaultValue = "") String search) {
        log.info("user.findPage : pageNum = {}, pageSize = {}, search = {}", pageNum, pageSize, search);
        try {
            LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
            if (StrUtil.isNotBlank(search)) {
                wrapper.like(User::getUsername, search);
            }
            Page<User> page = userService.page(new Page<>(pageNum, pageSize), wrapper);
            return Result.success(page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        try {
            userService.removeById(id);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail();
    }


    @PostMapping("/login")
    public Result<?> login(@RequestBody User user) {
        log.info("登录：username = {}, password = {}", user.getUsername(), user.getPassword());
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
        User res = userService.getOne(queryWrapper);
        if (res == null) {
            return Result.fail(ResultCode.LOGIN_ERROR);
        }
        return Result.success();
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        log.info("注册：username = {}, password = {}, confirm: {}", user.getUsername(), user.getPassword(), user.getConfirm());
        if (!StrUtil.equals(user.getPassword(), user.getConfirm())) {
            return Result.fail(ResultCode.LOGIN_ERROR_2);
        }
        // 查询账号是否重复
        User res = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res != null) {
            return Result.fail(ResultCode.LOGIN_ERROR_3);
        }
        // 注册成功
        userService.save(user);
        return Result.success();
    }

}
