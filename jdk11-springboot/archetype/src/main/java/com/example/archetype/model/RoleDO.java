// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity class Role DO.
 * 
 * @author guillermo.segura@axity.com
 */
@Entity
@Table(name = "TCFLC_Role")
@Getter
@Setter
public class RoleDO extends AbstractSignedEntity<RoleDO>
{
  private static final long serialVersionUID = -8166150633022360876L;
  /** The name of the user */
  @Column(name = "nb_name", nullable = false, length = 100)
  private String name;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public int compareTo( RoleDO o )
  {
    return new CompareToBuilder().append( this.id, o.id ).toComparison();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals( Object object )
  {
    boolean isEquals;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      final RoleDO that = (RoleDO) object;
      isEquals = Objects.equals( this.id, that.id );
    }
    else
    {
      isEquals = false;
    }

    return isEquals;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode()
  {
    return Objects.hash(this.id);
  }

}
