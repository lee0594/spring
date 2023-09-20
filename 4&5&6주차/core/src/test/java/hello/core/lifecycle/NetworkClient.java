package hello.core.lifecycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {

    private String url; //접속해야 할 서버의 url

    public NetworkClient() { //생성자
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출하는 메서드
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) { //연결된 상태에서 call 부를 수 있다고 가정, 연결된 서버에 message를 보냄
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출하는 메서드
    public void disconnect() {
        System.out.println("close: " + url);
    }

    /*
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
    */

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
