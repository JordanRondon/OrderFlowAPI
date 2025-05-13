package com.OrderFlowAPI.OrderFlowAPI.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.OrderFlowAPI.OrderFlowAPI.dto.response.ErrorDetailsDto;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(value = MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorDetailsDto> ValidationExceptionHandle(MethodArgumentNotValidException ex) {
                List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .collect(Collectors.toList());

                ErrorDetailsDto errorDto = ErrorDetailsDto.builder()
                                .timestamp(ZonedDateTime.now(ZoneId.of("America/Lima")).toLocalDateTime())
                                .code(ErrorCode.VALIDATION_ERROR.getCode())
                                .message("Validation error in request body")
                                .details(String.join(", ", errors))
                                .build();
                return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ErrorDetailsDto> handleConstraintViolationException(
                        ConstraintViolationException ex) {
                List<String> errors = ex.getConstraintViolations()
                                .stream()
                                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                                .collect(Collectors.toList());

                ErrorDetailsDto errorDto = ErrorDetailsDto.builder()
                                .timestamp(ZonedDateTime.now(ZoneId.of("America/Lima")).toLocalDateTime())
                                .code(ErrorCode.INVALID_INPUT.getCode())
                                .message("Validation error in URL parameters")
                                .details(String.join(", ", errors))
                                .build();
                return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(value = RequestException.class)
        public ResponseEntity<ErrorDetailsDto> RequestExceptionHandler(RequestException ex, WebRequest request) {
                ErrorDetailsDto errorDto = ErrorDetailsDto.builder()
                                .timestamp(ZonedDateTime.now(ZoneId.of("America/Lima")).toLocalDateTime())
                                .code(ex.getErrorCode().getCode())
                                .message(ex.getMessage())
                                .details(request.getDescription(false))
                                .build();
                return new ResponseEntity<>(errorDto, ex.getStatus());
        }

        @ExceptionHandler(value = BusinessException.class)
        public ResponseEntity<ErrorDetailsDto> BusinessExceptionHandler(BusinessException ex, WebRequest request) {
                ErrorDetailsDto errorDto = ErrorDetailsDto.builder()
                                .timestamp(ZonedDateTime.now(ZoneId.of("America/Lima")).toLocalDateTime())
                                .code(ex.getErrorCode().getCode())
                                .message(ex.getMessage())
                                .details(request.getDescription(false))
                                .build();
                return new ResponseEntity<>(errorDto, ex.getStatus());
        }
}
