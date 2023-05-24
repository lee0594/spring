package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인(이 상태에서는 MemoryMemberRepositoy까지 총 4개의 객체가 새로 생성된 것)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        //soutv 치면 됨
        //but 원래 test는 눈으로 확인하는게 아니라 assertions 등으로 자동화되게 만들어야 함

        //memberService 1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void SingletonServiceTest() {
        //new SingletonService(); //실행 안됨: singletonservice가 private access를 가지고 있으므로
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();
    
        //참조값 확인 : 같은 객체 인스턴스가 반환됨(참조값이 같음)
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        //same: '==' : 객체 인스턴스가 같은지 참조를 비교하는 것
        //equal: java의 equals 비교하는 것(내용 비교)
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        //AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext((AppConfig.class));
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService 1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
