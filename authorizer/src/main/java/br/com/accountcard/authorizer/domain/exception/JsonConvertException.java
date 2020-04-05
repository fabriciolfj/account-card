package br.com.accountcard.authorizer.domain.exception;

public class JsonConvertException extends RuntimeException {

    public JsonConvertException(final String msg) {
        super(msg);
    }
}
