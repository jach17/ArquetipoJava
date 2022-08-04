package com.axity.arquetipo.service.util;

import org.springframework.kafka.support.serializer.JsonSerializer;

import com.axity.arquetipo.commons.request.MessageDto;

public class MessageSerializer extends JsonSerializer<MessageDto>
{

}
