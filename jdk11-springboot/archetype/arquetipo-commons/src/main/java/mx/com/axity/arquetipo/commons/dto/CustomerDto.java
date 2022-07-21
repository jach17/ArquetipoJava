package mx.com.axity.arquetipo.commons.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class CustomerDto implements Serializable
{
  private static final long serialVersionUID = 239515572451245702L;

  private Long customerNumber;

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

  private EmployeeDto salesRepEmployee;

  private BigDecimal creditLimit;

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    return gson.toJson( this );
  }

}
