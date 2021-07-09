package com.zgz.blog.service.impl;

import com.zgz.blog.entity.User;
import com.zgz.blog.mapper.UserMapper;
import com.zgz.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author willie
 * @since 2021-07-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
