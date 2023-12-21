package com.blog.Blog.Controllers;

import com.blog.Blog.Entities.BlogUser;
import com.blog.Blog.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepo repo;
}
