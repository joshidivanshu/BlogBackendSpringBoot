package com.blog.Blog.Controllers;

import com.blog.Blog.Repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    CommentRepo repo;
}
