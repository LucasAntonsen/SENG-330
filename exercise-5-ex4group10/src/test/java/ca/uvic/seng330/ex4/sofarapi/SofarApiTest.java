package ca.uvic.seng330.ex4.sofarapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

public abstract class SofarApiTest {

  SofarApi s;

  @BeforeEach
  public void init(){
    s = getApi();
  }

  protected abstract SofarApi getApi();

  @Test
  public void testBuildUri() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method buildUrl = SofarApi.class.getDeclaredMethod("buildUrl", String.class, Map.class);
    buildUrl.setAccessible(true);

    String root = "https://fun.site";

    Map<String, String > GETParams = Map.of("age", "12", "weight", "10lb");
    assertThat(buildUrl.invoke(s, root, GETParams).toString()).contains(root+"?").contains("age=12").contains("weight=10lb");

  }

  @Test
  public void testCollectResponseFromStream() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method collectResponseFromStream = SofarApi.class.getDeclaredMethod("collectResponseFromStream", InputStream.class);
    collectResponseFromStream.setAccessible(true);

    String test = "This will be made into a stream";
    assertThat(test).isEqualTo(collectResponseFromStream.invoke(s, new ByteArrayInputStream(test.getBytes(StandardCharsets.UTF_8))));

  }

  @Test
  public void testGenerateSofarError(){
    String response = "{\"message\":\"Invalid data\"}";
    s.onError(response, 404);
    assertThat("Invalid data").isEqualTo(s.getError());
  }

  @Test
  void testNoResponse(){
    String response = "";
    s.onError(response, 404);
    assertThat("Server responded with error code 404").isEqualTo(s.getError());
  }


}
