package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.Result;
import com.example.demo.entity.User;
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
        log.info("einsert(user = {})", user);
        userService.save(user);
        return Result.success();
    }

    @PutMapping("")
    public Result update(@RequestBody User user) {
        log.info("update(user = {})", user);
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
        log.info("findPage : pageNum = {}, pageSize = {}, search = {}", pageNum, pageSize, search);
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

}
