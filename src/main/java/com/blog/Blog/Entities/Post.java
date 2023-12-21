package com.blog.Blog.Entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Entity
public class Post {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BlogUser user;

    //Can set using LocalDateTime.now();
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT") // Use TEXT type for longer content
    private String Content;

    private String Title;

    private String imageUrl;

    @OneToMany(mappedBy = "post_id", cascade = CascadeType.ALL)
    private List<Comment> comments;
}
