package ch.noser.oauth2.rest.api.boundary;

import com.google.common.collect.Lists;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Noser Rest API - KPZ Resource
 * <p>
 * This resource provides information for the KPZ-JAVA.
 *
 * @resourceDescription KPZ Java Resource
 */
@Path("/java")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("noser-kpz-java")
public class KPZJavaResource {

    private String text = "Hallo KPZ Java";

    /**
     * Get secret text of kpz.
     *
     * @return Secret text.
     * @scope noser-user
     */
    @GET
    public List<String> get() {
        return Lists.newArrayList(text);
    }

    /**
     * Post new secret text.
     *
     * @scope noser-user
     */
    @POST
    public void getById(@QueryParam("text") String text) {
        this.text = text;
    }

}
