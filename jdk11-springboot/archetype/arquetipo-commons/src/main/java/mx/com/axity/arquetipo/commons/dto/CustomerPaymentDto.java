package mx.com.axity.arquetipo.commons.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class CustomerPaymentDto implements Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = -8317095941740237365L;

  private String customerName;

  private String contactLastName;

  private String contactFirstName;

  private String phone;

  private Date paymentDate;

  private BigDecimal amount;

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
