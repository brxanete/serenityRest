package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import questions.ResponseCodeQuestion;
import tasks.GetUserTask;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetUserStepDefinitions {

    private static final String restURL = "http://localhost:5000/api";
    Actor bryan;

    @Given("Bryan es un cliente que consulta usuarios")
    public void bryan_es_un_cliente_que_consulta_usuarios() {
        bryan = Actor.named("Bryan").whoCan(CallAnApi.at(restURL));

    }

    @When("Envia la informacion requerida para la consulta")
    public void envia_la_informacion_requerida_para_la_consulta() {
        bryan.attemptsTo(
                GetUserTask.fromPage(1)
        );

    }

    @Then("El debe obtener la informacion de la pagina consultada")
    public void el_debe_obtener_la_informacion_de_la_pagina_consultada() {
        bryan.should(
                seeThat("El codigo de respuesta", ResponseCodeQuestion.was(), equalTo(200))
        );

    }

}
