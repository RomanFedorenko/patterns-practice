package handlers;

import lombok.Setter;
import models.UserData;

public abstract class RequestHandler {

    @Setter()
    private RequestHandler nextHandler = null;

    public abstract boolean handle(UserData userData);

    protected boolean passFurther(UserData userData) {
        if (nextHandler == null) {
            return true;
        }

        return nextHandler.handle(userData);
    }

}
