package com.[%= company %].[%= name %].persistence.graphql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.graphql.data.GraphQlRepository;

import com.[%= company %].[%= name %].model.EmployeeDO;

/**
 * @author guillermo.segura@axity.com
 */
@GraphQlRepository
public interface EmployeeGraphQLRepository
    extends JpaRepository<EmployeeDO, Long>, QuerydslPredicateExecutor<EmployeeDO>
{

}
