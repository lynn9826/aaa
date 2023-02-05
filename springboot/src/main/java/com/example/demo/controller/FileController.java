package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.demo.config.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author zsq
 * @create 2023-02-05-14:44:53
 */
@Slf4j
@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${book.url}")
    private String preUrl;


    /**
     * 上传接口
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) {
        try {
            // 获取源文件名称
            String originalFilename = file.getOriginalFilename();
            // 获取上传路径
            String rootFilePath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/" + originalFilename;
            // 把文件写入到相应路径
            FileUtil.writeBytes(file.getBytes(), rootFilePath);
            String url = preUrl + "/files/" + originalFilename;
            return Result.success(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail();
    }

    /**
     * 下载接口
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
        log.info("获取图片：flag = {}", flag);
        OutputStream os;
        String basePath = System.getProperty("user.dir") + "/springboot/src/main/resources/files/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String filename = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");
        try {
            if (StrUtil.isBlank(filename)) {
                return;
            }
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.setContentType("application/octet-stream");
            byte[] bytes = FileUtil.readBytes(basePath + filename);
            os = response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
