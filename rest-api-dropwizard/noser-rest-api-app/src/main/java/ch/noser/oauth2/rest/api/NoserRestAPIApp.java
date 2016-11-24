package ch.noser.oauth2.rest.api;

import ch.noser.oauth2.rest.api.boundary.KPZJavaResource;
import ch.noser.oauth2.rest.api.boundary.MitarbeiterResource;
import ch.noser.oauth2.rest.api.internal.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.ahus1.keycloak.dropwizard.KeycloakBundle;
import de.ahus1.keycloak.dropwizard.KeycloakConfiguration;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.TimeZone;

/**
 * Noser REST API as <a href=http://www.dropwizard.io/index.html>Dropwizard</a> Application.
 */
public class NoserRestAPIApp extends Application<NoserRestAPIConfiguration> {

    private static final Logger LOG = LoggerFactory.getLogger(NoserRestAPIApp.class);
    private NoserRestAPIConfiguration configuration;
    private Environment environment;
    private Injector injector;

    public static void main(String... args) throws Exception {
        new NoserRestAPIApp().run(args);
    }

    @Override
    public void initialize(final Bootstrap<NoserRestAPIConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor(false)));

        bootstrap.getObjectMapper()
                .setDateFormat(StdDateFormat.instance.withTimeZone(TimeZone.getTimeZone("UTC")))
                .configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setSerializationInclusion(JsonInclude.Include.NON_ABSENT);

        // set up folders for static content
        //bootstrap.addBundle(new AssetsBundle("/apidocs", "/doc", "index.html", "apidocs"));
        bootstrap.addBundle(new FileBundle("/"));
        bootstrap.addBundle(new KeycloakBundle<NoserRestAPIConfiguration>() {
            @Override
            protected KeycloakConfiguration getKeycloakConfiguration(NoserRestAPIConfiguration configuration) {
                return configuration.getKeycloakConfiguration();
            }
        });
    }

    @Override
    public void run(NoserRestAPIConfiguration configuration, Environment environment) {
        this.configuration = configuration;
        this.environment = environment;

        environment.servlets().addFilter("MDCInsertingServletFilter", ch.qos.logback.classic.helpers.MDCInsertingServletFilter.class);
        setupInjector();
        setupJersey();

        provideWwwFiles();
        provideSwaggerFiles();
    }

    private void setupInjector() {
        injector = Guice.createInjector(new NoserRestAPIModule(configuration, environment));
    }

    private void setupJersey() {
        environment.jersey().setUrlPattern("/api/*");

        environment.jersey().register(injector.getInstance(MitarbeiterResource.class));
        environment.jersey().register(injector.getInstance(KPZJavaResource.class));
    }

    @Override
    public String getName() {
        return "Noser REST API";
    }

    private void provideSwaggerFiles() {
        SwaggerDocumentationConfiguration config = configuration.getSwaggerDocumentation();
        FileHelper fileHelper = new FileHelper();
        try {
            fileHelper.deleteFolder(config.getProcessed());
            fileHelper.copyFiles(config.getOriginal(), config.getProcessed());
            fileHelper.replacePlaceholder(config.getProcessed(), config.getPlaceholders());
        } catch (IOException ex) {
            LOG.warn("Could not create Swagger documentation.", ex);
        }
    }

    private void provideWwwFiles() {
        WebpageConfiguration config = configuration.getWebpageConfiguration();
        FileHelper fileHelper = new FileHelper();
        try {
            fileHelper.deleteFolder(config.getWwwroot());
            fileHelper.copyFiles(config.getOriginalWwwroot(), config.getWwwroot());
        } catch (IOException ex) {
            LOG.warn("Could not provide wwwroot.", ex);
        }
    }

}
