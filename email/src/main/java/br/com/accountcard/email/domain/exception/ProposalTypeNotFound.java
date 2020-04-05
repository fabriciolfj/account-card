package br.com.accountcard.email.domain.exception;

public class ProposalTypeNotFound extends RuntimeException {

    public ProposalTypeNotFound(final String msg) {
        super(msg);
    }
}
