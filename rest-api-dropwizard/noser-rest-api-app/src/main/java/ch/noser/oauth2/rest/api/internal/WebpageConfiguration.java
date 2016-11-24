package ch.noser.oauth2.rest.api.internal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class WebpageConfiguration {

   @Valid
   @NotNull
   private String wwwroot;
   
   @Valid
   @NotNull
   private String originalWwwroot;

   public String getWwwroot() {
      return wwwroot;
   }

   public String getOriginalWwwroot() {
      return originalWwwroot;
   }

}
