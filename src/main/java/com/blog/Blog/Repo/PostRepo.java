package com.blog.Blog.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.Blog.Entities.Post;


public interface PostRepo extends JpaRepository<Post, Integer> {
}
