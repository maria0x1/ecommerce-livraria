package br.unitins.tp1.exception;

import java.util.Comparator;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        var violations = exception.getConstraintViolations().stream()
                .map(violation -> new ProblemDetails.Violation(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()))
                .sorted(Comparator.comparing(ProblemDetails.Violation::field)
                        .thenComparing(ProblemDetails.Violation::message))
                .toList();

        return ProblemDetailsFactory.response(
                uriInfo,
                Response.Status.BAD_REQUEST.getStatusCode(),
                "Erro de validação",
                "Um ou mais campos da requisição são inválidos.",
                violations);
    }
}
