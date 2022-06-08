// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.archetype.model.UserDO;

/**
 * @author guillermo.segura@axity.com
 *
 */
public interface UserPersistence extends JpaRepository<UserDO, Long>
{

  /**
   * @return
   */
  @Query("SELECT o FROM UserDO o WHERE o.active = true ORDER BY o.name")
  List<UserDO> findAllActive();

  /**
   * @param username
   * @return
   */
  @Query("SELECT o FROM UserDO o WHERE UPPER(o.username) = UPPER(:username)")
  Optional<UserDO> findByUsername( @Param("username") String username );
}
