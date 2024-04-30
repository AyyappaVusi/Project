package com.microservices.serviceImpl;
import com.microservices.entity.QuestionWrapper;
import com.microservices.entity.Quiz;
import com.microservices.entity.Response;
import com.microservices.feign.QuizInterface;
import com.microservices.repository.QuizRepository;
import com.microservices.service.QuizService;
import com.microservices.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    QuizInterface quizInterface;
    @Override
    public ResponseEntity<ResponseStructure<List<Integer>>> createQuiz(String category, int numQue, String title) {
        List<Integer> questionIds=quizInterface.createQuestionIdsForQuiz(category,numQue).getBody().getData();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizRepository.save(quiz);

        ResponseStructure<List<Integer>> responseStructure=new ResponseStructure<>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setData(questionIds);
        responseStructure.setMessage("Quiz created successfully");
        return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseStructure<List<QuestionWrapper>>> getQuiz(int id) {
        Quiz quiz=quizRepository.findById(id).get();

            List<Integer> questionIds = quiz.getQuestionIds();
            return quizInterface.getQuizQuestionsFromIds(questionIds);
    }

    @Override
    public ResponseEntity<ResponseStructure<Integer>> submitQuiz(int id, List<Response> responses) {
//
        return quizInterface.getScoreForQuiz(responses);
    }
}
