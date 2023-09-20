package hello.core.singleton;

public class StatefulService { //ctrl+shift+t: 새로운 test 생성 가능

    private int price; //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}


/*
public class StatefulService {

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}

 */

// int userAprice = statefulService1.order.... 이렇게 test에서 쓰면 정상적으로 나옴
// :지역변수는 공유되지 않기 때문