package com.blog.Blog.Entities;

import com.blog.Blog.Repo.PostRepo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@Entity
@Table(name="post")
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
    @NonNull
    private String content;

    @NonNull
    private String title;

    private String imageUrl;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;
}
