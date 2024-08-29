import models.Datum;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import questions.GetUserQuestion;
import questions.ResponseCodeQuestion;
import tasks.GetUserTask;


import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SerenityRunner.class)
public class UserTest {


    //Establecemos la URL
    private static final String restURL = "http://localhost:5000/api";

    @Test
    public void getUsers() {
        //Llamamos al actor y le damos la habilidad de llamar un API con las habilidades REST
        Actor bryan = Actor.named("Bryan").whoCan(CallAnApi.at(restURL));

        // Iniciamos acciones usando las tareas ya creadas.
        bryan.attemptsTo(
                GetUserTask.fromPage(3)

        );

        // Realizamos aserciones usando las questions ya creadas.
        bryan.should(
                seeThat("El codigo de la respuesta", ResponseCodeQuestion.was(), equalTo(200))
        );

        // Realizamos validaciones puntuales en la data, que el usuario no sea nulo y que el correo coincida con el indicado.

        Datum user = new GetUserQuestion()
                .answeredBy(bryan) // invoca la implementacion de la pregunta permitiendo al actor acceder a la ultima respuesta HTTP
                .getData() //
                .stream()
                .filter(x -> x.getId() == 1)
                .findFirst()
                .orElse(null);

        bryan.should(
                seeThat("Usuario no es nulo", act -> user, notNullValue())
        );

        bryan.should(
                seeThat("Email de usuario", act -> user.getEmail(), equalTo("george.bluth@reqres.in"))
        );

    }


}