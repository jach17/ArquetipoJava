package mx.com.axity.arquetipo.commons.response.graphql;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 *
 */
@Getter
@Setter
public class EmployeeResponseDto
{
    private Long employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String jobTitle;
    private EmployeeResponseDto reportsTo;
    private OfficeResponseDto office;
//  employeeNumber: ID
//  lastName: String
//  firstName: String
//  extension: String
//  email: String
//  office: Office
//  reportsTo: Employee
//  jobTitle: String
}
