package com.microservices.service;

import com.microservices.entity.Question;
import com.microservices.entity.QuestionWrapper;
import com.microservices.entity.Response;
import com.microservices.util.ResponseStructure;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
   public ResponseEntity<ResponseStructure<Question>> addQuestion(Question question);

   public ResponseEntity<ResponseStructure<List<Question>>> getAllQuestions();
   public ResponseEntity<ResponseStructure<List<Question>>> getQuestionsByCategory(String category);

   public ResponseEntity<ResponseStructure<List<Integer>>> createQuestionIdsForQuiz(String category, int numberOfQuestions);

   public ResponseEntity<ResponseStructure<List<QuestionWrapper>>> getQuizQuestionsFromIds(List<Integer> questionIds);

   public ResponseEntity<ResponseStructure<Integer>> getScoreForQuiz(List<Response> responses);
}
