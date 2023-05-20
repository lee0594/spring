package hello.core.order;
//오류나면 무조건 alt + enter
public interface OrderService { //주문 서비스 인터페이스
    Order createOrder(Long memberId, String itemName, int itemPrice);
    //주문생성: return 값이 order(주문 결과)
}
