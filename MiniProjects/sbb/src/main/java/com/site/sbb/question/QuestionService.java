package com.site.sbb.question;

import com.site.sbb.DataNotFoundException;
import com.site.sbb.answer.Answer;
import com.site.sbb.user.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {

    //  questionRepository 객체는 @RequiredArgsConstructor에 의해 생성자 방식으로 주입됨.
    private final QuestionRepository questionRepository;

//    public List<Question> getList() {
//        return this.questionRepository.findAll();
//    }

    // 해당 페이지의 데이터만 조회하도록 쿼리가 변경
    // 최신 순으로 정렬하여 데이터 조회
//    public Page<Question> getList(int page) {
//        List<Sort.Order> sorts = new ArrayList<>();
//        // 정렬 조건 추가
//        sorts.add(Sort.Order.desc("createDate"));
//        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
//        return this.questionRepository.findAll(pageable);
//    }
    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        // 정렬 조건 추가
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Question> spec = search(kw);
        return this.questionRepository.findAll(spec, pageable);
//        return  this.questionRepository.findAllByKeyword(kw, pageable);
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    // 질문 데이터 저장
    public void create(String subject, String content, SiteUser user) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setAuthor(user);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }

    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    public void vote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);
        this.questionRepository.save(question);
    }

    /**
     * 검색어를 가리키는 kw를 입력받아 쿼리의 조인문과 where문을 Specification 객체로 생성하여 리턴하는 메서드
     * q : Root 자료형, 기준이 되는 Question 엔티티의 객체를 의미하며 질문 제목과 내용을 검색하기 위해 필요
     * u1 : Question 엔티티와 SiteUser 엔티티를 아우터 조인 (JoinType.LEFT로 아우터 조인하여 만든 SiteUser 엔티티 객체)
     * (u1 객체는 질문 작성자를 검색하기 위해 필요)
     * Question 엔티티와 SiteUser 엔티티는 author 속성으로 연결되어있음 (q.join("author")와 같이 조인해야 함)
     * a : Question 엔티티와 Answer 엔티티를 아우터 조인하여 만든 Answer 엔티티의 객체
     * (Question 엔티티와 Answer 엔티티는 answerList 속성으로 연결되어 있어서 q.join(" answerList")와 같이 조인해야 한다. a 객체는 답변 내용을 검색할 때 필요)
     * u2 : a 객체와 다시 한 번 SiteUser 엔티티와 아우터 조인하여 만든 SiteUser 엔티티의 객체로 답변 작성자를 검색할 때 필요
     */
//    private Specification<Question> search(String kw) {
//        return new Specification<>() {
//            private static final long serialVersionUID = 1L;
//            @Override
//            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                // 중복제거
//                query.distinct(true);
//                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
//                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
//                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
//
//                /**
//                 * like 키워드로 검색하기 위해 제목, 내용, 질문 작성자, 답변 내용, 답변 작성자 각각에 cb.like를 사용하고 최종적으로 cb.or로 OR 검색
//                 */
//                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
//                    cb.like(q.get("content"), "%" + kw + "%"), // 내용
//                    cb.like(u1.get("username"), "%" + kw + "%"), // 질문
//                    cb.like(a.get("content"), "%" + kw + "%"), // 답변
//                    cb.like(u2.get("username"), "%" + kw + "%")); // 답변
//            }
//        };
//    }
    @SuppressWarnings("unused")
    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true); // 중복을 제거
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"), // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"), // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"), // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%")); // 답변 작성자
            }
        };
    }
}
