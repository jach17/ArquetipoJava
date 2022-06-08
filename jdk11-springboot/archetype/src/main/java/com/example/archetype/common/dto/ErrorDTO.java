// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.common.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class ErrorDTO implements Serializable
{
  private static final long serialVersionUID = -1985461609196983455L;

  /** The code */
  private String code;

  /** The description. */
  private String description;

  /** The trace. */
  private String trace;

  /** The bad request. */
  private boolean badRequest;
}
