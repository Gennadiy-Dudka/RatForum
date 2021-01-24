package com.guccigang6.ratForum.service;

import com.guccigang6.ratForum.entity.Comment;
import com.guccigang6.ratForum.entity.Topic;
import com.guccigang6.ratForum.entity.UserAccount;
import com.guccigang6.ratForum.exceptions.RecordNotFoundException;
import com.guccigang6.ratForum.repository.CommentDao;
import com.guccigang6.ratForum.repository.TopicDao;
import com.guccigang6.ratForum.security.UserAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class TopicService {
    private final TopicDao topicDao;
    private final CommentDao commentDao;
    private final static int PAGE_SIZE = 8;

    @Autowired
    public TopicService(TopicDao topicDao, CommentDao commentDao) {
        this.topicDao = topicDao;
        this.commentDao = commentDao;
    }

    @Transactional
    public Slice<Topic> getPaginatedTopics(int page){
        return topicDao.findAll(PageRequest.of(page, PAGE_SIZE,
                Sort.Direction.DESC, "creationDate"));
    }

    @Transactional
    public Slice<Topic> searchPaginatedTopics(int page, String searchedText){
        return topicDao.findByThemeContains(searchedText,
                PageRequest.of(page, PAGE_SIZE, Sort.Direction.DESC, "creationDate"));
    }

    @Transactional
    public Topic saveTopic(Topic topic, MultipartFile image){
        UserAccount userAccount = getCurrentUser();
        try {
            if (!image.isEmpty()) {
                topic.setImage(image.getBytes());
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        topic.setCreationDate(LocalDateTime.now());
        topic.setUserAccount(userAccount);
        return topicDao.save(topic);
    }

    @Transactional
    public Topic getTopic(Long id) throws RecordNotFoundException {
        return topicDao.findById(id).orElseThrow(RecordNotFoundException::new);
    }

    @Transactional
    public void deleteTopic(Topic topic){
        commentDao.deleteAllByTopic(topic);
        topicDao.delete(topic);
    }

    @Transactional
    public void saveComment(Comment comment, Long topicId) throws RecordNotFoundException {
        Topic topic = topicDao.findById(topicId).orElseThrow(RecordNotFoundException::new);
        UserAccount userAccount = getCurrentUser();
        comment.setTopic(topic);
        comment.setCreationDate(LocalDateTime.now());
        comment.setUserAccount(userAccount);
        commentDao.save(comment);
    }

    @Transactional
    public Comment getComment(Long commentId) throws RecordNotFoundException {
        return commentDao.findById(commentId).orElseThrow(RecordNotFoundException::new);
    }

    @Transactional
    public void deleteComment(Comment comment){
        commentDao.delete(comment);
    }

    public boolean checkOwner(Topic topic){
        UserAccount userAccount = getCurrentUser();
        return topic.getUserAccount().getUsername().equals(userAccount.getUsername());
    }

    private UserAccount getCurrentUser(){
        return ((UserAccountDetails)SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUserAccount();
    }
}
