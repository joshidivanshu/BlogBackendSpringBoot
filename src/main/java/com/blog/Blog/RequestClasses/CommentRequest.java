package com.blog.Blog.RequestClasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private int commentId;
    private int postId;
    private int userId;
    private String content;
}
