package com.example.project.repositories;

import com.example.project.entities.Tag;
import com.example.project.entities.User;
import com.example.project.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Modifying
    @Query(value="insert into vote_question (question_id, vote_id) values (:qId, :vId)",
            nativeQuery=true)
    @Transactional
    void insertVoteQuestion(@Param("qId") Long qId, @Param("vId") Long vId);


    @Modifying
    @Query(value="insert into vote_answer (answer_id, vote_id) values (:aId, :vId)",
            nativeQuery=true)
    @Transactional
    void insertVoteAnswer(@Param("aId") Long aId, @Param("vId") Long vId);

    Vote findByUser_UserId_AndVoteType(Long uid, boolean type);

    @Modifying
    @Query(value="SELECT vq.vote_question_id FROM users u JOIN vote v ON v.user_id=u.user_id " +
            "JOIN vote_question vq ON vq.vote_id=v.vote_id " +
            "WHERE u.user_id=:uId " +
            "AND vq.question_id=:qId",
            nativeQuery=true)
    @Transactional
    List<Long> getIfUserVotedQuestion(@Param("uId") Long uId, @Param("qId") Long qId);

    @Modifying
    @Query(value="SELECT va.vote_answer_id FROM users u JOIN vote v ON v.user_id=u.user_id " +
            "JOIN vote_answer va ON va.vote_id=v.vote_id " +
            "WHERE u.user_id=:uId " +
            "AND va.answer_id=:aId",
            nativeQuery=true)
    @Transactional
    List<Long> getIfUserVotedAnswer(@Param("uId") Long uId, @Param("aId") Long aId);

    @Modifying
    @Query(value="DELETE from vote_question vq WHERE vq.vote_question_id=:vqId ",
            nativeQuery=true)
    @Transactional
    void deleteVoteQuestion(@Param("vqId") Long vqId);

    @Modifying
    @Query(value="DELETE from vote_answer va WHERE va.vote_answer_id=:vaId ",
            nativeQuery=true)
    @Transactional
    void deleteVoteAnswer(@Param("vaId") Long vaId);

//    @Modifying
//    @Query(value="DELETE from vote v JOIN vote_question vq ON vq.vote_id=v.vote_id " +
//            "WHERE v.user_id=:userId " +
//            "AND vq.question_id=:qId",
//            nativeQuery=true)
//    @Transactional
//    void deleteUserVotesFromQuestion(@Param("userId") Long userId, @Param("qId") Long qId);
}
