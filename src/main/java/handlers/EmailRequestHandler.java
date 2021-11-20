package handlers;

import models.UserData;
import org.apache.commons.lang3.StringUtils;

/**
 * Checks if user's email is set and confirmed
 * (this class is part of Chain of Responsibility pattern)
 */
public class EmailRequestHandler extends RequestHandler {
    @Override
    public boolean handle(UserData userData) {
        if (StringUtils.isEmpty(userData.getEmail())) {
            //trigger email setting mechanism
        } else {
            if (!userData.isEmailConfirmed()) {
                //trigger email confirmation mechanism
            }
        }

        return super.passFurther(userData);
    }
}
