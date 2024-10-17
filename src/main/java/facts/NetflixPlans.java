package facts;

import interactions.Get;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.facts.Fact;

import java.util.HashMap;
import java.util.List;

public class NetflixPlans implements Fact {

    //Hechos acerca de un actor, caracteristicas o informacion relevante

    private String plansInfo;

    public static NetflixPlans toViewSeries() {
        return new NetflixPlans();
    }

    @Override
    public void setup(Actor actor) {
        actor.attemptsTo(
                Get.resource("/plans")
        );

        List<HashMap<String, String>> plans = SerenityRest.lastResponse().path("data");

        actor.remember("plans", plans);

        plansInfo = plans.toString();
    }
    public String toString(){
            return "Los planes son " + plansInfo;
    }
}
