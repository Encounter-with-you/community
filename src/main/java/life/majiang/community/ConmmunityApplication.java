package life.majiang.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("life.majiang.community.mapper")
public class ConmmunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConmmunityApplication.class, args);
    }

}
