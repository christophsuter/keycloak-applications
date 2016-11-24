package ch.noser.oauth2.rest.api.internal;

import de.ahus1.keycloak.dropwizard.KeycloakConfiguration;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NoserRestAPIConfiguration extends Configuration {

   @Valid
   @NotNull
   private KeycloakConfiguration keycloakConfiguration;

   @Valid
   @NotNull
   private WebpageConfiguration webpageConfiguration;

   @Valid
   @NotNull
   private SwaggerDocumentationConfiguration swaggerDocumentation;

   public KeycloakConfiguration getKeycloakConfiguration() {
      return keycloakConfiguration;
   }

   public WebpageConfiguration getWebpageConfiguration() {
      return webpageConfiguration;
   }

   public SwaggerDocumentationConfiguration getSwaggerDocumentation() {
      return swaggerDocumentation;
   }

}
