package hello.core.order;


public class Order { //field는 member Id와 item 이름

    private Long memberId;
    private String itemName;
    private int itemPrice; //주문할 때 주문서의 결과는 아이템 가격
    private int discountPrice; //주문서의 결과는 또한 할인가격
    //주문서에서 할인 다 끝났을 때 만들어지는 객체

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    //계산 로직
    public int calculatePrice(){ //최종 계산되 ㄴ가격
        return itemPrice - discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    //계산할 때 보기 쉽게 하기 위해서
    @Override
    public String toString() { //객체를 출력하면 이 결과가 쭉 나옴
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
    //우리가 order라는 객체 자체를 출력하면 거기에 있는 toString()이 출력됨
    //: 편하게 data를 모을 수 있음
}
