package com.axity.arquetipo.persistence.redis;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.util.ReflectionTestUtils;

class StringRedisRepositoryTest
{
  private static final String KEY = "key";

  private StringRedisRepository stringRedisRepository;

  private RedisTemplate<String, String> redisTemplate;
  private ValueOperations<String, String> valueOperations;
  private RedisOperations<String, String> redisOperations;

  @BeforeEach
  void init()
  {
    this.redisTemplate = mockRedisTemplate();
    this.valueOperations = mockValueOperations();
    this.redisOperations = mockRedisOperations();
    when( this.redisTemplate.opsForValue() ).thenReturn( this.valueOperations );
    when( this.redisTemplate.expire( anyString(), any( Duration.class ) ) ).thenReturn( Boolean.TRUE );
    when( this.redisTemplate.hasKey( anyString() ) ).thenReturn( Boolean.TRUE );
    when( this.redisTemplate.keys( anyString() ) ).thenReturn( createKeys() );
    when( this.valueOperations.get( anyString() ) ).thenReturn( "value" );
    when( this.valueOperations.getOperations() ).thenReturn( this.redisOperations );
    when( this.redisOperations.delete( anyString() ) ).thenReturn( Boolean.TRUE );

    this.stringRedisRepository = new StringRedisRepository();
    ReflectionTestUtils.setField( stringRedisRepository, "redisTemplate", this.redisTemplate );
    ReflectionTestUtils.setField( stringRedisRepository, "redisData", this.valueOperations );
    ReflectionTestUtils.setField( stringRedisRepository, "defaultTtl", 1000 );
  }

  @SuppressWarnings("unchecked")
  private RedisTemplate<String, String> mockRedisTemplate()
  {
    return mock( RedisTemplate.class );
  }

  @SuppressWarnings("unchecked")
  private ValueOperations<String, String> mockValueOperations()
  {
    return mock( ValueOperations.class );
  }

  @SuppressWarnings("unchecked")
  private RedisOperations<String, String> mockRedisOperations()
  {
    return mock( RedisOperations.class );
  }

  private Set<String> createKeys()
  {
    var keys = new HashSet<String>();
    for( int i = 0; i < 5; i++ )
    {
      keys.add( UUID.randomUUID().toString() );
    }
    return keys;
  }

  @Test
  void testAddStringString()
  {
    this.stringRedisRepository.add( KEY, UUID.randomUUID().toString() );
    assertNotNull( this.stringRedisRepository );
  }

  @Test
  void testAddStringStringLong()
  {
    this.stringRedisRepository.add( KEY, UUID.randomUUID().toString(), 1500L );
    assertNotNull( this.stringRedisRepository );
  }

  @Test
  void testGet()
  {
    var value = this.stringRedisRepository.get( KEY );
    assertNotNull( value );
  }

  @Test
  void testHasKey()
  {
    assertTrue( this.stringRedisRepository.hasKey( KEY ) );
  }

  @Test
  void testGetKeys()
  {
    assertNotNull( this.stringRedisRepository.getKeys( "keys" ) );
  }

  @Test
  void testDelete()
  {
    this.stringRedisRepository.delete( KEY );
    assertNotNull( this.stringRedisRepository );
  }

}
