package com.example.project.entities;

import javax.persistence.*;

@Entity
@Table(name="vote_question")
public class VoteQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteQuestionId;

    @ManyToOne
    @JoinColumn(name="questionId")
    private Question question;

    @ManyToOne
    @JoinColumn(name="voteId")
    private Vote vote;


    public VoteQuestion(){}

    public Long getVoteQuestionId() {
        return voteQuestionId;
    }

    public void setVoteQuestionId(Long voteQuestionId) {
        this.voteQuestionId = voteQuestionId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}


