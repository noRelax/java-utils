package com.huiton.mainframe.util;

public interface LoginStatusKeys {

    public final String WRONG_PASSWORD = "0"; // password wrong
    public final String SUCCEED = "1"; // succeed
    public final String NO_USER = "2"; // no this user

    public final String NO_YEAR = "3"; // no this year
    public final String DB_ERROR = "4"; // db error
    public final String UNKNOWN_ERROR = "5"; // unknow error

    public final String COMPANY_USER_OVERFLOW = "6"; // current company users number reaches max
    public final String USER_OVERFLOW = "7"; // permit no reaches max
    public final String IP_FORBID = "8"; // ip restriction
    public final String STATUS_FORBID = "9"; // user_status is n

}