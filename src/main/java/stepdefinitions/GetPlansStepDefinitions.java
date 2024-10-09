package stepdefinitions;

import facts.NetflixPlans;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCodeQuestion;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetPlansStepDefinitions {

    Actor bryan;
    private static final String restAPI = "http://localhost:5000/api";


    @Given("Bryan es un cliente que quiere validar su plan")
    public void bryan_es_un_cliente_que_quiere_validar_su_plan() {
        bryan = Actor.named("Bryan").whoCan(CallAnApi.at(restAPI));

    }

    @When("Envia la informacion requerida para la consulta de planes")
    public void envia_la_informacion_requerida_para_la_consulta_de_planes() {
        bryan.has(NetflixPlans.toViewSeries()
        );

    }

    @Then("El debe validar la obtencion correcta de la informacion")
    public void el_debe_validar_la_obtencion_correcta_de_la_informacion() {
        bryan.should(
                seeThat("Obtencion de planes", ResponseCodeQuestion.was(), equalTo(200))
        );
    }
}
