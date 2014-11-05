package info.bowkett.spring.jpa;

import javax.persistence.*;

/**
 * Created by jbowkett on 28/10/2014.
 */
@Entity
@Access(AccessType.FIELD)
public class Customer {

  protected Customer(){}

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String firstName, lastName;

  public Customer(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', lastName='%s']",
        id, firstName, lastName);
  }
}
