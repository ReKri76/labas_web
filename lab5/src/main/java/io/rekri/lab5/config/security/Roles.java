package io.rekri.lab5.config.security;

public enum Roles {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_MODERATOR,
    ROLE_ANON;

    @Override
    public String toString() {
        return switch (this){
            case ROLE_ANON -> "ROLE_ANON";
            case ROLE_USER -> "ROLE_USER";
            case ROLE_ADMIN ->"ROLE_ADMIN";
            case ROLE_MODERATOR ->"ROLE_MODERATOR";
        };
    }
}
