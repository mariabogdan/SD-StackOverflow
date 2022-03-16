package com.example.project.controllers;

import com.example.project.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/vote")
public class VoteController {
    @Autowired
    private VoteService voteService;

    @GetMapping("/likeQuestion/{qid}")
    public ResponseEntity likeQuestion(@PathVariable Long qid){
        try{
            this.voteService.likeQuestion(qid);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/dislikeQuestion/{qid}")
    public ResponseEntity dislikeQuestion(@PathVariable Long qid){
        try{
            this.voteService.dislikeQuestion(qid);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/likeAnswer/{aid}")
    public ResponseEntity likeAnswer(@PathVariable Long aid){
        try{
            this.voteService.likeAnswer(aid);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping("/dislikeAnswer/{aid}")
    public ResponseEntity dislikeAnswer(@PathVariable Long aid){
        try{
            this.voteService.dislikeAnswer(aid);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.OK);
        }
    }
}
