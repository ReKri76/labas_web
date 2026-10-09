package io.rekri.lab5.config.security;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.AuthorizationResult;

import java.util.function.Supplier;

public class AdminAuthorizationManager implements AuthorizationManager {
    @Override
    public @Nullable AuthorizationResult authorize(Supplier authentication, Object object) {
        return null;
    }
}
