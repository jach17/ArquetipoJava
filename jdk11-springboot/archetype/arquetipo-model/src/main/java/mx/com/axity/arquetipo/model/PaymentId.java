package mx.com.axity.arquetipo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

/**
 * Llave compuesta de la tabla payments
 * 
 * @author guillermo.segura@axity.com
 */
@Embeddable
@Getter
@Setter
public class PaymentId implements Serializable
{
  private static final long serialVersionUID = -494884062754177378L;

  @Column(name = "customerNumber", nullable = false)
  private Long customerNumber;

  @Column(name = "checkNumber", nullable = false)
  private String checkNumber;

  /**
   * Constructor default
   */
  public PaymentId()
  {
  }

  /**
   * Constructor por argumentos
   * 
   * @param customerNumber
   * @param checkNumber
   */
  public PaymentId( Long customerNumber, String checkNumber )
  {
    this.customerNumber = customerNumber;
    this.checkNumber = checkNumber;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals( Object object )
  {
    boolean isEquals = false;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      PaymentId that = (PaymentId) object;

      // isEquals = this.customerNumber.equals( that.checkNumber );
      isEquals = Objects.equals( this.customerNumber, that.customerNumber );
      isEquals = isEquals && Objects.equals( this.checkNumber, that.checkNumber );
    }
    return isEquals;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode()
  {
    return Objects.hash( this.customerNumber, this.checkNumber );
  }

}
