package br.unitins.tp1.exception;

import java.util.List;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

final class ProblemDetailsFactory {

    private static final String PROBLEM_MEDIA_TYPE = "application/problem+json";

    private ProblemDetailsFactory() {
    }

    static Response response(
            UriInfo uriInfo,
            int status,
            String title,
            String detail,
            List<ProblemDetails.Violation> violations) {
        ProblemDetails problem = new ProblemDetails(
                "about:blank",
                title,
                status,
                detail,
                uriInfo.getRequestUri().getPath(),
                violations);

        return Response.status(status)
                .type(PROBLEM_MEDIA_TYPE)
                .entity(problem)
                .build();
    }
}
