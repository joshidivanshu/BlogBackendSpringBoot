package com.blog.Blog.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="post_id")
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private BlogUser user;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String content;


}
