package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A 사용자가 10000원 주문
        statefulService1.order("userA",10000);
        //ThreadB: B 사용자가 10000원 주문
        statefulService2.order("userB",20000);

        //ThreadA: 사용자 A가 주문 금액 조회
        int price = statefulService1.getPrice();
        //ThreadA: 사용자 A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        System.out.println("price = " + price);
        //A,B가 같은 인스턴스(객체) 사용하기 때문에 발생

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        //위 annotationconfigapplicationcontext 스프링 컨테이너는 이거 하나만 생성해서 사용
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}