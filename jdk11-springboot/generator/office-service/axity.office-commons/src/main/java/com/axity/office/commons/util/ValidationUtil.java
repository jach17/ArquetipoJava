package com.axity.office.commons.util;

import org.apache.commons.lang3.StringUtils;

import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.ValidationException;

/**
 * Validation util class
 * 
 * @author username@axity.com
 */
public final class ValidationUtil
{
  private ValidationUtil()
  {
  }

  public static void checkRequired( String value, String fieldName )
  {
    if( StringUtils.isBlank( value ) )
    {
      throw new ValidationException( ErrorCode.REQUIRED_FIELD, fieldName );
    }
  }

  public static void checkMaxLength( String value, String fieldName, int maxLength )
  {
    if( !StringUtils.isBlank( value ) && value.length() > maxLength )
    {
      throw new ValidationException( ErrorCode.EXCEEDS_MAX_LENGTH, fieldName );
    }
  }

  public static void checkRequiredMaxLength( String value, String fieldName, int maxLength )
  {
    checkRequired( value, fieldName );
    checkMaxLength( value, fieldName, maxLength );
  }
}
