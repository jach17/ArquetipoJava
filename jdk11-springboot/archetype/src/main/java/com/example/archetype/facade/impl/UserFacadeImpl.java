// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.archetype.common.dto.UserDTO;
import com.example.archetype.facade.UserFacade;
import com.example.archetype.service.UserService;

/**
 * @author guillermo.segura@axity.com
 */
@Service
public class UserFacadeImpl implements UserFacade
{
  @Autowired
  private UserService userService;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<UserDTO> getAll()
  {
    return this.userService.getAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UserDTO getById( Long id )
  {
    return this.userService.getById( id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UserDTO save( UserDTO user )
  {
    return this.userService.save( user );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UserDTO update( UserDTO user )
  {
    return this.userService.update( user );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void delete( UserDTO user )
  {
    this.userService.delete( user );
  }
}
