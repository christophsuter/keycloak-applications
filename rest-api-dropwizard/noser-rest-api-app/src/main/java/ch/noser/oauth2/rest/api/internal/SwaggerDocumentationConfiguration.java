package ch.noser.oauth2.rest.api.internal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SwaggerDocumentationConfiguration {

   @Valid
   @NotNull
   private String original;

   @Valid
   @NotNull
   private String processed;

   @Valid
   @NotNull
   private List<Placeholder> placeholders;
   
   public String getOriginal() {
      return original;
   }

   public String getProcessed() {
      return processed;
   }

   public List<Placeholder> getPlaceholders() {
      return placeholders;
   }
}
