package mx.com.axity.arquetipo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidad de la tabla customers
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerDO implements Serializable
{
  private static final long serialVersionUID = 1880022978884166676L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customerNumber")
  private Long customerNumber;

  @Column(name = "customerName", length = 50, nullable = false)
  private String customerName;

  @Column(name = "contactLastName", length = 50, nullable = false)
  private String contactLastName;

  @Column(name = "contactFirstName", length = 50, nullable = false)
  private String contactFirstName;

  @Column(name = "phone", length = 50, nullable = false)
  private String phone;

  @Column(name = "addressLine1", length = 50, nullable = false)
  private String addressLine1;

  @Column(name = "addressLine2", length = 50, nullable = true)
  private String addressLine2;

  @Column(name = "city", length = 50, nullable = false)
  private String city;

  @Column(name = "state", length = 50, nullable = true)
  private String state;

  @Column(name = "postalCode", length = 15, nullable = true)
  private String postalCode;

  @Column(name = "country", length = 50, nullable = false)
  private String country;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "salesRepEmployeeNumber", referencedColumnName = "employeeNumber")
  private EmployeeDO salesRepEmployee;

  @Column(name = "creditLimit", precision = 10, scale = 2)
  private BigDecimal creditLimit;

  @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
  private List<PaymentDO> payments;

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
      CustomerDO that = (CustomerDO) object;

      isEquals = Objects.equals( this.customerNumber, that.customerNumber );
    }
    return isEquals;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( this.customerNumber );
  }
}
