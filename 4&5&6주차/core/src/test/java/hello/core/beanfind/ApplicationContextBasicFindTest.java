package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        //method 안에서 ctrl shift f10하면 그 method가 실행이 됨
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //검증
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){ //유연성이 떨어짐
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        //memberservice 대신 memberserviceimpl 사용 가능: 스프핑 컨테이너에 이러한 객체가 등록되어 있으면 그냥 조회가 됨, but 좋진 않음
        //구현에 의존했기 때문에 좋은 코드는 아님
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회x") //실패 케이스: 아래 예외가 터져야 성공하는 것
    void findBeanByNameX() {
       // ac.getBean("xxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("xxxxx", MemberService.class)); //static import(Assertions.assertThrows에서 alt+enter 이용)
        //오른쪽에 있는 로직을 실행하면 왼쪽의 예외가 터져야 성공하는 것
    }
}
