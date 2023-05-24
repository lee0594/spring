package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;
    //MemberService memberService = new MemberServiceImpl();
    //field에다 memberservice 만듦
    @BeforeEach
    //각 test 실행 전에 무조건 다 실행이 되는 것(appconfig가 잘 만들고 memberservice 할당해주도록)
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    } //test가 두 개 있으면 이게 두 번 돌음

    @Test
    void join(){
        //given: ~런게 주어졌을 때
        Member member = new Member(1L, "memberA",Grade.VIP);

        //when: ~게 했을 때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then: ~게 된다(검증)
        Assertions.assertThat(member).isEqualTo(findMember);
        //Assertions의 org.assetj.core.api 사용
        //test 해서 틀리면 오류가 뜸
    }
}