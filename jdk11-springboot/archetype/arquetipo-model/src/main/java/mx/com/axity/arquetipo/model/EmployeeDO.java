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
 * Entidad de la tabla employees
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
public class EmployeeDO implements Serializable
{
  private static final long serialVersionUID = 787741772876024676L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "employeeNumber")
  private Long employeeNumber;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "extension")
  private String extension;

  @Column(name = "email")
  private String email;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "officeCode", referencedColumnName = "officeCode")
  private OfficeDO office;

  @ManyToOne(fetch = FetchType.EAGER, optional = true)
  @JoinColumn(name = "reportsTo", referencedColumnName = "employeeNumber")
  private EmployeeDO reportsTo;

  @OneToMany(mappedBy = "reportsTo", fetch = FetchType.LAZY)
  private List<EmployeeDO> employees;

  @Column(name = "jobTitle")
  private String jobTitle;

  @OneToMany(mappedBy = "salesRepEmployee", fetch = FetchType.LAZY)
  private List<CustomerDO> customers;

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
      EmployeeDO that = (EmployeeDO) object;

      isEquals = Objects.equals( this.employeeNumber, that.employeeNumber );
    }
    return isEquals;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash( this.employeeNumber );
  }

}
