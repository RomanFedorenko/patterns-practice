package handlers;

import mocks.DatabaseMock;
import models.UserData;

/**
 * Checks if user confirmed that mobile number still belongs to him (should do that periodically)
 * (this class is part of Chain of Responsibility pattern)
 **/
public class MobileNumberRequestHandler extends RequestHandler{

    @Override
    public boolean handle(UserData userData) {

        if (userData.getMobileNumberConfirmed().after(DatabaseMock.getNextMobileConfirmation(userData.getUserId()))){
            //trigger mobile confirmation check again
        }
        return super.passFurther(userData);
    }
}
