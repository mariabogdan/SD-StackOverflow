package com.example.project.dtos.builders;

import com.example.project.dtos.UserDTO;
import com.example.project.entities.User;

import java.util.stream.Collectors;

public class UserBuilder {
    public static UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getUserEnabled(),
                user.getQuestions().stream()
                        .map(question -> question.getQuestionId())
                        .collect(Collectors.toList()),

                user.getAnswers().stream()
                        .map(answer -> answer.getAnswerId())
                        .collect(Collectors.toList())
        );
    }

    public static User toEntity(UserDTO userDTO){
        return new User(
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getUserEnabled()
        );
    }
}
