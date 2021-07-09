package com.zgz.blog.controller;


import com.zgz.blog.common.lang.Result;
import com.zgz.blog.entity.User;
import com.zgz.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author willie
 * @since 2021-07-09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/index")
    public Result index(){
        User user = userService.getById(1);
        Result result = new Result();
        result.setCode(200);
        result.setData(user);
        result.setMessage("操作成功");
//        Result.seccess(200,"操作成功！",user);
        return result;
    }

}
