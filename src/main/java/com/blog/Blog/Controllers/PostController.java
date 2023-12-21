package com.blog.Blog.Controllers;

import com.blog.Blog.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @Autowired
    PostRepo repo;
}
