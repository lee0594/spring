package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

//Ctrl+N: class 등 다 검색 가능

//아래 네 줄은 @Qualifier에 나와있는 기능들
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy { //이 애노테이션을 쓰면 위 기능들이 스프링 컨테이너 안에서 다 동작함
}
