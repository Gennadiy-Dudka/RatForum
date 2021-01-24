package com.guccigang6.ratForum.repository;

import com.guccigang6.ratForum.entity.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicDao extends CrudRepository<Topic, Long> {
    Slice<Topic> findAll(Pageable pageable);
    Slice<Topic> findByThemeContains(String searchedText, Pageable pageable);
}
