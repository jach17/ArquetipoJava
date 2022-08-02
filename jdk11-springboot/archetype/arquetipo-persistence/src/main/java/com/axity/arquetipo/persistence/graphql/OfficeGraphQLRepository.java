package com.axity.arquetipo.persistence.graphql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.axity.arquetipo.model.OfficeDO;

public interface OfficeGraphQLRepository extends JpaRepository<OfficeDO, Long>, QuerydslPredicateExecutor<OfficeDO>
{

}
