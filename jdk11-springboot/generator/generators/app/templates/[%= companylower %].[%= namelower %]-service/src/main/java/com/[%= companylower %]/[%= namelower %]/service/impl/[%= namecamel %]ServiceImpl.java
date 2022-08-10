package com.[%= companylower %].[%= namelower %].service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.enums.ErrorCode;
import com.[%= companylower %].[%= namelower %].commons.exception.BusinessException;
import com.[%= companylower %].[%= namelower %].commons.request.MessageDto;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.request.graphql.[%= namecamel %]QueryDto;
import com.[%= companylower %].[%= namelower %].commons.response.GenericResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.PaginatedResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.graphql.[%= namecamel %]GraphQLDto;
import com.[%= companylower %].[%= namelower %].model.[%= namecamel %]DO;
import com.[%= companylower %].[%= namelower %].model.Q[%= namecamel %]DO;
import com.[%= companylower %].[%= namelower %].persistence.[%= namecamel %]Persistence;
import com.[%= companylower %].[%= namelower %].persistence.graphql.[%= namecamel %]GraphQLRepository;
import com.[%= companylower %].[%= namelower %].service.[%= namecamel %]Service;
import com.[%= companylower %].[%= namelower %].service.helper.[%= namecamel %]Predicate;
import com.[%= companylower %].[%= namelower %].service.util.[%= namecamel %]GraphQLDtoTransformer;
import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;

/**
 * Class [%= namecamel %]ServiceImpl
 * 
 * @author [%= username %]
 */
@Service
@Transactional
@Slf4j
public class [%= namecamel %]ServiceImpl implements [%= namecamel %]Service
{
  @Autowired
  private [%= namecamel %]Persistence [%= namelower %]Persistence;

  @Autowired
  private [%= namecamel %]GraphQLRepository [%= namelower %]GraphQLRepository;

  @Autowired
  private Mapper mapper;

