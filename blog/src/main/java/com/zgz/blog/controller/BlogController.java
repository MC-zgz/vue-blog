package com.zgz.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgz.blog.common.lang.Result;
import com.zgz.blog.entity.Blog;
import com.zgz.blog.service.BlogService;
import com.zgz.blog.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author willie
 * @since 2021-07-09
 */
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){

        Page page = new Page(currentPage,5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.seccess(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id){

        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"该博客已被删除！");

        return Result.seccess(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody() Blog blog){

        Blog blog1 = null;

        if(blog.getId() != null){
            blog1 = blogService.getById(blog.getId());
            //只能编辑
            Assert.isTrue(blog1.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(),"没有编辑权限！");
        }else {
            blog1 = new Blog();
            blog1.setUserId(ShiroUtil.getProfile().getId());
            blog1.setCreated(LocalDateTime.now());
            blog1.setStatus(0);
        }

        BeanUtil.copyProperties(blog,blog1,"id","userId","created","status");
        blogService.saveOrUpdate(blog1);

        return Result.seccess(blog);
    }


}
