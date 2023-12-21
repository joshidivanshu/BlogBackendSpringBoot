package com.blog.Blog.Entities;

import com.blog.Blog.Repo.CommentRepo;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Builder
@Entity
public class Comment {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id")
    private BlogUser user;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime created_at;

    @Column(columnDefinition = "TEXT")
    private String content;


}
