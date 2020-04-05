package br.com.accountcard.email.domain.exception;

public class CustomerNotFound extends RuntimeException {

    public CustomerNotFound(final String msg) {
        super(msg);
    }
}
