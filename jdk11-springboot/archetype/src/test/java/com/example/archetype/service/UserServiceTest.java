// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

import com.example.archetype.common.dto.UserDTO;
import com.example.archetype.common.exception.BusinessException;

/**
 * @author guillermo.segura@axity.com
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class UserServiceTest
{

  @Autowired
  private UserService userService;

  @Test
  void testGetAll()
  {
    var users = this.userService.getAll();
    assertNotNull( users );
    assertFalse( users.isEmpty() );
  }

  @ParameterizedTest
  @ValueSource(longs = { 1, 2, 3, 4, 5 })
  void testGetById( long id )
  {
    var user = this.userService.getById( id );
    assertNotNull( user );
  }

  @Test
  void testGetById_NotFound()
  {
    assertNull( this.userService.getById( 99999L ) );
  }

  @Test
  void testSave()
  {
    int n = this.userService.getAll().size();
    var user = new UserDTO();
    user.setName( "Martha Sanchez" );
    user.setUsername( "martha.sanchez" );
    user.setEmail( "martha.sanchez@axity.com" );
    user.setUserCreated( "system" );

    var saved = this.userService.save( user );

    assertNotNull( this.userService.getById( saved.getId() ) );
    assertEquals( n + 1, this.userService.getAll().size() );
  }

  @Test
  void testSaveExistingUsername()
  {
    var user = new UserDTO();
    user.setName( "Juan Perez" );
    user.setUsername( "juan.perez" );
    user.setEmail( "juan.perez@axity.com" );
    user.setUserCreated( "system" );

    BusinessException be = assertThrows( BusinessException.class, () -> this.userService.save( user ) );
    assertNotNull(be.getError());
    assertTrue( be.getError().isBadRequest() );
    assertEquals( "101", be.getError().getCode() );
  }

}
