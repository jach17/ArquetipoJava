package mx.com.axity.arquetipo.commons.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

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

  @NotEmpty(message = "El campo customerName es requerido")
  @Size(max = 50, message = "El campo customerName excede el l\u00EDmite de 50 caracteres")
  private String customerName;

  @NotEmpty(message = "El campo contactLastName es requerido")
  @Size(max = 50, message = "El campo contactLastName el l\u00EDmite de 50 caracteres")
  private String contactLastName;

  @NotEmpty(message = "El campo contactFirstName es requerido")
  @Size(max = 50, message = "El campo contactFirstName el l\u00EDmite de 50 caracteres")
  private String contactFirstName;

  @NotEmpty(message = "El campo phone es requerido")
  @Size(max = 50, message = "El campo phone el l\u00EDmite de 50 caracteres")
  private String phone;

  @NotEmpty(message = "El campo addressLine1 es requerido")
  @Size(max = 50, message = "El campo addressLine1 el l\u00EDmite de 50 caracteres")
  private String addressLine1;

  @Size(max = 50, message = "El campo addressLine2 el l\u00EDmite de 50 caracteres")
  private String addressLine2;

  @NotEmpty(message = "El campo city es requerido")
  @Size(max = 50, message = "El campo city el l\u00EDmite de 50 caracteres")
  private String city;

  @Size(max = 50, message = "El campo state el l\u00EDmite de 50 caracteres")
  private String state;

  @Size(max = 15, message = "El campo postalCode el l\u00EDmite de 15 caracteres")
  private String postalCode;

  @NotEmpty(message = "El campo country es requerido")
  @Size(max = 50, message = "El campo country el l\u00EDmite de 50 caracteres")
  private String country;

  private EmployeeDto salesRepEmployee;

  @PositiveOrZero(message = "El campo creditLimit debe ser un n\u00FAmero positivo")
  @DecimalMax(value = "99999999.99", message = "El campo creditLimit debe ser menor a 99999999.99")
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
