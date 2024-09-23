package com.blogapp.service;

import com.blogapp.entity.Blog;
import com.blogapp.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private BlogRepository blogRepository;


    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;

    }

    public Blog createBlog(Blog blog){
        return blogRepository.save(blog);
    }

    public List<Blog> getALlBlogs(){
        return blogRepository.findAll();
    }

    public List<Blog> getByAuthor(String author){
        return blogRepository.findAllByAuthor(author);
    }

    public Blog findById(long id){
        Optional<Blog> blog = blogRepository.findById(id);
        if(blog.isPresent()){
           return blog.get();
        }else{
            return null;
        }
    }

    public Blog updateBlog(long id,Blog blog){
        Optional<Blog> oldBlog = blogRepository.findById(id);
        if(oldBlog.isPresent()){
            Blog blog1 = oldBlog.get();
            blog1.setContent(blog.getContent());
            blog1.setAuthor(blog.getAuthor());
            blog1.setTitle(blog.getTitle());
            blog1.setDescription(blog.getDescription());
            return blogRepository.save(blog1);
        }else{
            return null;
        }
    }

    public String deleteBlog(long id){
        try {
            blogRepository.deleteById(id);
            return "Blog with id " + id + " deleted successfully";
        }catch (Exception e){
            e.printStackTrace();
            return "Blog not found. Unable to delete";

        }
    }

}
