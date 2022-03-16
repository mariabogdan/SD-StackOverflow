package com.example.project.controllers;

import com.example.project.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/forQuestion/{qid}")
    public ResponseEntity<List<String>> getTagsForQuestion(@PathVariable Long qid){
        List<String> tagsList = tagService.getTagsForQuestion(qid);
        return new ResponseEntity<>(tagsList, HttpStatus.OK);
    }
}
