package com.bobrove.ws.mobileappws.ws.shared.security;

import com.bobrove.ws.mobileappws.ws.shared.SpringApplicationContextAccessor;

public final class SecurityConstants {
    private SecurityConstants() {}

    public static final long TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 10; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";

    public static String getTokenSecret() {
        SecurityProperties properties = SpringApplicationContextAccessor.getBean("securityProperties");
        return properties.getTokenSecret();
    }

}
