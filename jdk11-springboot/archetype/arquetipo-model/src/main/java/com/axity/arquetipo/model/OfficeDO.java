package com.axity.arquetipo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  @Column(name = "cd_id", length = 10)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "nb_city", nullable = false, length = 50)
  private String city;

  @Column(name = "nb_phone", nullable = false, length = 50)
  private String phone;

  @Column(name = "nb_addressLine1", nullable = false, length = 50)
  private String addressLine1;

  @Column(name = "nb_addressLine2", nullable = true, length = 50)
  private String addressLine2;

  @Column(name = "nb_state", nullable = true, length = 50)
  private String state;

  @Column(name = "nb_country", nullable = false, length = 50)
  private String country;

  @Column(name = "nb_postalCode", nullable = false, length = 15)
  private String postalCode;

  @Column(name = "nb_territory", nullable = false, length = 10)
  private String territory;

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

      isEquals = Objects.equals( this.id, that.id );
    }
    return isEquals;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode()
  {
    return Objects.hash( this.id );
  }

}
