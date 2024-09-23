package com.blogapp.controller;

import com.blogapp.entity.Blog;
import com.blogapp.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        return new ResponseEntity<>(blogService.createBlog(blog), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAll(){
        return ResponseEntity.ok(blogService.getALlBlogs());
    }

    @GetMapping("author/{author}")
    public ResponseEntity<List<Blog>> getAllByAuthor(@PathVariable String author){
        return ResponseEntity.ok(blogService.getByAuthor(author));
    }

    @GetMapping("{id}")
    public ResponseEntity<Blog> findById(@PathVariable long id){
        return ResponseEntity.ok(blogService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable long id ,@RequestBody Blog blog){
        return ResponseEntity.ok(blogService.updateBlog(id,blog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable long id){
        return ResponseEntity.ok(blogService.deleteBlog(id));
    }
}
