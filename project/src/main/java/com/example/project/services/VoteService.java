package com.example.project.services;

import com.example.project.entities.User;
import com.example.project.entities.Vote;
import com.example.project.repositories.AnswerRepository;
import com.example.project.repositories.QuestionRepository;
import com.example.project.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {
    @Autowired
    private UserService userService;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;


    public void likeQuestion(Long qid) throws Exception {
        User currentUser = userService.getCurrentUser();

        Vote likeVote = voteRepository.findByUser_UserId_AndVoteType(currentUser.getUserId(), true);
        Long authorIdOfQuestion = questionRepository.findByQuestionId(qid).getAuthor().getUserId();

        if(!currentUser.getUserId().equals(authorIdOfQuestion)) {
            List<Long> vote_question_ids = voteRepository.getIfUserVotedQuestion(currentUser.getUserId(), qid);
            vote_question_ids.stream().forEach(vqId -> voteRepository.deleteVoteQuestion(vqId));
            voteRepository.insertVoteQuestion(qid, likeVote.getVoteId());
        }
        else{
            throw new Exception("One user should not like its own question!");
        }
    }

    public void dislikeQuestion(Long qid) throws Exception {
        User currentUser = userService.getCurrentUser();

        Vote dislikeVote = voteRepository.findByUser_UserId_AndVoteType(currentUser.getUserId(), false);
        Long authorIdOfQuestion = questionRepository.findByQuestionId(qid).getAuthor().getUserId();

        if(!currentUser.getUserId().equals(authorIdOfQuestion)) {
            List<Long> vote_question_ids = voteRepository.getIfUserVotedQuestion(currentUser.getUserId(), qid);
            vote_question_ids.stream().forEach(vqId -> voteRepository.deleteVoteQuestion(vqId));
            voteRepository.insertVoteQuestion(qid, dislikeVote.getVoteId());
        }
        else
            throw new Exception("One user should not dislike its own question!");


    }

    public void likeAnswer(Long aid) throws Exception {
        User currentUser = userService.getCurrentUser();

        Vote likeVote = voteRepository.findByUser_UserId_AndVoteType(currentUser.getUserId(), true);
        Long authorIdOfAnswer = answerRepository.findById(aid).get().getAuthor().getUserId();

        if(!currentUser.getUserId().equals(authorIdOfAnswer)) {
            List<Long> vote_answer_ids = voteRepository.getIfUserVotedAnswer(currentUser.getUserId(), aid);
            vote_answer_ids.stream().forEach(vaId -> voteRepository.deleteVoteAnswer(vaId));
            voteRepository.insertVoteAnswer(aid, likeVote.getVoteId());
        }
        else
            throw new Exception("One user should not like its own answer!");
    }


    public void dislikeAnswer(Long aid) throws Exception {
        User currentUser = userService.getCurrentUser();

        Vote dislikeVote = voteRepository.findByUser_UserId_AndVoteType(currentUser.getUserId(), false);
        Long authorIdOfAnswer = answerRepository.findById(aid).get().getAuthor().getUserId();

        if(!currentUser.getUserId().equals(authorIdOfAnswer)) {
            List<Long> vote_answer_ids = voteRepository.getIfUserVotedAnswer(currentUser.getUserId(), aid);
            vote_answer_ids.stream().forEach(vaId -> voteRepository.deleteVoteAnswer(vaId));
            voteRepository.insertVoteAnswer(aid, dislikeVote.getVoteId());
        }
        else
            throw new Exception("One user should not like its own answer!");
    }

    private boolean checkIfAlreadyVotedQuestion(Long userId, Long qid) {
        List<Long> votes = voteRepository.getIfUserVotedQuestion(userId, qid);
        System.out.println("HERE  ====  " + votes.size());
        if(votes.size() == 0){
            return false;
        }
        else {
            return true;
        }
    }

    private boolean checkIfAlreadyVotedAnswer(Long userId, Long aid) {
        List<Long> votes = voteRepository.getIfUserVotedAnswer(userId, aid);
        System.out.println("HERE  ====  " + votes.size());
        if(votes.size() == 0){
            return false;
        }
        else {
            return true;
        }
    }
}
