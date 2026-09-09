package com.app.expenseautomator.dtos.auth;

import java.util.Date;

public record JwtResponse(
    String token,
    String authType,
    Date expiration
) {
    public JwtResponse(String token, Date expiration) {
        this(token, "Bearer", expiration);
    }
}
