// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.api.aop;

import java.io.Serializable;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.archetype.common.dto.ErrorDTO;
import com.example.archetype.common.exception.BusinessException;

/**
 * @author guillermo.segura@axity.com
 */
@Aspect
@Configuration
public class ControllerAspect
{
  @SuppressWarnings("unchecked")
  @Around(value = "within (@com.example.archetype.api.aop.Intercept *)")
  public ResponseEntity<Serializable> execute( ProceedingJoinPoint joinPoint ) throws Throwable
  {
    ResponseEntity<Serializable> response;
    try
    {
      response = (ResponseEntity<Serializable>) joinPoint.proceed();
    }
    catch( BusinessException e )
    {
      if( e.getError().isBadRequest() )
      {
        response = new ResponseEntity<>( e.getError(), HttpStatus.BAD_REQUEST );
      }
      else
      {
        response = new ResponseEntity<>( e.getError(), HttpStatus.INTERNAL_SERVER_ERROR );
      }
    }
    catch( Throwable e )
    {
      ErrorDTO error = new ErrorDTO();
      error.setCode( "ERR" );
      var msg = String.format( "Unknown error: %s", e.getMessage() );
      error.setDescription( msg );
      error.setTrace( ExceptionUtils.getStackTrace( e ) );
      response = new ResponseEntity<>( error, HttpStatus.INTERNAL_SERVER_ERROR );
    }
    return response;
  }
}
