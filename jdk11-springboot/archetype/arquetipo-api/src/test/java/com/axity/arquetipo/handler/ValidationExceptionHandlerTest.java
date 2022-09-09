package com.axity.arquetipo.handler;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.core.MethodParameter;

import com.axity.arquetipo.commons.response.HeaderDto;



class ValidationExceptionHandlerTest
{
  private ValidationExceptionHandler handler;

  @BeforeEach
  void init()
  {
    handler = new ValidationExceptionHandler();
    ReflectionTestUtils.setField( handler, "message", "Required field" );
  }

  @SuppressWarnings("rawtypes")
  @Test
  void testHandleMethodArgumentNotValidMethodArgumentNotValidExceptionHttpHeadersHttpStatusWebRequest()
  {
    var bindingResult =  mock(BindingResult.class);
    var ex = new MethodArgumentNotValidException(mock(MethodParameter.class), bindingResult);
    
    when( bindingResult.getFieldErrors() ).thenReturn( createDummyList() );

    var headers = new HttpHeaders();
    headers.add( HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE );

    var status = HttpStatus.BAD_REQUEST;
    var response = this.handler.handleMethodArgumentNotValid( ex, headers, status, null );
    assertNotNull( response );
    assertInstanceOf( ResponseEntity.class, response );
    var responseEntity = (ResponseEntity)response;
    assertNotNull( responseEntity );
    assertInstanceOf( HeaderDto.class, responseEntity.getBody() );
  }

  private List<FieldError> createDummyList()
  {
    var list = new ArrayList<FieldError>();
    list.add( new FieldError( "object", "data", "message" ) );
    return list;
  }
}
