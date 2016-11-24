package ch.noser.oauth2.rest.api.boundary;

import ch.noser.oauth2.rest.api.domain.Person;
import com.google.common.collect.Lists;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Noser Rest API - Mitarbeiter Resource.
 * <p>
 * This resource provides Mitarbeiter which are known by Noser.
 *
 * @resourceDescription Mitarbeiter Resource
 */
@Path("/mitarbeiter")
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("noser-ma")
public class MitarbeiterResource {


    /**
     * Get Mitarbeiter
     * <p>
     * @return List of found Mitarbeiter.
     * @scope noser-user
     */
    @GET
    public List<Person> get() {
        return Lists.newArrayList(new Person(1, "Geri Moll"), new Person(2, "Lehrling"));
    }

    /**
     * Get Mitarbeiter by id
     *
     * @param id Id of a Mitarbeiter.
     * @return Mitarbeiter
     * @scope noser-user
     */
    @GET
    @Path("/{id}")
    public Person getById(@PathParam("id") Integer id) {
        if (id == 1) {
            return new Person(1, "Geri Moll");
        } else if (id == 2) {
            new Person(2, "Lehrling");
        }

        return null;
    }

}
