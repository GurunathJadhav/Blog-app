package com.blogapp.service;

import com.blogapp.entity.Blog;
import com.blogapp.repository.BlogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private BlogRepository blogRepository;
    private ModelMapper modelMapper;

    public BlogService(BlogRepository blogRepository, ModelMapper modelMapper) {
        this.blogRepository = blogRepository;
        this.modelMapper = modelMapper;
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
            Blog blog2 = mapToEntity(blog1);
            return blogRepository.save(blog2);
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


   private Blog mapToEntity(Blog blog){
        return modelMapper.map(blog,Blog.class);
    }
}
