package com.example.challenge.api.exception;

import com.example.challenge.api.service.MensageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidacaoHandlerException extends ResponseEntityExceptionHandler {

    @Autowired
    private MensageService mensageService;

    @ExceptionHandler({CepInvalidoException.class})
    public ResponseEntity<Object> handlerCepInvalidoException(CepInvalidoException exception, WebRequest request){
        Object[] args = {exception.getMessage()};
        return handlerException(exception,HttpStatus.BAD_REQUEST, request, "cep-invalido", args);
    }

    @ExceptionHandler({CepNaoEncontradoException.class})
    public ResponseEntity<Object> handlerCepNaoEncontradoException(CepNaoEncontradoException exception, WebRequest request){
        Object[] args = {exception.getMessage()};
        return handlerException(exception,HttpStatus.NOT_FOUND, request, "cep-nao-encontrado", args);
    }

    private ResponseEntity<Object> handlerException(Exception exception, HttpStatus status, WebRequest request, String key, Object[] args) {
        Error response = new Error(List.of((mensageService.getMessage(key, args))));
        response.setStatusCode(status.value());
        return handleExceptionInternal(exception, response, new HttpHeaders(), status, request);
    }


}
