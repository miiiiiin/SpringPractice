package com.site.sbb.question;

import com.site.sbb.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 일반적으로 엔티티를 만들 때에는 Setter 메서드를 사용하지 않기를 권장됨.
 * 왜냐하면 엔티티는 데이터베이스와 바로 연결되므로 데이터를 자유롭게 변경할 수 있는
 * Setter 메서드를 허용하는 것 이 안전하지 않다고 판단하기 때문
 * 엔티티는 생성자에 의해서만 엔티티의 값을 저장할 수 있게 하고, 데이터를 변경해야 할 경우에 는 메서드를 추가로 작성
 */
@Getter
@Setter
@Entity
public class Question {
    // @Id 애너테이션은 id 속성을 기본키로 지정
    /**
     *  데이터를 저장할 때 해당 속성에 값을 일일이 입력하지 않아 도자동으로1씩증가하여저장된다.
     *  strategy = GenerationType.IDENTITY는 고유한번호 를 생성하는 방법을 지정하는 부분으로,
     *  GenerationType.IDENTITY는 해당 속성만 별도로 번호가 차례대로 늘어나도록 할 때 사용
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    /**
     * ‘텍스트’를 열 데이터로 넣을 수 있음을 의미하고, 글자수를 제한할 수 없는 경우에 사용
     */
    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    /**
     * 질문에서 답변을 참조하려면 question.getAnswerList()를 호출
     * mappedBy는 참조 엔티티의 속성명을 정의함. (Answer 엔티티에서 Question 엔티티를 참조한 속성인 question을 mappedBy에 전달해야 함.)
     */
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
