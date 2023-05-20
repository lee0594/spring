package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();
    //field에다 memberservice 만듦
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