package com.axity.arquetipo.commons.aspectj;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.axity.arquetipo.commons.enums.ErrorCode;
import com.axity.arquetipo.commons.exception.BusinessException;
import com.axity.arquetipo.commons.exception.ValidationException;
import com.axity.arquetipo.commons.response.GenericResponseDto;
import com.axity.arquetipo.commons.response.HeaderDto;

import lombok.extern.slf4j.Slf4j;

/**
 * Aspecto para interceptar los errores
 * 
 * @author guillermo.segura@axity.com
 */
@Aspect
@Component
@Slf4j
public class JsonResponseHandlerInterceptor implements HandlerInterceptor
{
  private static final String ARCHETYPE_ERROR = "arquetipo.error.%d";

  @Autowired
  private Environment env;

  @Value("${arquetipo.allowTrace}")
  private boolean allowTrace;

  @Around("execution (* com.axity.arquetipo.controller.*.*(..))"
      + " and @annotation(com.axity.arquetipo.commons.aspectj.JsonResponseInterceptor)")
  public Object interceptMethodAdvice( ProceedingJoinPoint pjp ) throws Throwable
  {
    Object result = null;
    try
    {
      if( log.isDebugEnabled() )
      {
        log.debug( pjp.toLongString() );
      }
      result = pjp.proceed();
    }
    catch( ValidationException e )
    {
      log.error( e.getMessage(), e );

      var genericResponse = new GenericResponseDto<>();
      var header = new HeaderDto();
      header.setMessage( this.env.getProperty( String.format( ARCHETYPE_ERROR, e.getCode() ) ) );
      header.setCode( e.getCode() );
      genericResponse.setHeader( header );
      result = new ResponseEntity<>( genericResponse, HttpStatus.BAD_REQUEST );
    }
    catch( BusinessException e )
    {
      log.error( e.getMessage(), e );

      var genericResponse = new GenericResponseDto<>();
      var header = new HeaderDto();
      header.setMessage( this.env.getProperty( String.format( ARCHETYPE_ERROR, e.getCode() ) ) );
      header.setCode( e.getCode() );
      header.setDetail( e.getMessage() );
      genericResponse.setHeader( header );
      result = new ResponseEntity<>( genericResponse, HttpStatus.INTERNAL_SERVER_ERROR );
    }
    catch( Exception e )
    {
      log.error( e.getMessage(), e );

      var genericResponse = new GenericResponseDto<>();
      var header = new HeaderDto();
      header.setMessage( this.env.getProperty( String.format( ARCHETYPE_ERROR, ErrorCode.UNKNOWN_ERROR.getCode() ) ) );
      header.setCode( ErrorCode.UNKNOWN_ERROR.getCode() );

      if( this.allowTrace )
      {
        header.setDetail( String.format( "%s %s", e.getMessage(), ExceptionUtils.getStackTrace( e ) ) );
      }
      else
      {
        header.setDetail( e.getMessage() );
      }
      genericResponse.setHeader( header );
      result = new ResponseEntity<>( genericResponse, HttpStatus.INTERNAL_SERVER_ERROR );
    }
    return result;
  }

}
