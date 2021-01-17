package com.guccigang6.ratForum.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "comment_comment_id_seq")
    @Column(name="comment_id")
    private Long commentId;

    @Column(name="value")
    private String value;

    @Column(name="creation_date")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name="topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserAccount userAccount;
}
