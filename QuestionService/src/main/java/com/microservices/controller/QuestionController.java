package com.microservices.controller;

import com.microservices.entity.Question;
import com.microservices.entity.QuestionWrapper;
import com.microservices.entity.Response;
import com.microservices.service.QuestionService;
import com.microservices.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
   private QuestionService questionService;
    @Autowired
    Environment environment;//Load balancing example
    @PostMapping
    public ResponseEntity<ResponseStructure<Question>> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Question>>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("/{category}")
    public ResponseEntity<ResponseStructure<List<Question>>> getQuestionsByCategory
            (@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }
    @GetMapping("generate")
    public ResponseEntity<ResponseStructure<List<Integer>>> createQuestionIdsForQuiz
            (@RequestParam String category,@RequestParam int numberOfQuestions){
      return  questionService.createQuestionIdsForQuiz(category,numberOfQuestions);
    }
    @PostMapping("getquestions")
    public ResponseEntity<ResponseStructure<List<QuestionWrapper>>> getQuizQuestionsFromIds
            (@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));// load balancing example
        return questionService.getQuizQuestionsFromIds(questionIds);
    }
    @PostMapping("getscore")
    public ResponseEntity<ResponseStructure<Integer>> getScoreForQuiz(@RequestBody List<Response> responses){
        return questionService.getScoreForQuiz(responses);
    }
}
