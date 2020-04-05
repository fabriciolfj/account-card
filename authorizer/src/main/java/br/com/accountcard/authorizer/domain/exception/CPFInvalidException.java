package br.com.accountcard.authorizer.domain.exception;

public class CPFInvalidException extends RuntimeException {

    public CPFInvalidException(String msg) {
        super(msg);
    }
}
