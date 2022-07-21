package mx.com.axity.arquetipo.persistence.graphql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.graphql.data.GraphQlRepository;

import mx.com.axity.arquetipo.model.EmployeeDO;

/**
 * @author guillermo.segura@axity.com
 */
@GraphQlRepository
public interface EmployeeGraphQLRepository
    extends JpaRepository<EmployeeDO, Long>, QuerydslPredicateExecutor<EmployeeDO>
{

}
