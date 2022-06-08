// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.api.aop;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import com.example.archetype.common.exception.BusinessException;

/**
 * @author guillermo.segura@axity.com
 */
@Aspect
@Configuration
public class PersistenceAspect
{
  @Around(value = "within(@com.example.archetype.api.aop.PersistenceIntercept *)")
  public Object execute( ProceedingJoinPoint joinPoint ) throws Throwable
  {
    Object response;
    try
    {
      System.out.println( "----before" );
      response = joinPoint.proceed();
      System.out.println( "----after" );
    }
    catch( BusinessException e )
    {
      throw e;
    }
    catch( Throwable e )
    {
      BusinessException be = new BusinessException(e.getMessage(), e);
      be.getError().setCode("100");
      be.getError().setDescription( e.getMessage() );
      be.getError().setTrace( ExceptionUtils.getStackTrace( e ) );
      
      throw be;
    }
    return response;
  }
}
