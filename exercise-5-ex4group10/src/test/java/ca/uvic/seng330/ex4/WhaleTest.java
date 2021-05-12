package ca.uvic.seng330.ex4;

import ca.uvic.seng330.ex4.Whale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl. NoPublicFieldsRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;


public class WhaleTest {

  Whale w;

  @BeforeEach
  void init(){
    w = new Whale();
  }

  @Test
  void TestEquality(){
    Whale copy = new Whale(w);
    Assertions.assertEquals(w, copy);
    Assertions.assertNotSame(copy, w);
  }

  @Test
  void TestGetterSetter() {
    PojoClass pojoclass = PojoClassFactory.getPojoClass(Whale.class);
    Validator validator = ValidatorBuilder
            .create()
            .with(new GetterTester())
            .build();
    validator.validate(pojoclass);
  }
  @Test
  void TestNullParameter(){
    Assertions.assertThrows(AssertionError.class, () -> {new Whale(null);});
  }

  @Test
  void TestAgeCheck(){
    Assertions.assertThrows(AssertionError.class, () -> w.setAge(-10));
  }

  @Test
  void TestAgeSearch(){
    List<Whale> whaleList = new ArrayList<>();
    Whale w1 = new Whale();
    Whale w2 = new Whale();
    Whale w3 = new Whale();
    Whale w4 = new Whale();
    w1.setAge(5);
    w2.setAge(65);
    w3.setAge(27);
    w4.setAge(11);
    whaleList.add(w1);
    whaleList.add(w2);
    whaleList.add(w3);
    whaleList.add(w4);
    Collections.sort(whaleList, new Whale.AgeComparator()); //need to sort the list before doing binary search
    //Now try to do a Binary Search for a specific whale
    int targetAge = 27;
    Whale searchWhale = new Whale();
    searchWhale.setAge(targetAge);
    int index = Collections.binarySearch(whaleList, searchWhale, new Whale.AgeComparator());
    Assertions.assertEquals(targetAge, whaleList.get(index).getAge());
  }

  @Test
  void TestNameSearch(){
    List<Whale> whaleList = new ArrayList<>();
    Whale w1 = new Whale();
    Whale w2 = new Whale();
    Whale w3 = new Whale();
    Whale w4 = new Whale();
    w1.setName("Bob");
    w2.setName("Bill");
    w3.setName("Belinda");
    w4.setName("Mike");
    whaleList.add(w1);
    whaleList.add(w2);
    whaleList.add(w3);
    whaleList.add(w4);
    Collections.sort(whaleList, new Whale.NameComparator()); //need to sort the list before doing binary search
    //Now try to do a Binary Search for a specific whale
    String targetName = "Mike";
    Whale searchWhale = new Whale();
    searchWhale.setName(targetName);
    int index = Collections.binarySearch(whaleList, searchWhale, new Whale.NameComparator());
    Assertions.assertEquals(targetName, whaleList.get(index).getName());
  }

  @Test
  void TestAgeSort(){
    List<Whale> whaleList = new ArrayList<>();
    Whale w1 = new Whale();
    Whale w2 = new Whale();
    Whale w3 = new Whale();
    Whale w4 = new Whale();
    w1.setAge(5);
    w2.setAge(65);
    w3.setAge(27);
    w4.setAge(11);
    whaleList.add(w1);
    whaleList.add(w2);
    whaleList.add(w3);
    whaleList.add(w4);
    Collections.sort(whaleList, new Whale.AgeComparator());
    Assertions.assertEquals(5, whaleList.get(0).getAge());
  }

  @Test
  void TestSpeciesSort(){
    List<Whale> whaleList = new ArrayList<>();
    Whale w1 = new Whale();
    Whale w2 = new Whale();
    Whale w3 = new Whale();
    Whale w4 = new Whale();
    w1.setSpecies(Whale.Species.BELUGA);
    w2.setSpecies(Whale.Species.FIN);
    w3.setSpecies(Whale.Species.HUMPBACK);
    w4.setSpecies(Whale.Species.HUMPBACK);
    whaleList.add(w1);
    whaleList.add(w2);
    whaleList.add(w3);
    whaleList.add(w4);
    Collections.sort(whaleList, new Whale.SpeciesComparator());
    Assertions.assertEquals(Whale.Species.HUMPBACK, whaleList.get(0).getSpecies());
  }
  @Test
  void TestFilterByAge(){
    List<Whale> whaleList = new ArrayList<>();
    Whale w1 = new Whale();
    Whale w2 = new Whale();
    Whale w3 = new Whale();
    Whale w4 = new Whale();
    w1.setAge(5);
    w2.setAge(65);
    w3.setAge(27);
    w4.setAge(11);
    whaleList.add(w1);
    whaleList.add(w2);
    whaleList.add(w3);
    whaleList.add(w4);
    List<Whale> closeToTarget = whaleList.stream().filter(whale1 -> (whale1.getAge() < 25)).collect(Collectors.toList());
    Assertions.assertEquals(2, closeToTarget.size());
  }

}
