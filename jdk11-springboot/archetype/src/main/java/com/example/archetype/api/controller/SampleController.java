// <summary>
// <copyright file="ArquetypeApplication.cs" company="Axity">
// This source code is Copyright Axity and MAY NOT be copied, reproduced,
// published, distributed or transmitted to or stored in any manner without prior
// written consent from Axity (www.axity.com).
// </copyright>
// </summary>
package com.example.archetype.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.archetype.common.dto.SampleDTO;

/**
 * Class Sample Controller
 * 
 * @author guillermo.segura@axity.com
 */
@RestController
@RequestMapping("/samples")
public class SampleController
{

  /**
   * Method get by id
   * 
   * @param id The id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<SampleDTO> getById( @PathVariable(value = "id")
  Long id )
  {
    SampleDTO sample = new SampleDTO();
    sample.setId( id );
    sample.setName( "Name" );
    return new ResponseEntity<>( sample, HttpStatus.OK );
  }
}
