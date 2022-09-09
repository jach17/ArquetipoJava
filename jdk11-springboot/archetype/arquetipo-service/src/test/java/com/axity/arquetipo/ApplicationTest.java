package com.axity.arquetipo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guillermo.segura@axity.com
 */
@SpringBootApplication
class ApplicationTest
{

  public static void main( String[] args )
  {
    SpringApplication.run( ApplicationTest.class, args );
  }

  @Test
  void test()
  {
    assertTrue( true );
  }
}
