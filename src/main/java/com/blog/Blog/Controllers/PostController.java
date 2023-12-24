package com.blog.Blog.Controllers;

import com.blog.Blog.Entities.Post;
import com.blog.Blog.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostRepo repo;

    @PostMapping("/createPost")
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        try {
            repo.save(post);
            return new ResponseEntity<>("Post created succesfully!!", HttpStatus.CREATED);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>("Unknown error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        try {
            return repo.findAll();
        }
        catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/getPost/{postId}")
    public Post getPost(@PathVariable int postId) {
        Post post = repo.findById(postId).orElse(null);
        return post;
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable int postId) {
        Post post = repo.findById(postId).orElse(null);
        if(post != null) {
            repo.delete(post);
            return new ResponseEntity<>("Post Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("No such user found", HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/updatePost")
    public ResponseEntity<String> updatePost(@RequestBody Post updatePost) {
        Post existingPost = repo.findById(updatePost.getId()).orElse(null);

        if (existingPost != null) {
            if(updatePost.getContent() != null) {
                existingPost.setContent(updatePost.getContent());
            }

            if(updatePost.getTitle() != null) {
                existingPost.setTitle(updatePost.getTitle());
            }

            existingPost.setCreatedAt(updatePost.getCreatedAt());
            repo.save(existingPost);

            return ResponseEntity.ok("Post updated successfully");
        }

        return new ResponseEntity("No such user exists ", HttpStatus.BAD_REQUEST);
    }


}
