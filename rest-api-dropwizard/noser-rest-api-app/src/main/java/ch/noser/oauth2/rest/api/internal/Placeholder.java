package ch.noser.oauth2.rest.api.internal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Placeholder {

   @Valid
   @NotNull
   private String key;
   
   @Valid
   @NotNull
   private String value;

   public String getKey() {
      return key;
   }

   public String getValue() {
      return value;
   }

}
