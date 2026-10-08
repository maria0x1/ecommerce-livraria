package br.unitins.tp1.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProblemDetails(
        String type,
        String title,
        int status,
        String detail,
        String instance,
        List<Violation> violations) {

    public record Violation(String field, String message) {
    }
}
