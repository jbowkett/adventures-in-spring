package info.bowkett.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jbowkett on 28/10/2014.
 */
@Configuration
@EnableAutoConfiguration
public class Main {

  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(Main.class);
    final CustomerRepository repository = context.getBean(CustomerRepository.class);

    repository.save(new Customer("Ian", "McKay"));
    repository.save(new Customer("Dave", "McKay"));
    repository.save(new Customer("Tony", "Colman"));
    repository.save(new Customer("Boris", "Netsky"));
    repository.save(new Customer("Jello", "Biafra"));

    System.out.println("Find all output:");
    for(final Customer customer : repository.findAll()){
      System.out.println(customer);
    }

    System.out.println("Find by id:");
    System.out.println(repository.findOne(1l));

    System.out.println("All Mckays:");
    for (Customer mcKay : repository.findByLastName("McKay")) {
      System.out.println(mcKay);
    }

    context.close();
  }

}
