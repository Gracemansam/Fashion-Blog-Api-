package com.graceman.fashionblogrestapi.repository;

import com.graceman.fashionblogrestapi.model.Comment;
import com.graceman.fashionblogrestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByCommentContaining(String keyword);
}

