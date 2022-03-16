package com.example.project.controllers;

import com.example.project.dtos.FilterDTO;
import com.example.project.dtos.QuestionDTO;
import com.example.project.dtos.builders.QuestionBuilder;
import com.example.project.dtos.builders.UserBuilder;
import com.example.project.entities.Question;
import com.example.project.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getQuestions(){
        List<Question> questionList = questionService.listAllbByDate();
        List<QuestionDTO> questionDTOs = questionList.stream()
                .map(question-> QuestionBuilder.toQuestionDTO(question)).collect(Collectors.toList());
        return new ResponseEntity<>(questionDTOs, HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByText(@RequestBody FilterDTO filterDTO){
        List<Question> questionList = questionService.listAllByFilter(filterDTO.getText(), filterDTO.getTags());
        List<QuestionDTO> questionDTOs = questionList.stream()
                .map(question-> QuestionBuilder.toQuestionDTO(question)).collect(Collectors.toList());
        return new ResponseEntity<>(questionDTOs, HttpStatus.OK);
    }

    @GetMapping("/forUser/{uid}")
    public ResponseEntity<List<Question>> getQuestionsForUser(@PathVariable Long uid){
        List<Question> questionList = questionService.getQuestionsForUser(uid);
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity postQuestion(@RequestBody QuestionDTO questionDTO){
        Question question = QuestionBuilder.toEntity(questionDTO);
        try{
            Question questionAdded = questionService.save(question, questionDTO.getTags());
            return new ResponseEntity(QuestionBuilder.toQuestionDTO(questionAdded), HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putQuestion(@RequestBody QuestionDTO questionDTO, @PathVariable Long id){
        Question question = QuestionBuilder.toEntity(questionDTO);
        try{
            Question updatedQuestion = questionService.saveEdit(id, question);
            return new ResponseEntity(QuestionBuilder.toQuestionDTO(updatedQuestion), HttpStatus.OK);

        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id){
        try{
            questionService.delete(id);
            return new ResponseEntity<>("Question "+id+" deleted", HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
        }
    }
}
