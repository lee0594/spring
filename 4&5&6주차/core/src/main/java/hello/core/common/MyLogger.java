package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value = "request") //값이 하나이면 'value =' 는 생략 가능
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct //고객 요청이 최초로 들어올 때
    public void init() {
        uuid = UUID.randomUUID().toString();//global하게 unique한 random uuid가 하나 생성됨
        System.out.println("[" + uuid + "] request scope bean create:" + this); //this: 현재 생성된 MyLogger 인스턴스
    }

    @PreDestroy //request scope는 소멸 호출이 됨(고객 요청이 server에서 빠져나갈 때)
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }

}