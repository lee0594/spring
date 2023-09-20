package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;

    @Override //alt+enter로 오류 고쳐서 이거 생성
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){ //alt +enter로 error 고쳐서 import도 함
            //enum type: '==' 쓰는 게 맞음
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
