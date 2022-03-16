package com.example.project.services;

import com.example.project.entities.Tag;
import com.example.project.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    Long saveFromTagName(String tagName){
        Tag newTag = new Tag(tagName);
        return tagRepository.save(newTag).getTagId();
    }

    public List<String> getTagsForQuestion(Long qid) {
        return tagRepository.findTagByQuestion(qid);
    }

    public List<Long> getIdsForTags(List<String> tags) {
         return tags.stream().map(tag -> tagRepository.findByTagName(tag).getTagId())
                 .collect(Collectors.toList());
    }
}
