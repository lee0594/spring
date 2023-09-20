package hello.core.singleton;

public class SingletonService {

    //1. static 영역에 객체를 딱 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();
    //자기자신을 내부에 private으로 하나 가지고 있는데 'static'으로 가지고 있음: 이렇게 하면 class level에 올라가기 때문에 딱 하나만 존재하게 됨
    //자바가 뜰 때 static 영역에 있는 것을 초기화하면서 new를 생성해서 instance가 참조하여 가지고 있음

    //2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance() { //만들어진 instance 조회
        return instance; //위 코드를 쓴 후 instance의 참조를 꺼낼 수 있는 유일한 방법: 항상 같은 인스턴스 반환
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {
        //private 생성자=>생성을 막아버림 : 외부에서 아래처럼 쓰면 private access이기 때문에 안된다고 compile 오류 남
    }

    /*public static void main(String[] args) { //이런식으로 사용하면 문제점이 생길 수 있음
        SingletonService singletonService1 = new SingletonService();
        SingletonService singletonService2 = new SingletonService();
    }*/
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
