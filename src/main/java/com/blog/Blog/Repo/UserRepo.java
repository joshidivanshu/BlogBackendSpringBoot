package com.blog.Blog.Repo;

import com.blog.Blog.Entities.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<BlogUser, Integer> {
}
