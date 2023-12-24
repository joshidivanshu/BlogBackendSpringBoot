package com.blog.Blog.Entities;

import com.blog.Blog.Repo.UserRepo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Builder
@Data
@Entity
@Table(name="app_user")
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String phoneNumber;

    private String imageUrl;

    //mapped by is the name of the field in the other table.
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    //mapped by is the name of the field in the other table.
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

}
