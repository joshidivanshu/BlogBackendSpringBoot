package com.blog.Blog.Entities;

import com.blog.Blog.Repo.CommentRepo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Builder
@Data
@Entity
@Table(name="comment")
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
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String content;


}
