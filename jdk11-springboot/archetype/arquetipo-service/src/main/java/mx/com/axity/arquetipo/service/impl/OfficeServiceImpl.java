package mx.com.axity.arquetipo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;

import mx.com.axity.arquetipo.commons.dto.OfficeDto;
import mx.com.axity.arquetipo.commons.enums.ErrorCode;
import mx.com.axity.arquetipo.commons.exception.BusinessException;
import mx.com.axity.arquetipo.commons.request.PaginatedRequestDto;
import mx.com.axity.arquetipo.commons.response.GenericResponseDto;
import mx.com.axity.arquetipo.commons.response.PaginatedResponseDto;
import mx.com.axity.arquetipo.model.OfficeDO;
import mx.com.axity.arquetipo.persistence.OfficePersistence;
import mx.com.axity.arquetipo.service.OfficeService;

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

}
