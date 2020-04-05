package br.com.accountcard.authorizer.api.exception;

import br.com.accountcard.authorizer.domain.exception.CPFInvalidException;
import br.com.accountcard.authorizer.domain.exception.JsonConvertException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static br.com.accountcard.authorizer.api.exception.TypeError.SERVER_ERROR;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(JsonConvertException.class)
    public ResponseEntity<?> handleJsonConvertException(final JsonConvertException e, final WebRequest request) {
        final var status = HttpStatus.INTERNAL_SERVER_ERROR;
        final var standarError = createErrorBuilder(status, SERVER_ERROR, e.getMessage()).build();
        return handleExceptionInternal(e, standarError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(CPFInvalidException.class)
    public ResponseEntity<?> handleCPFInvalid(final CPFInvalidException e, final WebRequest request) {
        final var status = HttpStatus.BAD_REQUEST;
        final var standarError = createErrorBuilder(status, TypeError.INVALID_DATA, e.getMessage()).build();
        return handleExceptionInternal(e, standarError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status,
                                                                  final WebRequest request) {
        return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(final Exception ex,
                                                            final BindingResult bindingResult,
                                                            final HttpHeaders headers,
                                                            final HttpStatus status,
                                                            final WebRequest request) {

        final var detail = "One or more fields with missing data or invalid.";
        final var errors = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    final var msg = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
                    var name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return StandardError.Object.builder()
                            .name(name)
                            .userMessage(msg)
                            .build();
                }).collect(Collectors.toList());

        final var standardError = createErrorBuilder(status, TypeError.INVALID_DATA, detail)
                .objects(errors)
                .build();

        return handleExceptionInternal(ex, standardError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, final Object body, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private StandardError.StandardErrorBuilder createErrorBuilder(final HttpStatus status, final TypeError error, final String detail) {
        return StandardError.builder()
                .title(status.getReasonPhrase())
                .detail(detail)
                .status(status.value())
                .type(error.getDescribe())
                .timestamp(LocalDateTime.now());
    }
}
