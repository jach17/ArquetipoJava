package com.[%= company %].[%= name %].commons.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author guillermo.segura@axity.com
 */
@Configuration
@PropertySource("classpath:message.properties")
public class MessagePropertiesConfig
{

}
