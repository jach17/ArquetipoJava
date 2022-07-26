package mx.com.axity.arquetipo.commons.response.graphql;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author guillermo.segura@axity.com
 *
 */
@Getter
@Setter
public class EmployeeGraphQLDto
{
    private Long employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String jobTitle;
    private EmployeeGraphQLDto reportsTo;
    private OfficeGraphQLDto office;
    private List<CustomerGraphlQLDto> customers;
}
