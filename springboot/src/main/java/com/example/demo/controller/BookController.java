package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.Result;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
 * @since 2023-02-05
 */
@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private IBookService bookService;

    @Value("${book.url}")
    private String preUrl;


    @PostMapping("")
    public Result insert(@RequestBody Book book) {
        log.info("insert(book = {})", book);
        bookService.save(book);
        return Result.success();
    }

    @GetMapping("")
    public Result<Page<Book>> get(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "") String search) {
        log.info("findPage : pageNum = {}, pageSize = {}, search = {}", pageNum, pageSize, search);
        try {
            LambdaQueryWrapper<Book> queryWrapper = Wrappers.<Book>lambdaQuery();
            if (StrUtil.isNotBlank(search)) {
                queryWrapper.like(Book::getName, search);
            }
            Page<Book> page = Page.of(pageNum, pageSize);
            Page<Book> result = bookService.page(page, queryWrapper);
            // for (Book book : result.getRecords()) {
            //     book.setCover();
            // }
            return Result.success(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail();
    }

}
