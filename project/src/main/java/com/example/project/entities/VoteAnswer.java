package com.example.project.entities;

import javax.persistence.*;

@Entity
@Table(name="vote_answer")
public class VoteAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteAnswerId;

    @ManyToOne
    @JoinColumn(name="answerId")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name="voteId")
    private Vote vote;

    public VoteAnswer(){}

    public Long getVoteAnswerId() {
        return voteAnswerId;
    }

    public void setVoteAnswerId(Long voteAnswerId) {
        this.voteAnswerId = voteAnswerId;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}


