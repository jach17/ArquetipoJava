package com.[%= companylower %].[%= namelower %].persistence.graphql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.[%= companylower %].[%= namelower %].model.[%= namecamel %]DO;

/**
 * Interface [%= namecamel %]GraphQLRepository
 * 
 * @author [%= username %]
 */
public interface [%= namecamel %]GraphQLRepository extends JpaRepository<[%= namecamel %]DO, Long>, QuerydslPredicateExecutor<[%= namecamel %]DO>
{

}
