package models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserData {
    private String userId;
    private Date mobileNumberConfirmed;
    private String email;
    private boolean emailConfirmed;
    private boolean blacklisted;

}
