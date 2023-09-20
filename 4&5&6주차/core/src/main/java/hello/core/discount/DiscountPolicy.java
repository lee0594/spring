package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
    /*
        @return 할인 대상 금액
     */
    int discount(Member member, int price); //f2 누르면 오류난 곳으로 바로 이동함: 여기서 import 선택할 수 있음
}
