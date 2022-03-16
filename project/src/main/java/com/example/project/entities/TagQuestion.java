package com.example.project.entities;


import javax.persistence.*;

@Entity
@Table(name="tag_question")
public class TagQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagQuestionId;

    @ManyToOne
    @JoinColumn(name="questionId")
    private Question question;

    @ManyToOne
    @JoinColumn(name="tagId")
    private Tag tag;

    public TagQuestion(){}


    public Long getTagQuestionId() {
        return tagQuestionId;
    }

    public void setTagQuestionId(Long tagQuestionId) {
        this.tagQuestionId = tagQuestionId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }


}
