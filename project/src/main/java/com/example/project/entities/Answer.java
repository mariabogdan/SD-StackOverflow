package com.example.project.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    private String content;

    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name="userId")
    private User author;

    @ManyToOne
    @JoinColumn(name="questionId")
    private Question question;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "answer")
    private Set<VoteAnswer> votesAnswer = new HashSet<>();

    public Answer(Long answerId, String content, User author, Question question, Timestamp timestamp) {
        this.answerId = answerId;
        this.content = content;
        this.author = author;
        this.question = question;
        this.timestamp = timestamp;
    }

    public Answer(Long answerId, String content, Timestamp timestamp) {
        this.answerId = answerId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Answer() {

    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<VoteAnswer> getVotesAnswer() {
        return votesAnswer;
    }

    public void setVotesAnswer(Set<VoteAnswer> votesAnswer) {
        this.votesAnswer = votesAnswer;
    }
}
