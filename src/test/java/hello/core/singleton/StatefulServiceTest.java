package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //Thread A 사용자 주문 10000원
        int userPriceA = statefulService1.order("userA", 10000);
        //Thread B 사용자 주문 20000원
        int userPriceB = statefulService2.order("userB", 20000);

        //Thread A 사용자 주문 가격 조회
        //int price = statefulService1.getPrice();
        System.out.println("userPriceA = " + userPriceA);

        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(2000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
