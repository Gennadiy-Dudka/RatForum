package com.guccigang6.ratForum.service;

import com.guccigang6.ratForum.entity.Comment;
import com.guccigang6.ratForum.entity.Topic;
import com.guccigang6.ratForum.entity.UserAccount;
import com.guccigang6.ratForum.exceptions.PageNotFoundException;
import com.guccigang6.ratForum.repository.CommentDao;
import com.guccigang6.ratForum.repository.TopicDao;
import com.guccigang6.ratForum.security.UserAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicService {
    private final TopicDao topicDao;
    private final CommentDao commentDao;

    @Autowired
    public TopicService(TopicDao topicDao, CommentDao commentDao) {
        this.topicDao = topicDao;
        this.commentDao = commentDao;
    }

    @Transactional
    public List<Topic> getTopics(){
        return topicDao.findAll(Sort.by(Sort.Direction.DESC, "creationDate"));
    }

    @Transactional
    public Topic saveTopic(Topic topic){
        UserAccount userAccount = ((UserAccountDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUserAccount();
        topic.setCreationDate(LocalDateTime.now());
        topic.setUserAccount(userAccount);
        return topicDao.save(topic);
    }

    @Transactional
    public Topic getTopic(Long id) throws PageNotFoundException{
        return topicDao.findById(id).orElseThrow(PageNotFoundException::new);
    }

    @Transactional
    public void saveComment(Comment comment, Long topicId){
        Topic topic = topicDao.findById(topicId).orElseThrow(RuntimeException::new);
        UserAccount userAccount = ((UserAccountDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUserAccount();
        comment.setTopic(topic);
        comment.setCreationDate(LocalDateTime.now());
        comment.setUserAccount(userAccount);
        commentDao.save(comment);
    }
}
