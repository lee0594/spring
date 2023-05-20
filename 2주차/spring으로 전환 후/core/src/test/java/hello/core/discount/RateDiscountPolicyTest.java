package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    //ratediscountpolicy가 정말 10% 할인이 되는지 볼 것임
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
    //JUnit5는 displayname이라고 해서 한글로 이름 쓸 수 있음
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    void vip_o(){
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member,10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_x(){ //test가 실패하는 case
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member,10000);
        //then
        //Assertions.assertThat(discount).isEqualTo(1000); //원래 할인이 되면 안됨 : 오류남
        assertThat(discount).isEqualTo(0);
    }
    //java: Assertions를 static import 하는 것이 좋음(alt+enter에서 on demand static... 선택)
    //java에서 static import 알아보기
}