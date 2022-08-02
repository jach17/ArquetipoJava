package com.axity.arquetipo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axity.arquetipo.commons.dto.OfficeDto;
import com.axity.arquetipo.commons.enums.ErrorCode;
import com.axity.arquetipo.commons.exception.BusinessException;
import com.axity.arquetipo.commons.request.PaginatedRequestDto;
import com.axity.arquetipo.commons.request.graphql.OfficeQueryDto;
import com.axity.arquetipo.commons.response.GenericResponseDto;
import com.axity.arquetipo.commons.response.PaginatedResponseDto;
import com.axity.arquetipo.commons.response.graphql.OfficeGraphQLDto;
import com.axity.arquetipo.model.OfficeDO;
import com.axity.arquetipo.model.QOfficeDO;
import com.axity.arquetipo.persistence.OfficePersistence;
import com.axity.arquetipo.persistence.graphql.OfficeGraphQLRepository;
import com.axity.arquetipo.service.OfficeService;
import com.axity.arquetipo.service.helper.OfficePredicate;
import com.axity.arquetipo.service.util.OfficeGraphQLDtoTransformer;
import com.github.dozermapper.core.Mapper;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import graphql.schema.DataFetchingEnvironment;

/**
 * @author guillermo.segura@axity.com
 */
@Service
@Transactional
public class OfficeServiceImpl implements OfficeService
{
  private static final Logger LOG = LoggerFactory.getLogger( OfficeServiceImpl.class );

  @Autowired
  private OfficePersistence officePersistence;
  
  @Autowired
  private OfficeGraphQLRepository officeGraphQLRepository;

  @Autowired
  private Mapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<OfficeDto> findOffices( PaginatedRequestDto request )
  {
    LOG.debug( "%s", request );

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "country", "city" ) );

    var paged = this.officePersistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<OfficeDto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( this.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<OfficeDto> find( String officeCode )
  {
    GenericResponseDto<OfficeDto> response = null;

    var optional = this.officePersistence.findById( officeCode );
    if( optional.isPresent() )
    {
      response = new GenericResponseDto<>();
      response.setBody( this.transform( optional.get() ) );
    }

    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<OfficeDto> create( OfficeDto office )
  {

    var saved = this.find( office.getOfficeCode() );
    if( saved != null )
    {
      throw new BusinessException( ErrorCode.OFFICE_ALREADY_EXISTS );
    }

    OfficeDO entity = new OfficeDO();
    this.mapper.map( office, entity );

    this.officePersistence.save( entity );

    return new GenericResponseDto<>( office );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( OfficeDto office )
  {
    var optional = this.officePersistence.findById( office.getOfficeCode() );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.OFFICE_NOT_FOUND );
    }

    var entity = optional.get();
    entity.setTerritory( office.getTerritory() );
    entity.setCountry( office.getCountry() );
    entity.setCity( office.getCity() );
    entity.setAddressLine1( office.getAddressLine1() );
    entity.setAddressLine2( office.getAddressLine2() );
    entity.setPhone( office.getPhone() );
    entity.setPostalCode( office.getPostalCode() );
    this.officePersistence.save( entity );

    return new GenericResponseDto<>( true );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( String officeCode )
  {
    var optional = this.officePersistence.findById( officeCode );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.OFFICE_NOT_FOUND );
    }

    var entity = optional.get();
    this.officePersistence.delete( entity );

    return new GenericResponseDto<>( true );
  }

  private OfficeDto transform( OfficeDO entity )
  {
    OfficeDto dto = null;
    if( entity != null )
    {
      dto = this.mapper.map( entity, OfficeDto.class );
    }
    return dto;
  }

  @Override
  public List<OfficeGraphQLDto> findGraphQL( OfficeQueryDto query, DataFetchingEnvironment env )
  {
    var office = QOfficeDO.officeDO;
    var predicates = this.getEmployeePredicates( office, query, true );

    return this.processPredicate( predicates, query );
  }

  private List<Predicate> getEmployeePredicates( QOfficeDO office, OfficeQueryDto wrapper,
      boolean checkSupervisor )
  {
    var predicates = new ArrayList<Predicate>();
    if( Stream.of( wrapper, wrapper.getQuery() ).allMatch( Objects::nonNull ) )
    {
      OfficePredicate.evaluateOfficeOfficeCode( wrapper.getQuery().getOfficeCode(), office, predicates );
      OfficePredicate.evaluateOfficeOfficeCode( wrapper.getQuery().getOfficeCode(), office, predicates );
      OfficePredicate.evaluateOfficeCity( wrapper.getQuery().getCity(), office, predicates );
      OfficePredicate.evaluateOfficePhone( wrapper.getQuery().getPhone(), office, predicates );
      OfficePredicate.evaluateOfficeAddressLine1( wrapper.getQuery().getAddressLine1(), office, predicates );
      OfficePredicate.evaluateOfficeAddressLine2( wrapper.getQuery().getAddressLine2(), office, predicates );
      OfficePredicate.evaluateOfficeState( wrapper.getQuery().getState(), office, predicates );
      OfficePredicate.evaluateOfficeCountry( wrapper.getQuery().getCountry(), office, predicates );
      OfficePredicate.evaluateOfficePostalCode( wrapper.getQuery().getPostalCode(), office, predicates );
      OfficePredicate.evaluateOfficeTerritory( wrapper.getQuery().getTerritory(), office, predicates );
    }

    return predicates;
  }
  
  private List<OfficeGraphQLDto> processPredicate( List<Predicate> predicates, OfficeQueryDto query )
  {
    var offices = new ArrayList<OfficeGraphQLDto>();

    if( predicates.isEmpty() )
    {
      var result = this.officeGraphQLRepository.findAll();
      offices
          .addAll( result.stream().map( officeDO -> OfficeGraphQLDtoTransformer.transform( officeDO ) )
              .collect( Collectors.toList() ) );
    }
    else
    {
      var result = this.officeGraphQLRepository.findAll( ExpressionUtils.allOf( predicates ) );
      result.forEach( officeDO -> offices.add( OfficeGraphQLDtoTransformer.transform( officeDO ) ) );
    }

    return offices;
  }
}
