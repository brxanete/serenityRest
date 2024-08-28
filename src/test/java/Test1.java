import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SerenityRunner.class)
public class Test1 {

    private static final String restURL = "http://localhost:5000/api";

    @Test
    public void getUsers() {

        Actor bryan = Actor.named("Bryan").whoCan(CallAnApi.at(restURL));

        bryan.attemptsTo(
                Get.resource("/users?page=2")

        );

        assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
    }




}
