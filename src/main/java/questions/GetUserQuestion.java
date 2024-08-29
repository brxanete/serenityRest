package questions;

import models.UserModel;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetUserQuestion implements Question<UserModel> {
    @Override
    public UserModel answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(UserModel.class);
    }
}
