package ch.noser.oauth2.rest.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

/**
 * Represantation of an Mitarbeiter.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    /**
     * Current name of the Mitarbeiter.
     */
    private final String currentName;

    private final Integer id;

    Person() {
        //serialisation
        this(null, null);
    }

    public Person(Integer id, String currentName) {
        this.currentName = currentName;
        this.id = id;
    }

    public String getCurrentName() {
        return currentName;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Person) {
            Person other = (Person) obj;
            return Objects.equals(this.id, other.id);
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s[#%s %s]",
                getClass().getSimpleName(),
                id, currentName);
    }

}
