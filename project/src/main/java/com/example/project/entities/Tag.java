package com.example.project.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    private String tagName;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "tag")
    private Set<TagQuestion> tagsQuestion = new HashSet<>();

    public Tag(){}

    public Tag(String tagName){
        this.tagName = tagName;

    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<TagQuestion> getTagsQuestion() {
        return tagsQuestion;
    }

    public void setTagsQuestion(Set<TagQuestion> tagsQuestion) {
        this.tagsQuestion = tagsQuestion;
    }
}
