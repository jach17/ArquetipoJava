package mx.com.axity.arquetipo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entidad de la tabla offices
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "offices")
@Getter
@Setter
public class OfficeDO implements Serializable
{
  private static final long serialVersionUID = -5035830587965574416L;

  @Id
  @Column(name = "officeCode", length = 10)
  private String officeCode;

  @Column(name = "city", nullable = false, length = 50)
  private String city;

  @Column(name = "phone", nullable = false, length = 50)
  private String phone;

  @Column(name = "addressLine1", nullable = false, length = 50)
  private String addressLine1;

  @Column(name = "addressLine2", nullable = true, length = 50)
  private String addressLine2;

  @Column(name = "state", nullable = true, length = 50)
  private String state;

  @Column(name = "country", nullable = false, length = 50)
  private String country;

  @Column(name = "postalCode", nullable = false, length = 15)
  private String postalCode;

  @Column(name = "territory", nullable = false, length = 10)
  private String territory;

  @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
  private List<EmployeeDO> employees;

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
      OfficeDO that = (OfficeDO) object;

      isEquals = Objects.equals( this.officeCode, that.officeCode );
    }
    return isEquals;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode()
  {
    return Objects.hash( this.officeCode );
  }

}
