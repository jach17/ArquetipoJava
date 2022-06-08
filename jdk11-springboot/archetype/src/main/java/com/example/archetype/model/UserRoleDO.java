// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.CompareToBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity class User Role DO.
 * @author guillermo.segura@axity.com
 *
 */
@Entity
@Table(name = "TRFLC_UserRole")
@Getter
@Setter
public class UserRoleDO extends AbstractSignedEntity<UserRoleDO>
{
  private static final long serialVersionUID = 7391951370215291173L;

  /** The user */
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "cd_user", referencedColumnName = "cd_id")
  private UserDO user;

  /** The role */
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "cd_role", referencedColumnName = "cd_id")
  private RoleDO role;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public int compareTo( UserRoleDO o )
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
      final UserRoleDO that = (UserRoleDO) object;
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
