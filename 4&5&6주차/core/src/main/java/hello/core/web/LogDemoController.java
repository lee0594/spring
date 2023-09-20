package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //생성자가 자동 주입됨
public class LogDemoController {

    private final LogDemoService logDemoService;
    //private final MyLogger myLogger; //request scope
    /*private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo") //log-demo url로 요청이 오면
    @ResponseBody //view 화면 없이 문자를 바로 반환하고 싶음
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString(); //고객이 어떤 url로 요청했는지 알 수 있음
        MyLogger myLogger = myLoggerProvider.getObject();
        //진짜 필요한 시점에 myLogger을 받음
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
     */

    /*
    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo") //log-demo url로 요청이 오면
    @ResponseBody //view 화면 없이 문자를 바로 반환하고 싶음
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString(); //고객이 어떤 url로 요청했는지 알 수 있음
        MyLogger myLogger = myLoggerProvider.getObject();
        //진짜 필요한 시점에 myLogger을 받음
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }*/

    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}