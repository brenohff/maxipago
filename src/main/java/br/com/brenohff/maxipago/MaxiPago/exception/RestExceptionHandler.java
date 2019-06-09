package br.com.brenohff.maxipago.MaxiPago.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CidadeNaoEncontrada.class)
    public ResponseEntity<?> handleResourceNotFoundException(CidadeNaoEncontrada handler) {

        DetalheErro erro = new DetalheErro(HttpStatus.NO_CONTENT, handler.getMessage());

        return new ResponseEntity<>(erro, null, HttpStatus.NO_CONTENT);
    }

}

class DetalheErro {
    DetalheErro(HttpStatus httpStatus, String mensagem) {
    }
}
