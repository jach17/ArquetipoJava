// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.model;

import java.io.Serializable;

/**
 * Abstract class entity
 * 
 * @author guillermo.segura@axity.com
 */
public abstract class AbstractEntity<T> implements Comparable<T>, Serializable
{

  private static final long serialVersionUID = -2456741561761268136L;

  /**
   * The entities must implement the method equals(Object):boolean
   * 
   * @param obj
   */
  public abstract boolean equals( Object object );

  /**
   * The entities must implement the method hashCode():int
   */
  public abstract int hashCode();
}
