package br.com.accountcard.authorizer.api.exception;

import lombok.Getter;

@Getter
public enum TypeError {

    INVALID_DATA("Invalid data"),
    SERVER_ERROR("Server error"),
    PARAMETER_INVALID("Invalid parameter");

    private String describe;

    TypeError(final String describe) {
        this.describe = describe;
    }
}
