package com.axity.arquetipo.commons.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.axity.arquetipo.commons.enums.ErrorCode;

class BusinessExceptionTest
{

  @Test
  void testBusinessException()
  {
    var ex = new BusinessException();
    assertNotNull( ex );
  }

  @Test
  void testBusinessExceptionStringThrowable()
  {
    var ex = new BusinessException( "An error has occurred!!!", new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testBusinessExceptionString()
  {
    var ex = new BusinessException( "An error has occurred!!!" );
    assertNotNull( ex );
  }

  @Test
  void testBusinessExceptionThrowable()
  {
    var ex = new BusinessException( new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testBusinessExceptionInt()
  {
    var ex = new BusinessException( 0 );
    assertNotNull( ex );
  }

  @Test
  void testBusinessExceptionIntString()
  {
    var ex = new BusinessException( 0, "An error has occurred!!!" );
    assertNotNull( ex );
  }

  @Test
  void testBusinessExceptionIntThrowable()
  {
    var ex = new BusinessException( 0, new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testBusinessExceptionIntStringThrowable()
  {
    var ex = new BusinessException( 0, "An error has occurred!!!", new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testBusinessExceptionErrorCode()
  {
    var ex = new BusinessException( ErrorCode.UNKNOWN_ERROR );
    assertNotNull( ex );
  }

  @Test
  void testBusinessExceptionErrorCodeString()
  {
    var ex = new BusinessException( ErrorCode.UNKNOWN_ERROR, "An error has occurred!!!" );
    assertNotNull( ex );
  }

  @Test
  void testBusinessExceptionErrorCodeThrowable()
  {
    var ex = new BusinessException( ErrorCode.UNKNOWN_ERROR, new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testBusinessExceptionErrorCodeStringThrowable()
  {
    var ex = new BusinessException( ErrorCode.UNKNOWN_ERROR, "An error has occurred!!!",
        new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testGetCode()
  {
    var ex = new BusinessException( ErrorCode.UNKNOWN_ERROR, "An error has occurred!!!",
        new IllegalArgumentException() );
    assertNotNull( ex );
    assertEquals( ErrorCode.UNKNOWN_ERROR.getCode(), ex.getCode() );
  }

}
