package com.example.project.repositories;

import com.example.project.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByQuestionId(Long id);
    List<Question> findByAuthor_UserId(Long uid);

    @Query(
            value="SELECT * FROM Questions q ORDER BY q.timestamp DESC",
            nativeQuery = true
    )
    List<Question> findAllByDate();

    @Modifying
    @Query(
            value="SELECT * FROM Questions q WHERE q.title LIKE CONCAT('%', :arg, '%')",
            nativeQuery = true
    )
    @Transactional
    List<Question> findAllByText(@Param("arg") String arg);

    @Modifying
    @Query(
            value="SELECT * FROM Questions q " +
                    "JOIN tag_question tq ON q.question_id=tq.question_id " +
                    "JOIN tags t ON t.tag_id=tq.tag_id " +
                    "WHERE t.tag_name in :tags " +
                    "AND q.title LIKE CONCAT('%', :arg, '%')",
            nativeQuery = true
    )
    @Transactional
    List<Question> findAllByTagsAndText(@Param("tags") List<String> tags, @Param("arg") String arg);

    @Modifying
    @Query(
            value = "SELECT * FROM vote_question vq " +
                    "JOIN vote v ON v.vote_id=vq.vote_id " +
                    "WHERE vq.question_id=:qId " +
                    "AND v.vote_type=1 ",

            nativeQuery = true
    )
    @Transactional
    List<Question> getNumberLikes(@Param("qId") Long qId);

    @Modifying
    @Query(
            value = "SELECT * FROM vote_question vq " +
                    "JOIN vote v ON v.vote_id=vq.vote_id " +
                    "WHERE vq.question_id=:qId " +
                    "AND v.vote_type=0 ",

            nativeQuery = true
    )
    @Transactional
    List<Question> getNumberDislikes(@Param("qId") Long qId);
}
