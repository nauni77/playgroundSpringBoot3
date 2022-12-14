package de.bls19.examples.springboot3check.dynamicproxybean;

import de.bls19.examples.springboot3check.dynamicproxybean.myservice.MyServiceIF;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Proxy;
import java.util.UUID;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@Slf4j
public class TestProxyNoBean {

  @Autowired
  ApplicationContext applicationContext;


  @Test
  @Order(10)
  public void testSpecializedProxy() {

    UUID uuid = UUID.randomUUID();

    MyServiceIF myService = (MyServiceIF) Proxy.newProxyInstance(
        GlobalProxyServiceBeanSpezialized.class.getClassLoader(),
        new Class[] { MyServiceIF.class },
        new GlobalProxyServiceBeanSpezialized());

    // even if this method is implemented at the dynamic proxy class - INVOKE is called!!!!
    Assertions.assertEquals("INVOKE WAS CALLED", myService.repeat(uuid.toString()));

  }

  @Test
  @Order(20)
  public void testUnspecializedProxy() {

    UUID uuid = UUID.randomUUID();

    MyServiceIF myService = (MyServiceIF) Proxy.newProxyInstance(
        GlobalProxyServiceBeanUnspezialized.class.getClassLoader(),
        new Class[] { MyServiceIF.class },
        new GlobalProxyServiceBeanUnspezialized(MyServiceIF.class));

    Assertions.assertEquals("INVOKE WAS CALLED", myService.repeat(uuid.toString()));

  }

}
