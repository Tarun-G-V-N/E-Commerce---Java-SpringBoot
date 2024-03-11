package com.simplestore.UserService.exceptions;

import java.util.Date;

public class SessionLimitExceededException extends RuntimeException{

    public SessionLimitExceededException(Date date1, Date date2) {

        super("There are 2 active sessions started at "+date1+" and "+date2);
    }
}
