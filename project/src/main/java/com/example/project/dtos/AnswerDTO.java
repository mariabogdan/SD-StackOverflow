package com.example.project.dtos;

import com.example.project.entities.Question;
import com.example.project.entities.User;

import java.sql.Timestamp;

public class AnswerDTO {

    private Long answerId;
    private String content;
    private Timestamp timestamp;
    private Long authorId;
    private Long questionId;
    private int votes;

 //   public AnswerDTO(Long answerId, String content, Timestamp timestamp, Long authorId, Long questionId) {
 //       this.answerId = answerId;
 //       this.content = content;
 //       this.timestamp = timestamp;
 //       this.authorId = authorId;
 //       this.questionId = questionId;
 //   }

    public AnswerDTO(Long answerId, String content, Timestamp timestamp, Long authorId, Long questionId, int votes) {
           this.answerId = answerId;
           this.content = content;
           this.timestamp = timestamp;
           this.authorId = authorId;
           this.questionId = questionId;
           this.votes = votes;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
