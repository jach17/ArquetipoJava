// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.archetype.api.aop.Intercept;
import com.example.archetype.common.dto.UserDTO;
import com.example.archetype.facade.UserFacade;

/**
 * @author guillermo.segura@axity.com
 */
@RestController
@RequestMapping("/users")
@Intercept
public class UserController
{

  @Autowired
  private UserFacade userFacade;

  /**
   * Method to get all users {@inheritDoc}
   */
  @GetMapping("")
  public ResponseEntity<List<UserDTO>> getAll()
  {

    List<UserDTO> users = this.userFacade.getAll();
    return new ResponseEntity<>( users, HttpStatus.OK );
  }

  /**
   * Method get user by id
   * 
   * @param id The id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getById( @PathVariable(value = "id")
  Long id )
  {
    UserDTO user = this.userFacade.getById( id );
    return new ResponseEntity<>( user, HttpStatus.OK );
  }

  @PostMapping("")
  public ResponseEntity<UserDTO> save( @RequestBody
  UserDTO user )
  {
    user.setUserCreated( "system" );

    var result = this.userFacade.save( user );

    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to edit a user
   * 
   * @param id
   * @param user
   * @return
   */
  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> update( @PathVariable(value = "id")
  Long id, @RequestBody
  UserDTO user )
  {

    user.setId( id );
    user.setUserModified( "system" );
    var result = this.userFacade.update( user );

    return new ResponseEntity<>( result, HttpStatus.CREATED );
  }

  /**
   * Method to delete users by id
   * 
   * @param id The user id.
   * @return
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<UserDTO> delete( @PathVariable(value = "id")
  Long id )
  {
    var user = new UserDTO ();
    user.setId( id );
    user.setUserModified( "system" );
    this.userFacade.delete( user );
    
    return new ResponseEntity<>( HttpStatus.OK );
  }
}
