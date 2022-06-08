// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.common.exception;

import com.example.archetype.common.dto.ErrorDTO;

import lombok.Getter;

/**
 * Class Business Exception
 * 
 * @author guillermo.segura@axity.com
 */
@Getter
public class BusinessException extends RuntimeException
{
  private static final long serialVersionUID = -4756891053119822697L;
  private final ErrorDTO error;

  /**
   * Instantiates a new internal error exception.
   */
  public BusinessException()
  {
    super();
    error = new ErrorDTO();
  }

  /**
   * Instantiates a new internal error exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public BusinessException( String message, Throwable cause )
  {
    super( message, cause );
    error = new ErrorDTO();
  }

  /**
   * Instantiates a new internal error exception.
   *
   * @param message the message
   */
  public BusinessException( String message )
  {
    super( message );
    error = new ErrorDTO();
  }

  /**
   * Instantiates a new internal error exception.
   *
   * @param cause the cause
   */
  public BusinessException( Throwable cause )
  {
    super( cause );
    error = new ErrorDTO();
  }
}
