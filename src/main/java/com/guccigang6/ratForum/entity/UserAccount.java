package com.guccigang6.ratForum.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user_account")
@Getter
@Setter
@NoArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="user_account_user_id_seq")
    @Column(name="user_id")
    private Long userId;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "userAccount")
    private List<Topic> topics;

    @OneToMany(mappedBy = "userAccount")
    private List<Comment> comments;
}
