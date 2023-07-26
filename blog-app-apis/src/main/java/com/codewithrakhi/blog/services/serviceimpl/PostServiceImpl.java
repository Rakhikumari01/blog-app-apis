package com.codewithrakhi.blog.services.serviceimpl;

import com.codewithrakhi.blog.entities.Category;
import com.codewithrakhi.blog.entities.Post;
import com.codewithrakhi.blog.entities.User;
import com.codewithrakhi.blog.exceptions.ResourceNotFoundException;
import com.codewithrakhi.blog.payloads.PostDto;
import com.codewithrakhi.blog.payloads.PostResponse;
import com.codewithrakhi.blog.repositories.CategoryRepo;
import com.codewithrakhi.blog.repositories.PostRepo;
import com.codewithrakhi.blog.repositories.UserRepo;
import com.codewithrakhi.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = this.postRepo.save(post);
        return this.modelMapper.map(newPost, PostDto.class); //converting into post dto
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", " post id", postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());


        Post updatedPost = this.postRepo.save(post);

        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", " post id", postId));

        this.postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {


        Sort sort = null;
        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else {
            sort = Sort.by(sortBy).descending();
        }


        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = this.postRepo.findAll(p);

        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postresponse = new PostResponse();

        postresponse.setContent(postDtos);
        postresponse.setPageNumber(pagePost.getNumber());
        postresponse.setPageSize(pagePost.getSize());
        postresponse.setTotalElements(pagePost.getTotalElements());

        postresponse.setTotalPages(pagePost.getTotalPages());
        postresponse.setLastPage(pagePost.isLast());

        return postresponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId)); // i mistaked here by passing user id and not userId
        List<Post> posts = this.postRepo.findByUser(user);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());


        return postDtos;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {

        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);

        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());


        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {

        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}
