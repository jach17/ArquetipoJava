package mx.com.axity.arquetipo.commons.enums;

/**
 * @author guillermo.segura@axity.com
 */
public enum ErrorCode
{

  UNKNOWN_ERROR(0),

  // Validacion
  OFFICE_ALREADY_EXISTS(100), OFFICE_NOT_FOUND(101);

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
