package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.Datum;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.GetUserQuestion;
import tasks.GetUserTask;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ValidateMailStepDefinitions {

    Actor bryan;
    private static final String restURL = "http://localhost:5000/api";

    @Given("Bryan es un cliente que quiere validar su correo")
    public void bryan_es_un_cliente_que_quiere_validar_su_correo() {
        bryan = Actor.named("Bryan").whoCan(CallAnApi.at(restURL));

    }

    @When("Obtiene la informacion del usuario")
    public void obtiene_la_informacion_del_usuario() {
        bryan.attemptsTo(
                GetUserTask.fromPage(1)
        );


    }

    @Then("El debe validar que el correo sea el correcto")
    public void el_debe_validar_que_el_correo_sea_el_correcto() {

        Datum user = new GetUserQuestion().answeredBy(bryan).getData().stream()
                .filter(x -> x.getId() == 1)
                .findFirst()
                .orElse(null);

        if (user != null) {
            System.out.println("Datos del usuario: " + user.toString());

            bryan.should(
                    seeThat("Email de usuario", act -> user.getEmail(), equalTo("george.bluth@reqres.in"))
            );
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}
