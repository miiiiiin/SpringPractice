package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // StatefulService를 싱글톤으로 사용하게 되어 발생하는 좋지 않은 예

        // Thread A 사용자 : 10000원 주문
        int userA = statefulService1.order("userA", 10000);
        // Thread B 사용자 : 20000원 주문
        int userB = statefulService2.order("userB", 20000);// 공유되는 필드가 있어 특정 클라이언트가 값을 변경할 수 있음.(위험)
// price가 변경되어 찍히는 일이 발생하지 않도록 멀티쓰레드 문제 유의

        // Thread A 사용자 : 사용자 A 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000); // 망한 예

        System.out.println("userA = " + userA);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}