package com.example.project.services;

import com.example.project.entities.Answer;
import com.example.project.entities.Question;
import com.example.project.entities.User;
import com.example.project.repositories.AnswerRepository;
import com.example.project.repositories.QuestionRepository;
import com.example.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    private UserService userService;

    public List<Answer> listAll() {
        return answerRepository.findAll();
    }

    public List<Answer> listAllByVotes() {
        List<Answer> answerList = answerRepository.findAll();
        answerList.sort(Comparator.comparing(ans -> -this.countTotalVotes(ans)));
        return answerList;
    }

    public Answer save(Answer answer, Long questionId) throws Exception {
        Long userId = userService.getCurrentUser().getUserId();
        User user = userRepository.findById(userId).get();
        Question question = questionRepository.findById(questionId).get();

        if(user == null){
            throw new Exception("User with id "+userId+" not found");
        }

        if(question == null){
            throw new Exception("Question with id "+questionId+" not found");
        }

        answer.setAuthor(user);
        answer.setQuestion(question);
        return answerRepository.save(answer);
    }


    public Answer findById(Long id) {
        return answerRepository.findById(id).get();
    }

    public Answer saveEdit(Answer answer) {
        return answerRepository.save(answer);
    }

    public Answer saveEdit(Long id, Answer answer) throws Exception {

        User currentUser = userService.getCurrentUser();

        Answer answerDB = this.findById(id);
        if(answerDB == null){
            throw new Exception("Answer "+id+" not found");
        }
        answerDB.setContent(answer.getContent());
        answerDB.setTimestamp(answer.getTimestamp());

        if(currentUser == answerDB.getAuthor())
            return answerRepository.save(answerDB);
        else
            throw new Exception("Only the author of the answer can edit it!");
    }

    public void delete(Long id) throws Exception {

        User currentUser = userService.getCurrentUser();

        Answer answer = answerRepository.findById(id).get();
        if (answer == null){
            throw new Exception("Answer "+id+" not found");
        }

        if(currentUser == answer.getAuthor())
            answerRepository.delete(answer);
        else
            throw new Exception("Only the author of the answer can delete it!");
    }

    public List<Answer> listAllForQuestion(Long qid) {
        List<Answer> answerList = answerRepository.findByQuestion_QuestionId(qid);
        answerList.sort(Comparator.comparing(ans -> -this.countTotalVotes(ans)));
        return answerList;
    }

    public int countNumberLikes(Answer answer){
        return  answerRepository.getNumberLikes(answer.getAnswerId()).size();
    }

    public int countNumberDislikes(Answer answer){
        return answerRepository.getNumberDislikes(answer.getAnswerId()).size();
    }

    public int countTotalVotes(Answer answer){
        return (answerRepository.getNumberLikes(answer.getAnswerId()).size() -
                answerRepository.getNumberDislikes(answer.getAnswerId()).size());
    }
}
