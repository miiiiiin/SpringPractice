package com.site.sbb.question;

import com.site.sbb.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);
    // Pageable 객체를 입력받아 Page<Question> 타입 객체를 리턴하는 메서드
    Page<Question> findAll(Pageable pageable);

}
