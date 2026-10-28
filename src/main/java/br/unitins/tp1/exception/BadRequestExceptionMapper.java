package br.unitins.tp1.exception;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Context
    UriInfo uriInfo;

    @Override
    public Response toResponse(BadRequestException exception) {
        String detail = exception.getMessage();
        if (detail == null || detail.isBlank()) {
            detail = "A requisição não pôde ser processada.";
        }

        return ProblemDetailsFactory.response(
                uriInfo,
                Response.Status.BAD_REQUEST.getStatusCode(),
                "Requisição inválida",
                detail,
                null);
    }
}
