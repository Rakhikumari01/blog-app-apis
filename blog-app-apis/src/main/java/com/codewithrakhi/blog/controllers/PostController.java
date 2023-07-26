package com.codewithrakhi.blog.controllers;

import com.codewithrakhi.blog.config.AppConstants;
import com.codewithrakhi.blog.payloads.ApiResponse;
import com.codewithrakhi.blog.payloads.PostDto;
import com.codewithrakhi.blog.payloads.PostResponse;
import com.codewithrakhi.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")

public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> posts = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> posts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }


    // get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost
    (@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
     @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
     @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
     @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        PostResponse postresponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postresponse, HttpStatus.OK);
    }


    // get post by post id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postDto = this.postService.getPostById(postId);

        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId) {
        this.postService.getPostById(postId);
        return new ApiResponse("Post is successfully deleted!!", true);

    }

    //update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatePost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
    }

    //Search post
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
        List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }


}
