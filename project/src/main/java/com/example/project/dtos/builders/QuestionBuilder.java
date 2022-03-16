package com.example.project.dtos.builders;

import com.example.project.dtos.QuestionDTO;
import com.example.project.entities.Question;
import com.example.project.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class QuestionBuilder {
    private static QuestionBuilder instance;
    @Autowired
    QuestionService questionService;

    @PostConstruct
    public void registerInstance(){
        instance = this;
    }

    public static QuestionDTO toQuestionDTO(Question question){
        return new QuestionDTO(
                question.getQuestionId(),
                question.getTitle(),
                question.getContent(),
                question.getTimestamp(),
                question.getAuthor().getUserId(),
                //instance.questionService.countNumberLikes(question)
                //instance.questionService.countNumberDislikes(question)
                instance.questionService.countTotalVotes(question)
        );
    }

    public static Question toEntity(QuestionDTO questionDTO){
        return new Question(
                questionDTO.getTitle(),
                questionDTO.getContent(),
                questionDTO.getTimestamp()
        );
    }
}
