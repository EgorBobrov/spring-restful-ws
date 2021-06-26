package com.bobrove.ws.mobileappws.ws.security;

public final class SecurityConstants {
    private SecurityConstants() {}

    public static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 10; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String TOKEN_SECRET = "jf9i4jgu83nfl0";

}
