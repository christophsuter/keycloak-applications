package ch.noser.spring.keycloak;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "greeter")
public class GreetingController {

    private AccessToken accessToken;

    @Autowired
    public GreetingController(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    @RequestMapping("greet")
    public List<String> getGreeting() {
        List<String> strings = new ArrayList<>();
        strings.add("Hello " + accessToken.getPreferredUsername());
        strings.add("Given Name: " + accessToken.getGivenName());
        Instant fromUnixTimestamp = Instant.ofEpochSecond(accessToken.getExpiration());
        strings.add("Expiration LocalDateTime: " + fromUnixTimestamp.atZone(ZoneId.of("CET")));

        return strings;
    }
}