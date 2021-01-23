package com.guccigang6.ratForum.repository;

import com.guccigang6.ratForum.entity.Comment;
import com.guccigang6.ratForum.entity.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends CrudRepository<Comment, Long> {
    void deleteAllByTopic(Topic topic);
}
