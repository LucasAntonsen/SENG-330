package ca.uvic.seng330.ex4;

import ca.uvic.seng330.ex4.sofarapi.*;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class MockedDependencies extends AbstractModule {
  @Override
  protected void configure() {
    bind(new TypeLiteral<WebApi<WindData>>() {}).to(StubWindAPI.class);
    bind(new TypeLiteral<WebApi<WaveData>>() {}).to(StubWaveAPI.class);
  }
}