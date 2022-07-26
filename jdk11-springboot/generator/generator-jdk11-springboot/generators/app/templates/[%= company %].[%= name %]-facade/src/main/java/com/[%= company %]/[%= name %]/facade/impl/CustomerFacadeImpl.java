package com.[%= company %].[%= name %].facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.[%= company %].[%= name %].commons.dto.CustomerDto;
import com.[%= company %].[%= name %].commons.request.PaginatedRequestDto;
import com.[%= company %].[%= name %].commons.response.PaginatedResponseDto;
import com.[%= company %].[%= name %].facade.CustomerFacade;
import com.[%= company %].[%= name %].service.CustomerService;

/**
 * @author guillermo.segura@axity.com
 *
 */
@Service
public class CustomerFacadeImpl implements CustomerFacade
{
  @Autowired
  private CustomerService customerService;

  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<CustomerDto> findCustomers( PaginatedRequestDto request )
  {
    return this.customerService.findCustomers(request);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CustomerDto findCustomer( Long customerNumber )
  {
    return this.customerService.findCustomer( customerNumber );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CustomerDto create( CustomerDto customer )
  {
    return this.customerService.create(customer);
  }

}
