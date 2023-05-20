package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) { //psvm + enter
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L; //member 저장
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member); //memory 객체에 넣어놓음: 찾아서 쓸 수 있도록

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order= " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice()); //9000원 출력
    }
}

//이렇게 main method로 하는 것은 좋지 않으므로 자동화된 test 사용