package com.site.sbb.answer;

import com.site.sbb.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    /**
     * 질문 엔티티와 연결된 속성이라는 것을 답변 엔티티에 표시해야 함.
     * Answer 엔티티의 question 속성에 @ManyToOne 애너테이션을 추가해 질문 엔티티와 연결
     * 게시판 서비스에서는 하나의 질문에 답변은 여러 개가 달릴 수 있음.
     * @ManyToOne 애너테이션을 사용하면 N:1 관계 나타낼 수 있음.
     * Answer(답변) 엔티티의 question 속성과 Question(질문) 엔티티가 서로 연결된다.
     * (실제 데이터베이스에서는 외래키 (foreign key) 관계가 생성됨.
     */
    @ManyToOne
    private Question question;
}
