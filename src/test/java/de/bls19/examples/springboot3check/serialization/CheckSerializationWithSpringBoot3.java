package de.bls19.examples.springboot3check.serialization;

import de.bls19.examples.springboot3check.mockito.TestConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.SerializationUtils;

import java.util.Date;

/**
 * Run the tests without Tomcat-Service
 */
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackageClasses = TestConfiguration.class)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class CheckSerializationWithSpringBoot3 {

  /**
   * Check if serialization with SpringBoot-Tools are still working.
   */
  @Test
  public void testSerialization() {
    Date originalDate = new Date();
    byte[] mySerializedObj = SerializationUtils.serialize(originalDate);
    Date deserializeDate = (Date) SerializationUtils.deserialize(mySerializedObj);
    Assertions.assertEquals(originalDate, deserializeDate);
  }

}
