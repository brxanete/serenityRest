import models.Datum;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import questions.GetUserQuestion;
import questions.ResponseCodeQuestion;
import tasks.GetUserTask;
import tasks.RegisterUserTask;


import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SerenityRunner.class)
public class UserTest {


    //Establecemos la URL
    private static final String restURL = "http://localhost:5000/api";

    @Test
    public void firstValidations() {
        //Llamamos al actor y le damos la habilidad de llamar un API con las habilidades REST
        Actor bryan = Actor.named("Bryan").whoCan(CallAnApi.at(restURL));

        // Iniciamos acciones usando las tareas ya creadas.
        bryan.attemptsTo(
                GetUserTask.fromPage(1)

        );


        // Realizamos aserciones usando las questions ya creadas.
        bryan.should(
                seeThat("El codigo de la respuesta", ResponseCodeQuestion.was(), equalTo(200))
        );

        // Realizamos validaciones puntuales en la data, que el usuario no sea nulo y que el correo coincida con el indicado.
        // invoca la implementacion de la pregunta permitiendo al actor acceder a la ultima respuesta HTTP
        Datum user = new GetUserQuestion().answeredBy(bryan).getData().stream().filter(x -> x.getId() == 1).findFirst().orElse(null);

        bryan.should(
                seeThat("Usuario no es nulo", act -> user, notNullValue())
        );

        bryan.should(
                seeThat("Email de usuario", act -> user.getEmail(), equalTo("george.bluth@reqres.in")),
                seeThat("Avatar de usuario", act -> user.getAvatar(), equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"))
        );

    }

    @Test
    public void registerUserTest() {
        Actor bryan = Actor.named("Bryan").whoCan(CallAnApi.at(restURL));

       // Establecemos la informacion que le vamos a pasar al modelo
        String userInfo = "{\n" +
                "      \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\",\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        bryan.attemptsTo(
                RegisterUserTask.withInfo(userInfo)
        );

        bryan.should(
                seeThat("Registro correcto", ResponseCodeQuestion.was(), equalTo(200))
        );





    }




}