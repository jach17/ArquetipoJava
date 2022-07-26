package mx.com.axity.arquetipo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.com.axity.arquetipo.commons.dto.CustomerDto;
import mx.com.axity.arquetipo.commons.enums.ErrorCode;
import mx.com.axity.arquetipo.commons.request.PaginatedRequestDto;
import mx.com.axity.arquetipo.commons.response.PaginatedResponseDto;
import mx.com.axity.arquetipo.commons.util.ValidationUtil;
import mx.com.axity.arquetipo.persistence.CustomerPersistence;
import mx.com.axity.arquetipo.service.CustomerService;
import mx.com.axity.arquetipo.service.util.CustomerDtoTransformer;

/**
 * @author guillermo.segura@axity.com
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService
{

  @Autowired
  private CustomerPersistence customerPersistence;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<CustomerDto> findCustomers( PaginatedRequestDto request )
  {
    log.debug( null );
    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "customerName", "country", "city" ) );

    var paged = this.customerPersistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<CustomerDto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( CustomerDtoTransformer.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CustomerDto findCustomer( Long customerNumber )
  {
    var optional = this.customerPersistence.findById( customerNumber );
    CustomerDto result = null;
    if( optional.isPresent() )
    {
      result = CustomerDtoTransformer.transform( optional.get() );
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CustomerDto create( CustomerDto customer )
  {
    return null;
  }


}
