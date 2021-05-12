package ca.uvic.seng330.ex4;

import com.github.javafaker.Faker;
import static net.andreinc.mockneat.unit.user.Emails.emails;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.NoPublicFieldsRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import java.util.ArrayList;
import java.util.Collections;


public class ReporterTest {

    Faker faker = new Faker();

	@Test
	void TestGetterSetter() {
		PojoClass pojoclass = PojoClassFactory.getPojoClass(Reporter.class);
		Validator validator = ValidatorBuilder
				.create()
				.with(new SetterMustExistRule())
				.with(new GetterMustExistRule())
				.with(new SetterTester())
				.with(new GetterTester())
				.build();
		validator.validate(pojoclass);
	}

	@Test
	void TestCompareTo(){

		Reporter r1 = new Reporter(faker.idNumber().hashCode(), "a", faker.phoneNumber().cellPhone(), emails().domain("uvic.ca").get(), faker.address().fullAddress());
		Reporter r2 = new Reporter(faker.idNumber().hashCode(), "b", faker.phoneNumber().cellPhone(), emails().domain("uvic.ca").get(), faker.address().fullAddress());
		Reporter r3 = new Reporter(faker.idNumber().hashCode(), "c", faker.phoneNumber().cellPhone(), emails().domain("uvic.ca").get(), faker.address().fullAddress());

		ArrayList<Reporter> reporters = new ArrayList<Reporter>();

		reporters.add(r1);
		reporters.add(r2);
		reporters.add(r3);

		Collections.sort(reporters);

		Assertions.assertEquals(r1,reporters.get(0));
		Assertions.assertEquals(r2,reporters.get(1));
		Assertions.assertEquals(r3,reporters.get(2));

	}


}
