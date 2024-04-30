package com.microservices.controller;

import com.microservices.entity.QuestionWrapper;
import com.microservices.entity.QuizDto;
import com.microservices.entity.Response;
import com.microservices.service.QuizService;
import com.microservices.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping ("create")
    public ResponseEntity<ResponseStructure<List<Integer>>> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategory(),quizDto.getNumberOfQuestions(),quizDto.getTitle());
    }
    @GetMapping("get/{id}")
    public ResponseEntity<ResponseStructure<List<QuestionWrapper>>> getQuiz(@PathVariable int id){
        return quizService.getQuiz(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<ResponseStructure<Integer>> submitQuiz(@PathVariable int id,@RequestBody List<Response> responses){
        return quizService.submitQuiz(id,responses);
    }
}
