package com.axity.arquetipo.commons.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Objeto de transferencia de Oficina
 * 
 * @author guillermo.segura@axity.com
 */
@Getter
@Setter
public class OfficeDto implements Serializable
{

  private static final long serialVersionUID = -1468484645398717478L;

  @Schema(required = true, description = "Código de la oficina", maxLength = 10)
  private Integer id;

  @Schema(required = true, description = "City", maxLength = 50)
  private String city;

  @Schema(required = true, description = "Phone", maxLength = 50)
  private String phone;

  @Schema(required = true, description = "Address line 1", maxLength = 50)
  private String addressLine1;

  @Schema(required = true, description = "Address line 2", maxLength = 50)
  private String addressLine2;

  @Schema(required = true, description = "State", maxLength = 50)
  private String state;

  @Schema(required = true, description = "Country", maxLength = 50)
  private String country;

  @JsonProperty("zip")
  @JsonAlias("postalCode")
  @Schema(required = true, description = "Zip", maxLength = 15)
  private String postalCode;

  @Schema(required = true, description = "Territory", maxLength = 50)
  private String territory;

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString()
  {
    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    return gson.toJson( this );
  }
}
