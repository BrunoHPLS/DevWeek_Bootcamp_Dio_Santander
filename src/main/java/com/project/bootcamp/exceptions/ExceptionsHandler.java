package com.project.bootcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe responsável por interceptar quando algo acontecer e retornar o status code para o Front
 *
 */
@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
    //Este tipo de classe vai tratar exceções de Response

    @ExceptionHandler({BusinessException.class}) //Ao propagar uma exceção Business, vem direto para esse método
    protected ResponseEntity<ExceptionResponse> handlerSecurity(BusinessException e){
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ExceptionResponse(e.getMessage()));
    //Sempre que a exceção ocorrer, será retornado um Error 422 com a mensagem de BusinessException em formato Json
    }
    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<ExceptionResponse> handlerSecurity(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(e.getMessage()));
    }
}
