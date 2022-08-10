package com.[%= companylower %].[%= namelower %].persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.[%= companylower %].[%= namelower %].model.[%= namecamel %]DO;

/**
 * Persistence interface of  de {@link com.[%= companylower %].[%= namelower %].model.[%= namecamel %]DO}
 * 
 * @author [%= username %]
 */
@Repository
public interface [%= namecamel %]Persistence extends JpaRepository<[%= namecamel %]DO, Integer>
{
  
  /**
   * Find registries by city
   * 
   * @param city
   * @return
   */
  List<[%= namecamel %]DO> findByCity( String city );

  /**
   * Find registries by territory
   * 
   * @param territory
   * @return
   */
  List<[%= namecamel %]DO> findByTerritory( String territory );

  /**
   * Find registries by country
   * 
   * @param country
   * @return
   */
  List<[%= namecamel %]DO> findByCountry( String country );
}
