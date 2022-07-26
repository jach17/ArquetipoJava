package mx.com.axity.arquetipo.commons.response.graphql;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class CustomerGraphlQLDto
{
  private String customerName;

  private String contactLastName;

  private String contactFirstName;

  private String phone;

  private String addressLine1;

  private String addressLine2;

  private String city;

  private String state;

  private String postalCode;

  private String country;

  private Long salesRepEmployee;

  private BigDecimal creditLimit;
}
