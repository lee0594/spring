package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@ToString은 객체.toString() 메서드 대체하는 어노테이션
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String [] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdas");

        //String name = helloLombok.getName();
        //System.out.println("name = " + name);
        System.out.println("helloLombok = " + helloLombok);
        //helloLombok = HelloLombok(name=asdas, age=0) 출력
    }
}
