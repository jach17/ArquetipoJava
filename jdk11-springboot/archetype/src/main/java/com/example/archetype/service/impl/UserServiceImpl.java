// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.archetype.common.dto.UserDTO;
import com.example.archetype.common.exception.BusinessException;
import com.example.archetype.mapper.UserMapper;
import com.example.archetype.model.UserDO;
import com.example.archetype.persistence.UserPersistence;
import com.example.archetype.service.UserService;

/**
 * @author guillermo.segura@axity.com
 */
@Service
public class UserServiceImpl implements UserService
{
  @Autowired
  private UserPersistence userPersistence;

  @Autowired
  private UserMapper userMapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<UserDTO> getAll()
  {
    List<UserDO> entities = this.userPersistence.findAllActive();

    return entities.stream().map( o -> userMapper.toDto( o ) ).collect( Collectors.toList() );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UserDTO getById( Long id )
  {
    Optional<UserDO> op = this.userPersistence.findById( id );

    UserDTO dto = null;
    if( op.isPresent() )
    {
      dto = userMapper.toDto( op.get() );
    }

    return dto;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UserDTO save( UserDTO user )
  {
    Optional<UserDO> op = this.userPersistence.findByUsername( user.getUsername() );
    if( op.isPresent() )
    {
      var msg = String.format( "Ya esta registrado el usuario %s", user.getUsername() );
      BusinessException be = new BusinessException( msg );
      be.getError().setBadRequest( true );
      be.getError().setCode( "101" );
      be.getError().setDescription( "Registro duplicado" );
      throw be;
    }

    var entity = new UserDO();
    entity.setName( user.getName() );
    entity.setUsername( user.getUsername() );
    entity.setEmail( user.getEmail() );
    entity.setUserCreated( user.getUserCreated() );
    entity.setUserModified( user.getUserCreated() );
    Calendar cal = Calendar.getInstance();

    entity.setCreated( cal.getTime() );
    entity.setModified( cal.getTime() );

    return userMapper.toDto( this.userPersistence.saveAndFlush( entity ) );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UserDTO update( UserDTO user )
  {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void delete( UserDTO user )
  {
    // TODO Auto-generated method stub

  }

}
