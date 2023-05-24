package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) { //psvm 치고 enter 누르면 됨
        //AppConfig appConfig = new AppConfig();
       //MemberService memberService = appConfig.memberService();
        //appconfig에서 다 결정함: appconfig에 memberservice달라고 함: MemberService memberService 인터페이스에는 memberserviceimpl이 들어감
        //MemberService memberService = new MemberServiceImpl();

        //spring은 모든게 applicationContext라고 시작함: 이게 spring container
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //이게 모든 것(@bean이라 된 객체들을) 관리해줌, 이렇게 적으면 AppConfig에 있는 환경 설정 정보를 가지고 spring이 @Bean 붙은 것들을 spring container에 객체 생성한 것들을 넣어서 관리함
        //이제 찾아올 때 appConfig에서 직접 찾아오지 않고 spring container을 통해서 가져옴
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
        //첫번째 parameter: 이름, 두번째 parameter: (반환)type(type이 MemberSeervice다)


        Member member = new Member(1L, "memberA", Grade.VIP);
        //Ctrl + Alt + V: 변수 추출하기 //1뒤의 L: long type이라서 붙여주는 것: 그냥 1 넣으면 compile error 남
        memberService.join(member); //member을 넣음: 회원가입이 돼야함

        Member findMember = memberService.findMember(1L);
        //findMember와 가입한 member의 이름이 똑같으면 원하는대로 된 것
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName()); //soutv하면 자동으로 쳐짐

    }
}

//순수한 자바 코드에 자바 메서드를 실행한 것
//application logic으로 main method test 하는 것은 한계가 있음: 좋은 방법이 아님(맨날 눈으로 확인해야 함)
// =>JUnit이라는 test framework 사용