package com.example.project.dtos;

import com.example.project.entities.Question;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO {
    private Long userId;
    private String email;
    private String username;
    private String password;
    private Boolean userEnabled;
    private List<Long> questions;
    private List<Long> answers;


    public UserDTO(Long userId, String username, String password, String email, Boolean userEnabled, List<Long> questions, List<Long> answers) {
        this.userId = userId;
        this.userEnabled = userEnabled;
        this.username = username;
        this.password = password;
        this.email = email;
        this.questions = questions;
        this.answers = answers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getUserEnabled() {
        return userEnabled;
    }

    public void setUserEnabled(Boolean userEnabled) {
        this.userEnabled = userEnabled;
    }

    public List<Long> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Long> questions) {
        this.questions = questions;
    }

    public List<Long> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Long> answers) {
        this.answers = answers;
    }
}
