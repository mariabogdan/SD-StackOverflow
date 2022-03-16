package com.example.project.services;

import com.example.project.entities.Question;
import com.example.project.entities.TagQuestion;
import com.example.project.entities.User;
import com.example.project.repositories.QuestionRepository;
import com.example.project.repositories.TagRepository;
import com.example.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagService tagService;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UserService userService;

    public List<Question> listAll() {
        return questionRepository.findAll();
    }

    public List<Question> listAllbByDate() {
        return questionRepository.findAllByDate();
    }

    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public Question save(Question question, List<String> tags) throws Exception {
        User currentUser = userService.getCurrentUser();


        if(question.getTitle().length() < 4){
            throw new Exception("Question title is too short");
        }
        question.setAuthor(currentUser);
        Question questionDB = questionRepository.save(question);

        for (String tagName:tags) {
            Long tagId = tagService.saveFromTagName(tagName);
            tagRepository.insertTagQuestion(questionDB.getQuestionId(), tagId);
        }
        return questionDB;
    }


    public Question findById(Long id) {
        return questionRepository.findById(id).get();
    }

    public Question saveEdit(Question question) {
        return questionRepository.save(question);
    }

    public Question saveEdit(Long id, Question question) throws Exception {

        Question questionDB = this.findById(id);
        if(questionDB == null){
            throw new Exception("Question "+id+" not found");
        }
        questionDB.setContent(question.getContent());
        questionDB.setTitle(question.getTitle());
        questionDB.setTimestamp(question.getTimestamp());

        return questionRepository.save(questionDB);
    }

    public void delete(Long id) throws Exception {
        Question question = questionRepository.findById(id).get();
        if (question == null){
            throw new Exception("Question "+id+" not found");
        }
        questionRepository.delete(question);
    }

    public List<Question> getQuestionsForUser(Long uid) {
        return this.questionRepository.findByAuthor_UserId(uid);
    }

    public List<Question> listAllbByText(String text) {
        return questionRepository.findAllByText(text);
    }

    public List<Question> listAllByFilter(String text, List<String> tags) {
        if(tags.size() == 0){
            return questionRepository.findAllByText(text);
        }
        else {
            return questionRepository.findAllByTagsAndText(tags, text);
        }
    }

    public int countNumberLikes(Question question){
        return questionRepository.getNumberLikes(question.getQuestionId()).size();
    }

    public int countNumberDislikes(Question question){
        return questionRepository.getNumberDislikes(question.getQuestionId()).size();
    }

    public int countTotalVotes(Question question){
        return (questionRepository.getNumberLikes(question.getQuestionId()).size() -
                questionRepository.getNumberDislikes(question.getQuestionId()).size());
    }


}
