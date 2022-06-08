// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.mapper;

/**
 * Contract for a generic dto to entity mapper.
 * 
 * @author guillermo.segura@axity.com
 */
public interface EntityMapper<D, E>
{
  /**
   * Converts a DTO to an entity
   * 
   * @param dto
   * @return
   */
  E toEntity( D dto );

  /**
   * Converts an entity to a DTO
   * 
   * @param entity
   * @return
   */
  D toDto( E entity );
}
