package com.example.project.dtos;

import com.example.project.entities.User;

import java.sql.Timestamp;
import java.util.List;

public class QuestionDTO {
    private Long questionId;
    private String title;
    private String content;
    private Timestamp timestamp;
    private Long authorId;
    private List<String> tags;
    private int votes;

    public QuestionDTO(){}

//    public QuestionDTO(Long questionId, String title, String content, Timestamp timestamp, Long authorId) {
//        this.timestamp = timestamp;
//        this.title = title;
//        this.questionId = questionId;
//        this.content = content;
//        this.authorId = authorId;
//    }

    public QuestionDTO(Long questionId, String title, String content, Timestamp timestamp, Long authorId, int votes) {
        this.timestamp = timestamp;
        this.title = title;
        this.questionId = questionId;
        this.content = content;
        this.authorId = authorId;
        this.votes = votes;
    }


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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
