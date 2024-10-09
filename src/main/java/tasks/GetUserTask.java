package tasks;

import interactions.Get;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;


import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUserTask implements Task {

    private final int page;

    public GetUserTask(int page) {
        this.page = page;
    }

    public static Performable fromPage(int page) {
        return instrumented(GetUserTask.class, page);
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/users?page=1").with(requestSpecification -> requestSpecification.contentType(ContentType.JSON).header("header1", "value1")
                        ));
    }
}
