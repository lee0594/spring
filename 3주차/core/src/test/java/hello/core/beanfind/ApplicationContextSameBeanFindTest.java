package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByTypeDuplicate(){ //중복으로 뭘 해야하는데 그러면 appconfig를 forward 해야 함:
        //appconfig는 손대기 싫으므로 이 class 내부에 config 파일을 새로 만들 것(samebeanconfig, 여기 안에서만 쓸 것)
        //MemberRepository bean = ac.getBean(MemberRepository.class);
        //spring 입장에서는 둘 중 뭘 선택해야 하는지 모르므로 위 코드 주석 처리하고 아래 코드를 쓰기 전에는 exception이 생김(nouniquebeandefinitionexception)
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        //검증
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기") //한번에 조회하는 방법
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        //ctrl+alt+v 자동으로 변수 추출해줌
        //map: key, value
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2); //아래 두개가 나와야 하므로
    }

    @Configuration
    static class SameBeanConfig { //class안에 class 썼다는 것은 scope을 이 내부로 한정하겠다는 뜻

        @Bean
        public MemberRepository memberRepository1() {
            //각각 다른 parameter로 bean이 생성될 수 있으므로 bean의 이름이 다르고 객체 타입(인스턴스 타입)(class type)이 같을 수 있음
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }


}
