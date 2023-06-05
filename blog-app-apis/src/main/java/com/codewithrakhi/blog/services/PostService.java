package com.codewithrakhi.blog.services;

import com.codewithrakhi.blog.payloads.PostDto;
import com.codewithrakhi.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);


    //update
    PostDto updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //getall
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get single
    PostDto getPostById(Integer postId);


    //get by userid
    List<PostDto> getPostByUser(Integer userId);


    //get by category id
    List<PostDto> getPostByCategory(Integer categoryId);

    //search
    List<PostDto> searchPosts(String keyword);



}
