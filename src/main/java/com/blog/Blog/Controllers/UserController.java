package com.blog.Blog.Controllers;

import com.blog.Blog.Entities.BlogUser;
import com.blog.Blog.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepo repo;

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody BlogUser user) {
        try {
            repo.save(user);
            return new ResponseEntity<>("User Created Successfully!!", HttpStatus.CREATED);
        }
        catch (Exception ex) {
            return  new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/allUsers")
    public List<BlogUser> getAllUsers() {
        try {
            List<BlogUser> users = repo.findAll();
            return users;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return  null;
        }

    }

    @GetMapping("/getUser/{userId}")
    public BlogUser getUser(@PathVariable int userId) {
        BlogUser user = repo.findById(userId).orElse(null);
        if(user == null)
            return null;
        return user;
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        BlogUser user = repo.findById(userId).orElse(null);
        if(user != null) {
            repo.delete(user);
            return new ResponseEntity<>("User Deleted Successfully!!", HttpStatus.OK);
        }
        return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody BlogUser updatedUser) {
        BlogUser existingUser = repo.findById(id).orElse(null);

        if(existingUser != null) {
            if (updatedUser.getName() != null) {
                existingUser.setName(updatedUser.getName());
            }

            if (updatedUser.getEmail() != null) {
                existingUser.setEmail(updatedUser.getEmail());
            }

            // Add more fields as needed

            // Step 3: Save the updated user back to the repository
            repo.save(existingUser);

            // Step 4: Return a ResponseEntity indicating success
            return ResponseEntity.ok("User updated successfully");
        }

        return new ResponseEntity<>("User doesn't exist", HttpStatus.BAD_REQUEST);

    }
}
