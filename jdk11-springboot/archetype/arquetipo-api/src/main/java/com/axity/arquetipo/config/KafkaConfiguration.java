package com.axity.arquetipo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.axity.arquetipo.facade.OfficeFacade;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class KafkaConfiguration
{
  @Autowired
  private OfficeFacade officeFacade;

  @KafkaListener(id = "listener", topics = "test")
  public void listen( String message )
  {
    try
    {
      this.officeFacade.processMessage( message );
    }
    catch( Exception e )
    {
      log.error( "El mensaje no pudo ser procesado: {}", message );
      log.error( e.getMessage(), e );
    }
  }
}
