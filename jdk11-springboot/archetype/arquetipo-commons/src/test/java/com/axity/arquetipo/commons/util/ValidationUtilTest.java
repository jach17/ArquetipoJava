package com.axity.arquetipo.commons.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.axity.arquetipo.commons.exception.ValidationException;

class ValidationUtilTest
{

  @Test
  void testCheckRequired()
  {
    assertThrows( ValidationException.class, () -> ValidationUtil.checkRequired( null, "data" ) );
    assertThrows( ValidationException.class, () -> ValidationUtil.checkRequired( "", "data" ) );
    ValidationUtil.checkRequired( "ok", "data" );
  }

  @Test
  void testCheckMaxLength()
  {
    assertThrows( ValidationException.class,
      () -> ValidationUtil.checkMaxLength( "Lorem ipsum dolor sit amet", "data", 10 ) );
    ValidationUtil.checkMaxLength( null, "data", 10 );
    ValidationUtil.checkMaxLength( "", "data", 10 );
    ValidationUtil.checkMaxLength( "ok", "data", 10 );
  }
}
