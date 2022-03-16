package com.example.project.controllers;

import com.example.project.dtos.AnswerDTO;
import com.example.project.dtos.builders.AnswerBuilder;
import com.example.project.entities.Answer;
import com.example.project.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping
    public ResponseEntity<List<AnswerDTO>> getAnswers(){
        List<Answer> answerList = answerService.listAllByVotes();
        List<AnswerDTO> answerDTOs = answerList.stream()
                .map(answer-> AnswerBuilder.toAnswerDTO(answer)).collect(Collectors.toList());
        return new ResponseEntity<>(answerDTOs, HttpStatus.OK);
    }

    @GetMapping("/forQuestion/{qid}")
    public ResponseEntity<List<AnswerDTO>> getAnswersForQuestion(@PathVariable Long qid){
        List<Answer> answerList = answerService.listAllForQuestion(qid);
        List<AnswerDTO> answerDTOs = answerList.stream()
                .map(answer-> AnswerBuilder.toAnswerDTO(answer)).collect(Collectors.toList());
        return new ResponseEntity<>(answerDTOs, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity postAnswer(@RequestBody AnswerDTO answerDTO){
        Answer answer = AnswerBuilder.toEntity(answerDTO);
        try{
            Answer answerAdded = answerService.save(answer, answerDTO.getQuestionId());
            return new ResponseEntity(AnswerBuilder.toAnswerDTO(answerAdded), HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity putAnswer(@RequestBody AnswerDTO answerDTO, @PathVariable Long id){
        Answer answer = AnswerBuilder.toEntity(answerDTO);
        try{
            Answer updatedAnswer = answerService.saveEdit(id, answer);
            return new ResponseEntity(AnswerBuilder.toAnswerDTO(updatedAnswer), HttpStatus.OK);

        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable Long id){
        try{
            answerService.delete(id);
            return new ResponseEntity<>("Answer "+id+" deleted", HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
        }
    }
}
