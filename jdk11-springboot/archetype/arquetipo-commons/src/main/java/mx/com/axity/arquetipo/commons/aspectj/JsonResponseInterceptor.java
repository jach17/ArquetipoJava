package mx.com.axity.arquetipo.commons.aspectj;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Anotación para interceptar métodos de controladores que regresan un objeto JSON
 * 
 * @author guillermo.segura@axity.com
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface JsonResponseInterceptor
{
}
