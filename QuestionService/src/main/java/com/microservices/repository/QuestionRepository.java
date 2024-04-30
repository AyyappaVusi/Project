package com.microservices.repository;

import com.microservices.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> getQuestionsByCategory(String category);


    @Query(value = "SELECT q.que_num FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numberOfQuestions",nativeQuery = true)
    List<Integer> getRandomQuestionBasedOnCategory(String category, int numberOfQuestions);
}
