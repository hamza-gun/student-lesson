package tr.gov.sgk.demo.studentlesson;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan
//        (basePackages = "studentlesson.log")
public class StudentLessonApplication extends SpringBootServletInitializer{
    public static void main(String[] args) {
        SpringApplication.run(StudentLessonApplication.class, args);

    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    // Log4j yapılandırmasını yüklemek için
    @PostConstruct
    public void init() {
        System.setProperty("log4j.configurationFile", "classpath:log4j2.xml");
    }

}
