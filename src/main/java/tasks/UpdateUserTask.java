package tasks;


import interactions.Patch;
import interactions.Put;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;


import static net.serenitybdd.screenplay.Tasks.instrumented;


public class UpdateUserTask implements Task {

    //Object model
    private final Object userInfo;

    public UpdateUserTask(Object userInfo) {
        this.userInfo = userInfo;
    }

    public static Performable withInfo(Object userInfo) {
        return instrumented(UpdateUserTask.class, userInfo);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to("/users/2") //or patch
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(userInfo)
                        ));


    }

}

