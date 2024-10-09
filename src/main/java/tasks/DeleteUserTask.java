package tasks;

import interactions.Delete;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteUserTask implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Delete.resources("/users/2").with(requestSpecification -> requestSpecification.contentType(ContentType.JSON).header("header1", "value1"))
        );


    }
}
