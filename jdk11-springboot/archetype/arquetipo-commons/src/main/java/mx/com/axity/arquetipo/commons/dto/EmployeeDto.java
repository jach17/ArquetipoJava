package mx.com.axity.arquetipo.commons.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto de transferencia de Empleado
 * 
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class EmployeeDto
{
  private Long employeeNumber;
  private String lastName;
  private String firstName;
  private String extension;
  private String email;
  private String officeCode;
  private Long reportsTo;
  private String jobTitle;

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    return gson.toJson( this );
  }
}
