package com.site.sbb.question;

import com.site.sbb.question.Question;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

//    Question findBySubject(String subject);
//    Question findBySubjectAndContent(String subject, String content);
//    List<Question> findBySubjectLike(String subject);
    // Pageable 객체를 입력받아 Page<Question> 타입 객체를 리턴하는 메서드
//    Page<Question> findAll(Pageable pageable);
    // Specification 통해 질문 조회 (Specification과 Pageable 객체를 사용하여 DB 에서 Question 엔티티 를 조회한 결과를 페이징하여 반환)
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);

    // 직접 쿼리 사용하여 질문 조회하는 방법
//    @Query("select "
//        + "distinct q "
//            + "from Question q"
//            + "left outer join SiteUser u1 on q.author=u1 "
//            + "left outer join Answer a on a.question=q "
//            + "left outer join SiteUser u2 on a.author=u2 "
//            + "where "
//            + " q.subject like %:kw% "
//            + " or q.content like %:kw% "
//            + " or u1.username like %:kw%"
//            + " or a.content like %:kw%"
//            + " or u2.username like %:kw%")
//    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);

}

