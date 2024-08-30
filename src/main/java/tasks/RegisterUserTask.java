package tasks;


import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;


public class RegisterUserTask implements Task {


    //Realizamos la creacion de otro modelo para de esta manera pasar la informacion del body al metodo.
    private final String userInfo;

    public RegisterUserTask(String userInfo) {
        this.userInfo = userInfo;
    }

    public static Performable withInfo(String userInfo) {
        return instrumented(RegisterUserTask.class, userInfo);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Post.to("/register")
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(userInfo)
                        ));


    }

}
