package com.microservices.service;

import com.microservices.entity.QuestionWrapper;
import com.microservices.entity.Response;
import com.microservices.util.ResponseStructure;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {
   public ResponseEntity<ResponseStructure<List<Integer>>> createQuiz(String category, int numQue, String title);

 public  ResponseEntity<ResponseStructure<List<QuestionWrapper>>> getQuiz(int id);

   public ResponseEntity<ResponseStructure<Integer>> submitQuiz(int id, List<Response> responses);
}
