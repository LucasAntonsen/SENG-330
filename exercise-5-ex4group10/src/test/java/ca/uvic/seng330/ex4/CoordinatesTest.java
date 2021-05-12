package ca.uvic.seng330.ex4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl. NoPublicFieldsRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class CoordinatesTest {
  Coordinates c;

  @BeforeEach
  void init(){
    c = new Coordinates();
  }

  @Test
  void TestGetterSetter() {
    PojoClass pojoclass = PojoClassFactory.getPojoClass(Coordinates.class);
    Validator validator = ValidatorBuilder
            .create()
            .with(new  NoPublicFieldsRule())
            .with(new SetterTester())
            .with(new GetterTester())
            .build();
    validator.validate(pojoclass);
  }

  @Test
  void TestDistance(){
    Coordinates.setOrigin(new Coordinates(22.0, 80.0));
    c.setLatitude(30);
    c.setLongitude(85);
    Assertions.assertEquals(1019, c.distanceFromOrigin(), 1);
  }
}
