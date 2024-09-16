package com.site.sbb.answer;

import com.site.sbb.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service // 답변 저장하려면 서비스 필요
public class AnswerService {
    private final AnswerRepository answerRepository;

    // 답변 저장
    public void create(Question question, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
}
