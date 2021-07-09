package com.zgz.blog.service.impl;

import com.zgz.blog.entity.Blog;
import com.zgz.blog.mapper.BlogMapper;
import com.zgz.blog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
