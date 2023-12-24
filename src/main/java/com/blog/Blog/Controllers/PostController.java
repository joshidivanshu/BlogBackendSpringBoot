package com.blog.Blog.Controllers;

import com.blog.Blog.Entities.BlogUser;
import com.blog.Blog.Entities.Post;
import com.blog.Blog.Repo.PostRepo;
import com.blog.Blog.Repo.UserRepo;
import com.blog.Blog.RequestClasses.PostRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class PostController {
    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @PostMapping("/createPost")
    public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) {
        try {
            BlogUser user = userRepo.findById(postRequest.getUserId()).orElse(null);

            if(user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
            }

            Post post = Post.builder()
                    .user(user)
                    .createdAt(LocalDateTime.now())
                    .content(postRequest.getContent())
                    .title(postRequest.getTitle())
                    .imageUrl(postRequest.getImageUrl())
                    .comments(postRequest.getComments()).build();

            postRepo.save(post);

            return new ResponseEntity<>("Post created Succesfully!!", HttpStatus.CREATED);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>("Unknown error", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        try {
            return postRepo.findAll();
        }
        catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/getPost/{postId}")
    public Post getPost(@PathVariable int postId) {
        Post post = postRepo.findById(postId).orElse(null);
        return post;
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
        Post post = postRepo.findById(postId).orElse(null);
        if(post != null) {
            postRepo.delete(post);
            return new ResponseEntity<>("Post Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No such user found", HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/updatePost")
    public ResponseEntity<String> updatePost(@RequestBody Post updatePost) {
        Post existingPost = postRepo.findById(updatePost.getId()).orElse(null);

        if (existingPost != null) {
            if(updatePost.getContent() != null) {
                existingPost.setContent(updatePost.getContent());
            }

            if(updatePost.getTitle() != null) {
                existingPost.setTitle(updatePost.getTitle());
            }

            existingPost.setCreatedAt(updatePost.getCreatedAt());
            postRepo.save(existingPost);

            return ResponseEntity.ok("Post updated successfully");
        }

        return new ResponseEntity("No such user exists ", HttpStatus.BAD_REQUEST);
    }


}
