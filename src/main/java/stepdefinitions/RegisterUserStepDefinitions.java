package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.RegisterUserInfo;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCodeQuestion;
import tasks.RegisterUserTask;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterUserStepDefinitions {

    private static final String restURL = "http://localhost:5000/api";
    Actor bryan;

    @Given("Bryan es un cliente que quiere administrar sus productos")
    public void bryan_es_un_cliente_que_quiere_administrar_sus_productos() {
        bryan = Actor.named("Bryan").whoCan(CallAnApi.at(restURL));
    }

    @When("Envia la informacion requerida para el registro")
    public void envia_la_informacion_requerida_para_el_registro() {

        RegisterUserInfo registerUserInfo = new RegisterUserInfo();

        registerUserInfo.setName("morpheus");
        registerUserInfo.setJob("leader");
        registerUserInfo.setEmail("eve.holt@reqres.in");
        registerUserInfo.setPassword("password123");

        bryan.attemptsTo(
                RegisterUserTask.withInfo(registerUserInfo)
        );


    }

    @Then("El debe obtener una cuenta virtual para poder ingresar cuando lo requiera")
    public void el_debe_obtener_una_cuenta_virtual_para_poder_ingresar_cuando_lo_requiera() {

        bryan.should(
                seeThat("Registro correcto", ResponseCodeQuestion.was(), equalTo(200))
        );

    }


}
