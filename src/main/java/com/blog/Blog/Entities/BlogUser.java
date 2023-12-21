package com.blog.Blog.Entities;

import com.blog.Blog.Repo.UserRepo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Builder
@Entity
public class BlogUser {

    @Id
    private int id;

    private String name;

    private String email;

    private String phoneNumber;

    private String imageUrl;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL)
    private List<Comment> comments;

}
