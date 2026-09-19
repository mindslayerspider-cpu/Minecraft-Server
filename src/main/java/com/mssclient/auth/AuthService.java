package com.mssclient.auth;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

/** Handles Microsoft OAuth device-code flow integration points and offline sessions. */
public final class AuthService {
    private final SecureRandom random = new SecureRandom(); private AuthSession session = offline("Player");
    public AuthSession offline(String username) { var safe = username == null || username.isBlank() ? "Player" : username.trim(); session = new AuthSession(safe, "offline-" + token(), true); return session; }
    public AuthSession beginMicrosoftLogin() { session = new AuthSession("MicrosoftUser", "msal-placeholder-" + token(), false); return session; }
    public Optional<AuthSession> currentSession() { return Optional.ofNullable(session); }
    private String token() { var bytes = new byte[18]; random.nextBytes(bytes); return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes); }
}
