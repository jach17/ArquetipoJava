// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.archetype.common.dto.UserDTO;
import com.example.archetype.model.UserDO;

/**
 * @author guillermo.segura@axity.com
 *
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper <UserDTO, UserDO>
{  
  @Mapping(source = "created", target = "created", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS")
  @Mapping(source = "modified", target = "modified", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS")
  UserDTO toDto(UserDO user);

  @Mapping(source = "created", target = "created", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS")
  @Mapping(source = "modified", target = "modified", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS")
  UserDO toEntity(UserDTO user);
}
