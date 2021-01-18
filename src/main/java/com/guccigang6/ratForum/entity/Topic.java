package com.guccigang6.ratForum.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="topic")
@Getter
@Setter
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "topic_id_generator")
    @SequenceGenerator(name="topic_id_generator",
                       sequenceName = "topic_topic_id_seq",
                       allocationSize = 1)
    @Column(name="topic_id")
    private Long topicId;

    @Column(name="theme")
    private String theme;

    @Column(name="value")
    private String value;

    @Column(name="creation_date")
    private LocalDateTime creationDate;

    @Column(name="image")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "topic")
    @OrderBy("creationDate desc")
    private List<Comment> comments;
}
