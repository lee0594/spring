package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest { //JUnit5부터는 void와 전체에 public 설정 안해도 됨

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        //ctrl+alt+v 하면 변수명이 자동 생성됨
        //빈 정의된 이름 등록
        for (String beanDefinitionName : beanDefinitionNames){ //리스트나 배열 있을 때 iter+enter 하면 for문이 자동으로 만들어짐
            Object bean = ac.getBean(beanDefinitionName);
            //type을 지정하지 않았기 때문에 Object가 꺼내짐
            System.out.println("name = " + beanDefinitionName + " object = " + bean); //soutv: 자동으로 print문 생성됨
        }
    }
    
    @Test //ctrl+d 하면 자동으로 복붙됨
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames){ //리스트나 배열 있을 때 iter+enter 하면 for문이 자동으로 만들어짐
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//bean 하나하나에 대한 메타데이터 정보

            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                //role이 세가지가 있는데 이거는 스프링 내부 뭔가를 하기 위해 등록한 빈들이 아니라 내가 applicaiton을 개발하기 위해서 등록한 빈(외부 library 포함)만 출력하게 함
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
