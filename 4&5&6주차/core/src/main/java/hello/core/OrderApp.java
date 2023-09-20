package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) { //psvm + enter

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();

        //MemberService memberService = new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L; //member 저장
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member); //memory 객체에 넣어놓음: 찾아서 쓸 수 있도록

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order= " + order);
        //System.out.println("order.calculatePrice = " + order.calculatePrice()); //9000원 출력
    }
}

//이렇게 main method로 하는 것은 좋지 않으므로 자동화된 test 사용