package com.blog.Blog.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Data
@Entity
@Table(name="app_user")
@NoArgsConstructor
@AllArgsConstructor
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;

    @NonNull
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
