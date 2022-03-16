package com.example.project.dtos;

import java.util.List;

public class FilterDTO {
    private String text;
    private List<String> tags;

    public FilterDTO(String text, List<String> tags){
        this.text = text;
        this.tags = tags;
    }

    public FilterDTO() {}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
