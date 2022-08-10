package com.[%= companylower %].[%= namelower %].commons.enums;

/**
 * Error code enumeration
 * 
 * @author [%= username %]
 */
public enum ErrorCode
{

  UNKNOWN_ERROR(0), REQUIRED_FIELD(1), EXCEEDS_MAX_LENGTH(2),

  // Validation errors
  OFFICE_ALREADY_EXISTS(100), OFFICE_NOT_FOUND(101),

  CUSTOMER_ALREADY_EXISTS(200), CUSTOMER_NOT_FOUND(201);

  private int code;

  private ErrorCode( int code )
  {
    this.code = code;
  }

  /**
   * @return the code
   */
  public int getCode()
  {
    return code;
  }

}
