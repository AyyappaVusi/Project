package com.microservices.serviceImpl;

import com.microservices.entity.Question;
import com.microservices.entity.QuestionWrapper;
import com.microservices.entity.Response;
import com.microservices.repository.QuestionRepository;
import com.microservices.service.QuestionService;
import com.microservices.util.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    @Override
    public ResponseEntity<ResponseStructure<Question>> addQuestion(Question question) {
        ResponseStructure<Question> responseStructure=new ResponseStructure<Question>();
       responseStructure.setStatus(HttpStatus.CREATED.value());
       responseStructure.setData(questionRepository.save(question));
       responseStructure.setMessage("Question added successfully");
       return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
    }

    @Override
    public  ResponseEntity<ResponseStructure<List<Question>>> getAllQuestions() {

            ResponseStructure<List<Question>> responseStructure = new ResponseStructure<List<Question>>();
            responseStructure.setStatus(HttpStatus.FOUND.value());
            responseStructure.setData(questionRepository.findAll());
            responseStructure.setMessage("Questions found succesfully");
            return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);

    }
    @Override
    public ResponseEntity<ResponseStructure<List<Question>>> getQuestionsByCategory(String category){

            ResponseStructure<List<Question>> responseStructure = new ResponseStructure<List<Question>>();
            responseStructure.setStatus(HttpStatus.FOUND.value());
            responseStructure.setData(questionRepository.getQuestionsByCategory(category));
            responseStructure.setMessage("Questions found successfully");
            return new ResponseEntity<>(responseStructure, HttpStatus.FOUND);

    }

    @Override
    public ResponseEntity<ResponseStructure<List<Integer>>> createQuestionIdsForQuiz(String category, int numberOfQuestions) {
        List<Integer> questionList=questionRepository.getRandomQuestionBasedOnCategory(category,numberOfQuestions);

        ResponseStructure<List<Integer>> responseStructure=new ResponseStructure<>();
        responseStructure.setStatus(HttpStatus.CREATED.value());
        responseStructure.setData(questionList);
        responseStructure.setMessage("Quiz created successfully");
        return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseStructure<List<QuestionWrapper>>> getQuizQuestionsFromIds
            (List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Integer queNum:questionIds) {
            questions.add(questionRepository.findById(queNum).get());
        }
        for(Question question:questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setQueNum(question.getQueNum());
            wrapper.setQuestion(question.getQuestion());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }
        ResponseStructure<List<QuestionWrapper>> responseStructure=new ResponseStructure<>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setData(wrappers);
        responseStructure.setMessage("Quiz questions found successfully");
        return new ResponseEntity<>(responseStructure,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ResponseStructure<Integer>> getScoreForQuiz(List<Response> responses) {
        int marks=0;
        for(Response response:responses){
            if(response.getResponse().equals(questionRepository.findById(response.getQueNum())
                    .get().getCorrectAnswer()))
                marks++;
        }
        ResponseStructure<Integer> responseStructure=new ResponseStructure<>();
        responseStructure.setStatus(HttpStatus.OK.value());
        responseStructure.setData(marks);
        responseStructure.setMessage("This is the final result");
        return new ResponseEntity<>(responseStructure,HttpStatus.OK) ;
    }
}
