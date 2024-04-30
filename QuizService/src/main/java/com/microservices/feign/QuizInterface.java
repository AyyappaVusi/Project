package com.microservices.feign;

import com.microservices.entity.QuestionWrapper;
import com.microservices.entity.Response;
import com.microservices.util.ResponseStructure;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("questions/generate")
    public ResponseEntity<ResponseStructure<List<Integer>>> createQuestionIdsForQuiz
            (@RequestParam String category, @RequestParam int numberOfQuestions);
    @PostMapping("questions/getquestions")
    public ResponseEntity<ResponseStructure<List<QuestionWrapper>>> getQuizQuestionsFromIds
            (@RequestBody List<Integer> questionIds);
    @PostMapping("questions/getscore")
    public ResponseEntity<ResponseStructure<Integer>> getScoreForQuiz(@RequestBody List<Response> responses);
}
