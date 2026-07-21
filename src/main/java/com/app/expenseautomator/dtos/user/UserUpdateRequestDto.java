package com.app.expenseautomator.dtos.user;

import java.util.Optional;

public record UserUpdateRequestDto(String name, Optional<String> email) {}
