package stepdefinitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCodeQuestion;
import tasks.DeleteUserTask;
import tasks.GetUserTask;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteUserStepDefinitions {

    private static final String restURL= "http://localhost:5000/api";
    Actor bryan;

    @Given("Bryan es un admin que debe realizar la eliminacion de un usuario")
    public void bryan_es_un_admin_que_debe_realizar_la_eliminacion_de_un_usuario() {
        bryan = Actor.named("Bryan").whoCan(CallAnApi.at(restURL));

    }

    @When("Envia la informacion requerida para la eliminacion")
    public void envia_la_informacion_requerida_para_la_eliminacion() {
        bryan.attemptsTo(
                new DeleteUserTask()
        );

    }

    @Then("El debe validar la eliminacion correcta de datos")
    public void el_debe_validar_la_eliminacion_correcta_de_datos() {
        bryan.should(
                seeThat("Error de eliminacion no content", ResponseCodeQuestion.was(), equalTo(204))
        );

    }
}
