package ch.noser.oauth2.rest.api.internal;

import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;

/**
 * A bundle for serving static asset files from the fileSystem.
 */
public class FileBundle implements ConfiguredBundle<NoserRestAPIConfiguration> {

   private static final Logger LOGGER = LoggerFactory.getLogger(FileBundle.class);

   private static final String DEFAULT_INDEX_FILE = "index.html";
   private static final String DEFAULT_FILE_ASSETS_NAME = "FileAssest";

   private final String uriPath;

   /**
    * Creates a new FileBundle which will configure the application to serve the static files
    * located in {@code ${path}}. The URL will be taken when the Bundle ist started.
    *
    * @param uriPath the path to the files in the FS.
    */
   public FileBundle(String uriPath) {
      this.uriPath = uriPath.endsWith("/") ? uriPath : (uriPath + '/');
   }
   
   
   @Override
   public void initialize(Bootstrap<?> bootstrap) {
      //Nothing to do
   }

   @Override
   public void run(NoserRestAPIConfiguration configuration, Environment environment) throws Exception {
      LOGGER.info("Registering FileBundle with name: {} for path {}", DEFAULT_FILE_ASSETS_NAME, uriPath + '*');
      FileSystemServlet servlet = createServlet(configuration.getWebpageConfiguration().getWwwroot());

      environment.servlets().addServlet(DEFAULT_FILE_ASSETS_NAME, servlet).addMapping(uriPath + '*');

       final FilterRegistration.Dynamic cors =
               environment.servlets().addFilter("CORS", CrossOriginFilter.class);

       // Configure CORS parameters
       cors.setInitParameter("allowedOrigins", "*");
       cors.setInitParameter("allowedHeaders", "*"); //X-Requested-With,Content-Type,Accept,Origin
       cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

       // Add URL mapping
       cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "*");
       cors.addMappingForServletNames(EnumSet.allOf(DispatcherType.class), true, "*");
       cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
       cors.addMappingForServletNames(EnumSet.allOf(DispatcherType.class), true, "/*");
   }

   protected FileSystemServlet createServlet(String wwwRootPath) {
      return new FileSystemServlet(wwwRootPath, uriPath, DEFAULT_INDEX_FILE, StandardCharsets.UTF_8);
   }

}
