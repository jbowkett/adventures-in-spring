package info.bowkett.spring.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 *
 * Created by jbowkett on 28/10/2014.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
  public List<Customer> findByLastName(String lastName);
}
