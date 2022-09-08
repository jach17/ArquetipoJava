package com.axity.arquetipo.commons.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.axity.arquetipo.commons.enums.ErrorCode;

class ValidationExceptionTest
{

  @Test
  void testValidationException()
  {
    var ex = new ValidationException();
    assertNotNull( ex );
  }

  @Test
  void testValidationExceptionStringThrowable()
  {
    var ex = new ValidationException( "An error has occurred!!!", new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testValidationExceptionString()
  {
    var ex = new ValidationException( "An error has occurred!!!" );
    assertNotNull( ex );
  }

  @Test
  void testValidationExceptionThrowable()
  {
    var ex = new ValidationException( new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testValidationExceptionInt()
  {
    var ex = new ValidationException( 0 );
    assertNotNull( ex );
  }

  @Test
  void testValidationExceptionIntString()
  {
    var ex = new ValidationException( 0, "An error has occurred!!!" );
    assertNotNull( ex );
  }

  @Test
  void testValidationExceptionIntThrowable()
  {
    var ex = new ValidationException( 0, new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testValidationExceptionIntStringThrowable()
  {
    var ex = new ValidationException( 0, "An error has occurred!!!", new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testValidationExceptionErrorCode()
  {
    var ex = new ValidationException( ErrorCode.UNKNOWN_ERROR );
    assertNotNull( ex );
  }

  @Test
  void testValidationExceptionErrorCodeString()
  {
    var ex = new ValidationException( ErrorCode.UNKNOWN_ERROR, "An error has occurred!!!" );
    assertNotNull( ex );
  }

  @Test
  void testValidationExceptionErrorCodeThrowable()
  {
    var ex = new ValidationException( ErrorCode.UNKNOWN_ERROR, new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testValidationExceptionErrorCodeStringThrowable()
  {
    var ex = new ValidationException( ErrorCode.UNKNOWN_ERROR, "An error has occurred!!!",
        new IllegalArgumentException() );
    assertNotNull( ex );
  }

  @Test
  void testGetCode()
  {
    var ex = new ValidationException( ErrorCode.UNKNOWN_ERROR, "An error has occurred!!!",
        new IllegalArgumentException() );
    assertNotNull( ex );
    assertEquals( ErrorCode.UNKNOWN_ERROR.getCode(), ex.getCode() );
  }

}
