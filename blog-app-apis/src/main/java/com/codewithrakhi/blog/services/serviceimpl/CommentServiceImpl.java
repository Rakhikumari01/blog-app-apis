package com.codewithrakhi.blog.services.serviceimpl;

import com.codewithrakhi.blog.entities.Comment;
import com.codewithrakhi.blog.entities.Post;
import com.codewithrakhi.blog.exceptions.ResourceNotFoundException;
import com.codewithrakhi.blog.payloads.CommentDto;
import com.codewithrakhi.blog.repositories.CommentRepo;
import com.codewithrakhi.blog.repositories.PostRepo;
import com.codewithrakhi.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment savedComment = this.commentRepo.save(comment);

        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment com = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", " commentId", commentId));

        this.commentRepo.delete(com);

    }
}
