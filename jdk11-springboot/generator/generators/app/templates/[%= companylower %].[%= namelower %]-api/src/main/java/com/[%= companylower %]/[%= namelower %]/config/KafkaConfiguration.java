package com.[%= companylower %].[%= namelower %].config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.[%= companylower %].[%= namelower %].facade.OfficeFacade;

import lombok.extern.slf4j.Slf4j;

/**
 * Kafka configuration class
 * 
 * @author [%= username %]
 */
@Configuration
@Slf4j
public class KafkaConfiguration
{
  @Autowired
  private [%= namecamel %]Facade [%= namelower %]Facade;

  @KafkaListener(id = "listener", topics = "test")
  public void listen( String message )
  {
    try
    {
      this.[%= namelower %]Facade.processMessage( message );
    }
    catch( Exception e )
    {
      log.error( "El mensaje no pudo ser procesado: {}", message );
      log.error( e.getMessage(), e );
    }
  }
}
