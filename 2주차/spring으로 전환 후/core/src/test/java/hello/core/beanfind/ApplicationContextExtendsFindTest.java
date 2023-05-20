package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate() {
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class); //둘 다 나옴: NoUniqueBeanDefinitionException 오류가 생김
        //위 코드 주석처리하고 아래 코드로 오류 안나게 함
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    } //=>빈 이름 지정

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        //type은 discountpolicy지만 실제 구현 객체는 ratedscountpolicy임
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    //타입으로 조회할 수 있는 또다른 방법: 특정 하위 타입으로 조회하는 방법(안좋은 방법임)
    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType() {
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        //구체적인 타입(ratediscountpolicy)를 지정하면 이건 하나밖에 없으므로(return new ratediscountpolicy) 타입 바로 지정 가능
        assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }

    //부모 타입으로 모두 조회하기
    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String key : beansOfType.keySet()) {
            System.out.println("key= " + key + " value= " + beansOfType.get(key));
        } //실제 test code 짤때는 출력 만들지는 않음: 공부용
    }

    //object type으로 모두 꺼내보기
    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key= " + key + " value= " + beansOfType.get(key));
        }
    } //spring 내부적으로 쓰는 bean끼리 다 튀어나옴(자바 객체는 모든 게 Object type이기 때문)

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            //사실 DiscountPolicy rateDiscountPolicy에서 앞 부분을 RateDiscountPolicy로 해도 상관은 없지만 역할과 구현을 쪼개기 위함
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
        //DiscountPolicy로 조회하면 자식 타입이 두개인 것이 조회됨
    }
}
