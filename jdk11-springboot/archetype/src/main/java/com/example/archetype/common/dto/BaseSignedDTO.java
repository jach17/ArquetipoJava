// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class BaseSignedDTO
{
  /** The id */
  protected Long id;

  /** The username who created the entity */
  protected String userCreated;

  /** The timestamp of creation */
  protected String created;

  /** The username who modified the entity */
  protected String userModified;

  /** The timestamp of modification */
  protected String modified;

  /** The active flag of the entity */
  protected boolean active = true;
}
