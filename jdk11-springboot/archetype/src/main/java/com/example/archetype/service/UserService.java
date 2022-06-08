// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.service;

import java.util.List;

import com.example.archetype.common.dto.UserDTO;

/**
 * @author guillermo.segura@axity.com
 */
public interface UserService
{

  /**
   * Method to get the users
   * 
   * @return a {@link java.util.List<UserDTO>} of active users
   */
  List<UserDTO> getAll();

  /**
   * Method to get the user by id
   * 
   * @param id the user id.
   * @return a {@link com.example.archetype.common.dto.UserDTO}
   */
  UserDTO getById( Long id );

  /**
   * Method to create a user
   * 
   * @param user
   * @return
   */
  UserDTO save( UserDTO user );

  /**
   * Method to update a user
   * 
   * @param user
   * @return
   */
  UserDTO update( UserDTO user );

  /**
   * Method to delete a user
   * 
   * @param user
   */
  void delete( UserDTO user );
}
