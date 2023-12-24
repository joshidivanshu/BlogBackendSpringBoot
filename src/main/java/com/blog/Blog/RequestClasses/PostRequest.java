package com.blog.Blog.RequestClasses;

import com.blog.Blog.Entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private int userId;
    private String content;
    private String title;
    private String imageUrl;
    private List<Comment> comments;
}
