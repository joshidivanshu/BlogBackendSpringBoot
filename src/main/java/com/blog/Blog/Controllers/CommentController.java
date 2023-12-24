package com.blog.Blog.Controllers;

import com.blog.Blog.Entities.BlogUser;
import com.blog.Blog.Entities.Comment;
import com.blog.Blog.Entities.Post;
import com.blog.Blog.Repo.CommentRepo;
import com.blog.Blog.Repo.PostRepo;
import com.blog.Blog.Repo.UserRepo;
import com.blog.Blog.RequestClasses.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class CommentController {
    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    /**
     * Example request to create a comment.
     * curl -X POST -H "Content-Type: application/json" -d '{"userId":1, "postId": 1, "content" : "comment text you need to read"}' http://localhost:8080/createComment
     * @param commentRequest
     * @return
     */
    @PostMapping("/createComment")
    public ResponseEntity<String> createComment(@RequestBody CommentRequest commentRequest) {
        try {
            BlogUser user = userRepo.findById(commentRequest.getUserId()).orElse(null);

            if(user == null) {
                return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
            }

            Post post = postRepo.findById(commentRequest.getPostId()).orElse(null);

            if(post == null) {
                return new ResponseEntity<>("Post doesn't exist", HttpStatus.BAD_REQUEST);
            }

            Comment comment = Comment.builder().
                    post(post)
                    .user(user)
                    .createdAt(LocalDateTime.now())
                    .content(commentRequest.getContent()).build();

            commentRepo.save(comment);

            return new ResponseEntity<>("Created a comment successfully ", HttpStatus.CREATED);
        }
        catch (Exception ex) {
            return new ResponseEntity<>("Unknown error", HttpStatus.BAD_REQUEST);
        }
    }

}
