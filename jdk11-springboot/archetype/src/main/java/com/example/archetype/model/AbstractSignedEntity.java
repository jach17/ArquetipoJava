// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AbstractSignedEntity<T> extends AbstractEntity<T>
{
  private static final long serialVersionUID = 6447819994167044131L;

  /** The id */
  @Id
  @Column(name = "cd_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;
  
  /** The username who created the entity */
  @Column(name = "nb_userCreated", nullable = false, length = 200)
  protected String userCreated;

  /** The timestamp of creation */
  @Column(name = "ts_created", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  protected Date created;

  /** The username who modified the entity */
  @Column(name = "nb_userModified", nullable = false, length = 200)
  protected String userModified;

  /** The timestamp of modification */
  @Column(name = "ts_modified", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  protected Date modified;

  /** The active flag of the entity */
  @Column(name = "st_active", nullable = false)
  protected boolean active = true;
}
