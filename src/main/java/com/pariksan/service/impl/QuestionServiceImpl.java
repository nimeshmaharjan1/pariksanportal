package com.pariksan.service.impl;

import com.pariksan.helper.ExcelHelper;
import com.pariksan.model.exam.Question;
import com.pariksan.model.exam.Quiz;
import com.pariksan.repo.QuestionRepository;
import com.pariksan.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Set<Question> getQuestions() {
        return new HashSet<>(this.questionRepository.findAll());
    }

    @Override
    public Question getQuestion(Long questionId) {
        return this.questionRepository.findById(questionId).get();
    }

    @Override
    public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
        return this.questionRepository.findByQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        Question question = new Question();
        question.setQuestionId(questionId);
        this.questionRepository.delete(question);
    }

    @Override
    public Question getQuestionAnswer(Long questionId) {
        return this.questionRepository.getById(questionId);
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Question> questions = ExcelHelper.convertExcelToListOfQuestions(file.getInputStream());
            this.questionRepository.saveAll(questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> getAllQuestions() {
        return this.questionRepository.findAll();
    }
}
