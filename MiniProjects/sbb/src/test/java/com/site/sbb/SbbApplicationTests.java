package com.site.sbb;

import com.site.sbb.question.Question;
import com.site.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 질문 엔티티의 데이터를 생성할 때 리포지터리 (여기서는 QuestionRepository) 가 필요하므로
 * @Autowired 애너테이션을 통해 스프링의 ‘의존성 주입 (DI)’ 이라는 기능을 사용하여
 * QuestionRepository의 객체를 주입
 */
@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa() {
		Question q1 = new Question();
		q1.setSubject("what is sbb?");
		q1.setContent("i want to know about sbb");
		q1.setCreateDate(LocalDateTime.now());

		// 첫번째 질문 리포지토리에 저장
		this.questionRepository.save(q1);

		Question q2 = new Question(); q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자 동 으 로 생 성 되 나 요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);
	}

	@Test
	void testJpa2() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		// 리스트 인덱스로 검색
		Question q = all.get(0);
		assertEquals("what is sbb?", q.getSubject());
	}

	@Test
	void testJpa3() {
		// id로 검색
		Optional<Question> oq = this.questionRepository.findById(1);
		if (oq.isPresent()) {
			Question q = oq.get();
			assertEquals("what is sbb?", q.getSubject());
		}
	}

	@Test
	void testJpa4() {
		// 리포지토리 커스텀 함수 만들어서 객체 반환
		Question q = this.questionRepository.findBySubject("what is sbb?");
		assertEquals(1, q.getId());
	}

	/**
	 * where 문에 and 연산자가 사용되어 subject와 content 열을 조회하는 것을 확인
	 */
	@Test
	void testJpa5() {
		Question q = this.questionRepository.findBySubjectAndContent(
				"what is sbb?", "i want to know about sbb");
		assertEquals(1, q.getId());
	}

	/**
	 * subject 열 값들 중에 특정 문자열을 포함하는 데이터 조회
	 * SQL 에서는 특정 문자열을 포함한 데이터를 열에서 찾을 때 Like를 사용
	 */
	@Test
	void testJpa6() {
//		List<Question> qList = this.questionRepository.findBySubjectLike("%sbb");
//		Question q = qList.get(0);
//		System.out.println(q.getSubject());
//		assertEquals("what is sbb?", q.getSubject());
	}
}
