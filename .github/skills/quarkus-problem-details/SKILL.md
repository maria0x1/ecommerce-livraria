---
name: quarkus-problem-details
description: Implement and verify centralized RFC 9457 Problem Details responses in Quarkus REST APIs.
---

# Quarkus Problem Details

Use this skill when adding or changing consistent REST error responses in a Quarkus application using Jakarta REST.

## Implementation procedure

1. Inspect the Quarkus REST and JSON extensions, existing exception mappers, validation annotations, resource behavior, and test conventions. Reuse the project's package layout and serialization conventions.
2. Define one response model with the RFC 9457 members `type`, `title`, `status`, `detail`, and `instance`. Add validation-specific data as an extension, such as `violations: [{ "field": "...", "message": "..." }]`. Omit an absent extension rather than serializing it as `null` when that matches the API's JSON conventions.
3. Centralize response construction in a small factory/helper so every mapper uses the same media type (`application/problem+json`), status, instance URI, and response body.
4. Register `@Provider` `ExceptionMapper` implementations for the required exception types. Common cases include:
   - `ConstraintViolationException`: map each constraint path and message into `violations`; sort them for deterministic responses.
   - `BadRequestException`: return HTTP 400 with a safe, client-facing explanation. Do not expose parser internals or stack traces.
   - `NotFoundException`: return HTTP 404 with a safe, client-facing explanation.
5. Check how the application's actual operations represent missing entities. Throw `NotFoundException` for absent resources rather than returning `null` or silently succeeding when the API contract requires 404. Avoid unrelated service behavior changes.
6. Keep endpoints free of repeated error-response construction. Preserve existing success response types and media types.
7. Add or update tests to assert the HTTP status, `Content-Type`, required Problem Details fields, `instance`, and validation violations. Also check that absent resources produce 404 and that normal success behavior is unchanged.
8. Run the narrowest relevant test/build command and document the response contract where the project keeps API documentation.

## Response rules

- Keep the body's `status` equal to the actual HTTP status.
- Use stable, localized titles and details consistent with the application.
- `instance` should identify the request that produced the error; do not put sensitive query values in it.
- Use `about:blank` for `type` when no more specific problem type is defined.
- Never include exception messages, SQL, stack traces, or internal implementation details in client-facing errors unless they have been explicitly reviewed as safe.
- Do not add a catch-all mapper that turns every unexpected exception into a success-shaped or misleading client error. If 500 responses are standardized, keep their status accurate and their details generic.

## Example validation response

```json
{
  "type": "about:blank",
  "title": "Validation failed",
  "status": 400,
  "detail": "One or more fields are invalid.",
  "instance": "/books",
  "violations": [
    {
      "field": "title",
      "message": "must not be blank"
    }
  ]
}
```