  @Autowired
  private KafkaTemplate<Object, Object> template;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<[%= namecamel %]Dto> find[%= namecamel %]s( PaginatedRequestDto request )
  {
    log.debug( "%s", request );

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "country", "city" ) );

    var paged = this.[%= namelower %]Persistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<[%= namecamel %]Dto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( this.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<[%= namecamel %]Dto> find( Integer [%= namelower %]Id )
  {
    GenericResponseDto<[%= namecamel %]Dto> response = null;

    var optional = this.[%= namelower %]Persistence.findById( [%= namelower %]Id );
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
  public GenericResponseDto<[%= namecamel %]Dto> create( [%= namecamel %]Dto [%= namelower %] )
  {

    [%= namecamel %]DO entity = new [%= namecamel %]DO();
    this.mapper.map( [%= namelower %], entity );
    entity.setId(null);

    this.[%= namelower %]Persistence.save( entity );

    [%= namelower %].setId(entity.getId());

    Gson gson = new GsonBuilder().create();
    var message = new MessageDto( "Se creo entidad:", gson.toJson( [%= namelower %] ) );

    this.template.send( "test", gson.toJson( message ) );

    return new GenericResponseDto<>( [%= namelower %] );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( [%= namecamel %]Dto [%= namelower %] )
  {
    var optional = this.[%= namelower %]Persistence.findById( [%= namelower %].getId() );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.[%= nameupper %]_NOT_FOUND );
    }

    var entity = optional.get();
    entity.setTerritory( [%= namelower %].getTerritory() );
    entity.setCountry( [%= namelower %].getCountry() );
    entity.setCity( [%= namelower %].getCity() );
    entity.setAddressLine1( [%= namelower %].getAddressLine1() );
    entity.setAddressLine2( [%= namelower %].getAddressLine2() );
    entity.setPhone( [%= namelower %].getPhone() );
    entity.setPostalCode( [%= namelower %].getPostalCode() );
    this.[%= namelower %]Persistence.save( entity );

    return new GenericResponseDto<>( true );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer [%= namelower %]Id )
  {
    var optional = this.[%= namelower %]Persistence.findById( [%= namelower %]Id );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.[%= nameupper %]_NOT_FOUND );
    }

    var entity = optional.get();
    this.[%= namelower %]Persistence.delete( entity );

    return new GenericResponseDto<>( true );
  }

  @Override
  public List<[%= namecamel %]GraphQLDto> findGraphQL( [%= namecamel %]QueryDto query, DataFetchingEnvironment env )
  {
    var [%= namelower %] = Q[%= namecamel %]DO.[%= namelower %]DO;
    var predicates = this.get[%= namecamel %]Predicates( [%= namelower %], query );

    return this.processPredicate( predicates, query );
  }

  /**
   * {@inheritDoc}
   */
  public void processMessage( String message )
  {
    log.info( "-> Mensaje: {}", message );

    Gson gson = new GsonBuilder().create();

    var obj = gson.fromJson( message, MessageDto.class );

    log.info( "{}", obj.getJson() );
    var [%= namelower %] = gson.fromJson( obj.getJson(), [%= namecamel %]Dto.class );
    log.info( "{}", [%= namelower %] );
  }

  private [%= namecamel %]Dto transform( [%= namecamel %]DO entity )
  {
    [%= namecamel %]Dto dto = null;
    if( entity != null )
    {
      dto = this.mapper.map( entity, [%= namecamel %]Dto.class );
    }
    return dto;
  }

  private List<Predicate> get[%= namecamel %]Predicates( Q[%= namecamel %]DO [%= namelower %], [%= namecamel %]QueryDto wrapper )
  {
    var predicates = new ArrayList<Predicate>();
    if( Stream.of( wrapper, wrapper.getQuery() ).allMatch( Objects::nonNull ) )
    {
      [%= namecamel %]Predicate.evaluate[%= namecamel %]Id( wrapper.getQuery().getId(), [%= namelower %], predicates );
      [%= namecamel %]Predicate.evaluate[%= namecamel %]City( wrapper.getQuery().getCity(), [%= namelower %], predicates );
      [%= namecamel %]Predicate.evaluate[%= namecamel %]Phone( wrapper.getQuery().getPhone(), [%= namelower %], predicates );
      [%= namecamel %]Predicate.evaluate[%= namecamel %]AddressLine1( wrapper.getQuery().getAddressLine1(), [%= namelower %], predicates );
      [%= namecamel %]Predicate.evaluate[%= namecamel %]AddressLine2( wrapper.getQuery().getAddressLine2(), [%= namelower %], predicates );
      [%= namecamel %]Predicate.evaluate[%= namecamel %]State( wrapper.getQuery().getState(), [%= namelower %], predicates );
      [%= namecamel %]Predicate.evaluate[%= namecamel %]Country( wrapper.getQuery().getCountry(), [%= namelower %], predicates );
      [%= namecamel %]Predicate.evaluate[%= namecamel %]PostalCode( wrapper.getQuery().getPostalCode(), [%= namelower %], predicates );
      [%= namecamel %]Predicate.evaluate[%= namecamel %]Territory( wrapper.getQuery().getTerritory(), [%= namelower %], predicates );
    }

    return predicates;
  }

  private List<[%= namecamel %]GraphQLDto> processPredicate( List<Predicate> predicates, [%= namecamel %]QueryDto query )
  {
    var [%= namelower %]s = new ArrayList<[%= namecamel %]GraphQLDto>();

    Pageable pageable = extractPageable( query );

    if( predicates.isEmpty() )
    {
      var result = this.[%= namelower %]GraphQLRepository.findAll( pageable );
      [%= namelower %]s.addAll( result.stream().map( [%= namelower %]DO -> [%= namecamel %]GraphQLDtoTransformer.transform( [%= namelower %]DO ) )
          .collect( Collectors.toList() ) );
    }
    else
    {
      var result = this.[%= namelower %]GraphQLRepository.findAll( ExpressionUtils.allOf( predicates ), pageable );
      result.forEach( [%= namelower %]DO -> [%= namelower %]s.add( [%= namecamel %]GraphQLDtoTransformer.transform( [%= namelower %]DO ) ) );
    }

    return [%= namelower %]s;
  }

  private Pageable extractPageable( [%= namecamel %]QueryDto query )
  {
    Pageable pageable;
    if( query.getSize() > 0 )
    {
      pageable = PageRequest.of( query.getPage(), query.getSize() );
    }
    else
    {
      pageable = PageRequest.of( 0, 50 );
    }
    return pageable;
  }
}
