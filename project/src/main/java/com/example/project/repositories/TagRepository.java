package com.example.project.repositories;

import com.example.project.entities.Tag;
import com.example.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByTagName(String tagName);

    @Modifying
    @Query(value="insert into tag_question (question_id, tag_id) values (:qId, :tId)",
            nativeQuery=true)
    @Transactional
    void insertTagQuestion(@Param("qId") Long qId, @Param("tId") Long tId);

    @Modifying
    @Query("SELECT DISTINCT t.tagName FROM Tag t JOIN t.tagsQuestion tq WHERE tq.question.questionId=:qId")
    @Transactional
    public List<String> findTagByQuestion(@Param("qId")  Long qid);

}
