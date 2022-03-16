package com.example.project.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String title;
    private String content;
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name="userId")
    private User author;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "question")
    private Set<TagQuestion> tagsQuestion = new HashSet<>();

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "question")
    private Set<VoteQuestion> votesQuestion = new HashSet<>();

    public Question(String title, String content, Timestamp timestamp) {
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Question() {}

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Set<TagQuestion> getTagsQuestion() {
        return tagsQuestion;
    }

    public void setTagsQuestion(Set<TagQuestion> tagsQuestion) {
        this.tagsQuestion = tagsQuestion;
    }

    public Set<VoteQuestion> getVotesQuestion() {
        return votesQuestion;
    }

    public void setVotesQuestion(Set<VoteQuestion> votesQuestion) {
        this.votesQuestion = votesQuestion;
    }
}
