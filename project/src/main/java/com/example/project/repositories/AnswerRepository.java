package com.example.project.repositories;

import com.example.project.entities.Answer;
import com.example.project.entities.Question;
import com.example.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestion_QuestionId(Long questionId);


    @Modifying
    @Query(
            value = "SELECT * FROM vote_answer vq " +
                    "JOIN vote v ON v.vote_id=vq.vote_id " +
                    "WHERE vq.answer_id=:aId " +
                    "AND v.vote_type=1 ",

            nativeQuery = true
    )
    @Transactional
    List<Answer> getNumberLikes(@Param("aId") Long aId);

    @Modifying
    @Query(
            value = "SELECT * FROM vote_answer vq " +
                    "JOIN vote v ON v.vote_id=vq.vote_id " +
                    "WHERE vq.answer_id=:aId " +
                    "AND v.vote_type=0 ",

            nativeQuery = true
    )
    @Transactional
    List<Answer> getNumberDislikes(@Param("aId") Long aId);

}
