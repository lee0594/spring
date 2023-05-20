package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) { //psvm 치고 enter 누르면 됨
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        //appconfig에서 다 결정함: appconfig에 memberservice달라고 함: MemberService memberService 인터페이스에는 memberserviceimpl이 들어감
        //MemberService memberService = new MemberServiceImpl();
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