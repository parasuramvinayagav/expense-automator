package com.app.expenseautomator.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "email", "name"})
public record UserDto(Long id, String email, String name) {}
