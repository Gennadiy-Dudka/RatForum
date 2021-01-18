package com.guccigang6.ratForum.repository;

import com.guccigang6.ratForum.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountDao extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);
}
