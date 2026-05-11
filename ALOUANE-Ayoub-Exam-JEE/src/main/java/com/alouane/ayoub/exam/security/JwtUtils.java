package com.alouane.ayoub.exam.security;

public class JwtUtils {
    public static final String SECRET = "mySecretKey01234567890123456789012345678901234567890123456789";
    public static final String AUTH_HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 24 * 60 * 60 * 1000;
}
