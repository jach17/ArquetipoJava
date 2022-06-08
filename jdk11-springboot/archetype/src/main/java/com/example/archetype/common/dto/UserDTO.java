// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Class User DTO
 * @author guillermo.segura@axity.com
 *
 */
@Getter
@Setter
public class UserDTO extends BaseSignedDTO
{
  /** The name of the user */
  private String name;
  
  /** The username */
  private String username;
  
  /** The email */
  private String email;
}
