package com.guccigang6.ratForum.repository;

import com.guccigang6.ratForum.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicDao extends JpaRepository<Topic, Long> {
}
