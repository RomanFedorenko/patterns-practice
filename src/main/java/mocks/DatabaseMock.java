package mocks;

import models.UserData;

import java.util.Date;
import java.util.UUID;

import static constants.PatternsConstants.BLACKLISTED_LOGIN;
import static constants.PatternsConstants.SUCCESSFULL_LOGIN;

public class DatabaseMock {
    private static final int FIFTH_MAY_2020 = 1621521691;

    public static UserData getUserDataByLogin(String userLogin) {
        switch (userLogin) {
            case BLACKLISTED_LOGIN:
                return UserData.builder()
                        .mobileNumberConfirmed(new Date(FIFTH_MAY_2020))
                        .blacklisted(true)
                        .email("email@example.com")
                        .emailConfirmed(true)
                        .userId(UUID.randomUUID().toString())
                        .build();
            case SUCCESSFULL_LOGIN:
            default:
                return UserData.builder()
                        .mobileNumberConfirmed(new Date(FIFTH_MAY_2020))
                        .blacklisted(false)
                        .email("email@example.com")
                        .emailConfirmed(true)
                        .userId(UUID.randomUUID().toString())
                        .build();
        }
    }

    public static Date getNextMobileConfirmation(String userId) {
        return new Date();
    }
}
