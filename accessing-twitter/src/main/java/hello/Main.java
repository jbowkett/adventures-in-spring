package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jbowkett on 05/11/2014.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }
}
