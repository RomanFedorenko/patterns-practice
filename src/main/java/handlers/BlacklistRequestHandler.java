package handlers;

import models.UserData;

/**
 * Checks if user is not blacklisted by admins
 * (this class is part of Chain of Responsibility pattern)
 */
public class BlacklistRequestHandler extends RequestHandler{

    @Override
    public boolean handle(UserData userData) {
        if (userData.isBlacklisted()) return false;
        return super.passFurther(userData);
    }
}
