package com.example.project.dtos.builders;

import com.example.project.dtos.AnswerDTO;
import com.example.project.dtos.QuestionDTO;
import com.example.project.entities.Answer;
import com.example.project.entities.Question;
import com.example.project.services.AnswerService;
import com.example.project.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AnswerBuilder {

    private static AnswerBuilder instance;
    @Autowired
    AnswerService answerService;

    @PostConstruct
    public void registerInstance(){
        instance = this;
    }

    public static AnswerDTO toAnswerDTO(Answer answer){
        return new AnswerDTO(
                answer.getAnswerId(),
                answer.getContent(),
                answer.getTimestamp(),
                answer.getAuthor().getUserId(),
                answer.getQuestion().getQuestionId(),
                instance.answerService.countTotalVotes(answer)
        );
    }

    public static Answer toEntity(AnswerDTO answerDTO){
        return new Answer(
                answerDTO.getAnswerId(),
                answerDTO.getContent(),
                answerDTO.getTimestamp()
        );
    }

}
