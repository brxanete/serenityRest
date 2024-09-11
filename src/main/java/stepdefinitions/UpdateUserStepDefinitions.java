package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.UpdateUserInfo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCodeQuestion;
import tasks.UpdateUserTask;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class UpdateUserStepDefinitions {

    private static final String restURL = "http://localhost:5000/api";
    Actor bryan;

    @Given("Bryan es un cliente que quiere actualizar su usuario")
    public void bryan_es_un_cliente_que_quiere_actualizar_su_usuario() {
        bryan = Actor.named("Bryan").whoCan(
                CallAnApi.at(restURL)
        );


    }

    @When("Envia la informacion requerida para la actualizacion")
    public void envia_la_informacion_requerida_para_la_actualizacion() {

        UpdateUserInfo updateUserInfo = new UpdateUserInfo();

        updateUserInfo.setName("morpheusUpdated");
        updateUserInfo.setJob("leaderUpdated");
        updateUserInfo.setEmail("wrongMail@gmail.in");
        updateUserInfo.setPassword("updated123");

        bryan.attemptsTo(
                UpdateUserTask.withInfo(updateUserInfo)
        );


    }

    @Then("El debe validar la actualizacion correcta de datos")
    public void el_debe_validar_la_actualizacion_correcta_de_datos() {

        bryan.should(
                seeThat("Actualizacion de usuario correcta", ResponseCodeQuestion.was(), equalTo(200))
        );
    }


}
