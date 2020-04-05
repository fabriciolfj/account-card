package br.com.accountcard.domain.exception;

public class CustomerNotFound extends RuntimeException {

    public CustomerNotFound(final String msg) {
        super(msg);
    }
}
