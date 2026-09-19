package com.mssclient.auth;
public record AuthSession(String username, String accessToken, boolean offline) {}
