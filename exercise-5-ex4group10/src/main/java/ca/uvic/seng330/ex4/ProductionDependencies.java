package ca.uvic.seng330.ex4;

import ca.uvic.seng330.ex4.sofarapi.WaveApi;
import ca.uvic.seng330.ex4.sofarapi.WaveData;
import ca.uvic.seng330.ex4.sofarapi.WindApi;
import ca.uvic.seng330.ex4.sofarapi.WindData;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class ProductionDependencies extends AbstractModule {
    @Override
    protected void configure() {
      bind(new TypeLiteral<WebApi<WindData>>() {}).to(WindApi.class);
      bind(new TypeLiteral<WebApi<WaveData>>() {}).to(WaveApi.class);
    }
  }


