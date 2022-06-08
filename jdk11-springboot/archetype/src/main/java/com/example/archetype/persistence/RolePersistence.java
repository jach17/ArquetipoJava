// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.archetype.model.RoleDO;

/**
 * @author guillermo.segura@axity.com
 *
 */
public interface RolePersistence extends JpaRepository<RoleDO, Long>
{

}
