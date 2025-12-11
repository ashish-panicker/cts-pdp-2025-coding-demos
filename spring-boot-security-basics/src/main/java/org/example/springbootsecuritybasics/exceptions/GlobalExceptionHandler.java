package org.example.springbootsecuritybasics.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/*
 * Global Exception Handling
 * @RestControllerAdvice
 * - Application wide JSON error models
 * - Mapping exception to error codes
 * - Logging and co-relation id's
 *
 * Methods inside this are automatically discovered by ExceptionHandlerExceptionResolver
 * and RequestResponseBodyMethodProcessor
 * Return values of methods are written out using HttpMessageConverter
 * Supports narrow scoping
 * = Exceptions raised form classes in a package
 * - Example: @RestControllerAdvice("org.example.springbootsecuritybasics.security")
 * - Exceptions raised from certain classes
 * - Example: @RestControllerAdvice(assignableTypes = {AuthConfig.class, EmployeeController.class})
 * - Exceptions raised from certain classes with these annotations
 * - Example: @RestControllerAdvice(annotations = RestController.class)
 *
 * You can determine the order in which exception handler beans are evaluated
 * using the @Order annotation
 * @Order(1), lower values get more precedence
 */
@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(1)
public class GlobalExceptionHandler {

    private ApiError buildError(HttpStatus status, String message, String path) {
        return new ApiError(LocalDateTime.now(), status.value(), status.getReasonPhrase(), message, path);
    }

//    Custom data binding
//    @InitBinder
//    public void bindString(WebDataBinder binder) {
//        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthentication(AuthenticationException ex, HttpServletRequest req) {
        ApiError error = buildError(HttpStatus.UNAUTHORIZED, ex.getMessage(), req.getRequestURI());
        log.error("Authentication Exception: ", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException ex, HttpServletRequest req) {
        ApiError error = buildError(HttpStatus.FORBIDDEN, "You do not have permission to access this resource", req.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {

        var msg = ex.getBindingResult().getFieldErrors().stream().map(err -> err.getField() + " " + err.getDefaultMessage()).findFirst().orElse("Validation error");

        ApiError error = buildError(HttpStatus.BAD_REQUEST, msg, req.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntime(RuntimeException ex, HttpServletRequest req) {
        ApiError error = buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex, HttpServletRequest req) {

        // Log full error on console
        ex.printStackTrace();

        ApiError error = buildError(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", req.getRequestURI());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
