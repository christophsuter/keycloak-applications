package ch.noser.oauth2.rest.api.internal;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import io.dropwizard.setup.Environment;

public class NoserRestAPIModule extends AbstractModule {

   private final NoserRestAPIConfiguration configuration;
   private final Environment environment;

   public NoserRestAPIModule(NoserRestAPIConfiguration configuration, Environment environment) {
      this.configuration = configuration;
      this.environment = environment;
   }

   @Override
   protected void configure() {
      bind(NoserRestAPIConfiguration.class).toInstance(configuration);
      bind(ObjectMapper.class).toInstance(environment.getObjectMapper());
      bind(MetricRegistry.class).toInstance(environment.metrics());
   }

}
